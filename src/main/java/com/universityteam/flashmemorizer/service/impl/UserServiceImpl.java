package com.universityteam.flashmemorizer.service.impl;

import com.universityteam.flashmemorizer.converter.UserConverter;
import com.universityteam.flashmemorizer.dto.LoginDTO;
import com.universityteam.flashmemorizer.dto.UserDTO;
import com.universityteam.flashmemorizer.entity.User;
import com.universityteam.flashmemorizer.entity.VerificationToken;
import com.universityteam.flashmemorizer.exception.PasswordMismatchException;
import com.universityteam.flashmemorizer.exception.UserAlreadyExistsException;
import com.universityteam.flashmemorizer.exception.UserNotFoundException;
import com.universityteam.flashmemorizer.form.ChangePassForm;
import com.universityteam.flashmemorizer.records.RegistrationRequest;
import com.universityteam.flashmemorizer.repository.UserRepository;
import com.universityteam.flashmemorizer.repository.VerificationTokenRepository;
import com.universityteam.flashmemorizer.service.UserService;

import lombok.RequiredArgsConstructor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    private final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

    private final UserRepository userRepo;
    private final PasswordEncoder passwordEncoder;
    private final VerificationTokenRepository tokenRepository;;
    private final UserConverter userConverter;

    @Override
    public UserDTO add(UserDTO userDTO) {
        User user = userConverter.convertDtoToEntity(userDTO);
        try {
            User added = userRepo.save(user);
            return userConverter.convertEntityToDto(added);
        } catch (Exception e) {
            log.error(e.getMessage());
            return null;
        }
    }

    @Override
    public UserDTO updateNotPassword(UserDTO userDTO) throws UserNotFoundException {
        User user = userRepo
                .findById(userDTO.getId())
                .orElseThrow(() -> new UserNotFoundException("Could not find any users with userId=" + userDTO.getId()));

        user.setUsername( userDTO.getUsername() );
        user.setEmail( userDTO.getEmail() );
        user.setFullName( userDTO.getFullName() );

        try {
            User updated = userRepo.save(user);
            return userConverter.convertEntityToDto(updated);
        } catch (Exception e) {
            log.error(e.getMessage());
            return null;
        }
    }

    @Override
    public UserDTO getById(Long id) throws UserNotFoundException {
        User user = userRepo.findById(id)
                .orElseThrow(() -> new UserNotFoundException("Could not find any users with userId=" + id));
        return userConverter.convertEntityToDto(user);
    }

    @Override
    public List<UserDTO> getUsers(){
        List<User> users = userRepo.findAll();
        return userConverter.convertEntityToDto(users);
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
    public UserDTO findByUsername(String username) {
        List<UserDTO> users = getUsers();
        for (UserDTO user : users)
            if(user.getUsername().compareTo(username) == 0)
                return user;
        return null;
    }

    @Override
    public UserDTO findByUsernameAndPassword(String username, String password){
        List<UserDTO> users = getUsers();
        for (UserDTO user : users)
            if(user.getUsername().compareTo(username) == 0
                && user.getPass().compareTo(password) == 0)
                return user;
        return null;
    }

    @Override
    public UserDTO loginUser(LoginDTO login){
        UserDTO user = this.findByUsername(login.getUsename());
        if(user != null){
            String password = login.getPassword();
            String endcodedPassword = user.getPass();
            if(endcodedPassword.matches(password) == true){
                Optional<UserDTO> theUser =
                        Optional.ofNullable(this.findByUsernameAndPassword(login.getUsename(), login.getPassword()));
                if(theUser.isPresent())
                    return theUser.orElse(null);
            }
        }
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
        return add(newUser);
    }


    @Override
    public void saveUserVerifycationToken(UserDTO theUser, String token){
        User user = userConverter.convertDtoToEntity(theUser);
        var verificationToken = new VerificationToken(token, user);
        tokenRepository.save(verificationToken);
    }

    @Override
    public String validateToken(String theToken){
        VerificationToken token = tokenRepository.findByToken(theToken);
        if(token == null){
            return "Invalid verification token";
        }
        User user = token.getUser();
        Calendar calendar = Calendar.getInstance();
        if(token.getExpirationTime().getTime() -  calendar.getTime().getTime() <= 0){
            tokenRepository.delete(token);
            return "Token already expired";
        }
        UserDTO userDTO = userConverter.convertEntityToDto(user);
        userDTO.setEnabled(true);
        this.add(userDTO);
        return "valid";
    }

    public VerificationToken generateNewVerificationCode(String oldToken){
        VerificationToken verificationToken = tokenRepository.findByToken(oldToken);
        var verificationTokenTime = new VerificationToken();
        verificationToken.setToken(UUID.randomUUID().toString());
        verificationToken.setExpirationTime(verificationTokenTime.getExpirationTime());
        return tokenRepository.save(verificationToken);
    }

    @Override
    public boolean delete(Long userId) throws UserNotFoundException {
        Long count = userRepo.countById(userId);
        if (count == null || count == 0)
            throw new UserNotFoundException("Could not find any users with userId=" + userId);
        try {
            userRepo.deleteById(userId);
            return true;
        } catch (Exception e) {
            log.error(e.getMessage());
            return false;
        }
    }

    @Override
    public boolean changePassword(ChangePassForm passForm) throws UserNotFoundException, PasswordMismatchException {
        UserDTO user = getById(passForm.getUserId());
        if (!passwordEncoder.matches(passForm.getCurPass(), user.getPass())) {
            throw new PasswordMismatchException("Incorrect current password");
        }
        if (!passForm.getNewPass().equals(passForm.getReTypeNewPass())) {
            throw new PasswordMismatchException("New password and confirm password do not match");
        }
        String newPassHash = passwordEncoder.encode(passForm.getNewPass());
        try {
            userRepo.updatePasswordById(user.getId(), newPassHash);
            return true;
        } catch (Exception e) {
            log.error(e.getMessage());
            return false;
        }
    }
}
