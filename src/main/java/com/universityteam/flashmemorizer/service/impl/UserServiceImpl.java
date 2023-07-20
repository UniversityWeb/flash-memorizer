package com.universityteam.flashmemorizer.service.impl;

import com.universityteam.flashmemorizer.converter.UserConverter;
import com.universityteam.flashmemorizer.dto.UserDTO;
import com.universityteam.flashmemorizer.entity.User;
import com.universityteam.flashmemorizer.exception.UserAlreadyExistsException;
import com.universityteam.flashmemorizer.registration.token.VerificationTokenRepository;
import com.universityteam.flashmemorizer.registration.token.VerificationToken;
import com.universityteam.flashmemorizer.registration.RegistrationRequest;
import com.universityteam.flashmemorizer.repository.UserRepository;
import com.universityteam.flashmemorizer.service.UserService;

import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    @Autowired
    private final UserRepository userRepo;
    private final PasswordEncoder passwordEncoder;
    private final VerificationTokenRepository tokenRepository;

    @Autowired
    private UserConverter userConverter;

    @Override
    public UserDTO add(UserDTO userDTO) {
        User user = userConverter.convertDtoToEntity(userDTO);
        try {
            User added = userRepo.save(user);
            return userConverter.convertEntityToDto(added);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public UserDTO update(UserDTO userDTO) {
        Optional<User> optUser = userRepo.findById(userDTO.getId());
        if (optUser.isEmpty()) return null;
        try {
            User updated = userRepo.save(optUser.get());
            return userConverter.convertEntityToDto(updated);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public UserDTO getById(Long Id) {
        User user = userRepo.findById(Id).get();
        return userConverter.convertEntityToDto(user);
    }

    @Override
    public List<UserDTO> getUsers(){
        return userConverter.convertEntityToDto(userRepo.findAll());
    }

    @Override
    public UserDTO findByEmail(String email) {
        List<UserDTO> users = getUsers();
        for (UserDTO user : users)
            if(user.getEmail().compareTo(email) == 0)
                return user;
        return null;
    }

    @Override
    public UserDTO registerUser(RegistrationRequest request) {
        Optional<UserDTO> user = Optional.of(this.findByEmail(request.email()));

        if(user.isPresent()){
            throw new UserAlreadyExistsException(
                "User with email " + request.email() + " already exist");
        }

        var newUser = new UserDTO();
        newUser.setUsername(request.username());
        newUser.setPass(passwordEncoder.encode(request.pass()));
        newUser.setFullName(request.fullName());
        newUser.setEmail(request.email());
        newUser.setRole(request.role());
        return add(newUser);
    }


    @Override
    public void saveUserVerifycationToken(UserDTO theUser, String token){
        var verificationToken = new VerificationToken(token, theUser);
        tokenRepository.save(verificationToken);
    }

    @Override
    public String validateToken(String theToken){
        VerificationToken token = tokenRepository.findByToken(theToken);
        if(token == null){
            return "Invalid verification token";
        }
        UserDTO user = token.getUser();
        Calendar calendar = Calendar.getInstance();
        if(token.getExpirationTime().getTime() -  calendar.getTime().getTime() <= 0){
            tokenRepository.delete(token);
            return "Token already expired";
        }
        user.setEnable(true);
        this.add(user);
        return "valid";
    }

    public VerificationToken generateNewVerificationCode(String oldToken){
        VerificationToken verificationToken = tokenRepository.findByToken(oldToken);
        var verificationTokenTime = new VerificationToken();
        verificationToken.setToken(UUID.randomUUID().toString());
        verificationToken.setExpirationTime(verificationTokenTime.getExpirationTime());
        return tokenRepository.save(verificationToken);
    }
}
