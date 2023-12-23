package com.uniteam.flashmemorizer.service.user;

import com.uniteam.flashmemorizer.mapper.UserMapper;
import com.uniteam.flashmemorizer.dto.UserDTO;
import com.uniteam.flashmemorizer.dto.UserHolder;
import com.uniteam.flashmemorizer.entity.User;
import com.uniteam.flashmemorizer.entity.VerificationToken;
import com.uniteam.flashmemorizer.exception.PasswordMismatchException;
import com.uniteam.flashmemorizer.exception.UserNotFoundException;
import com.uniteam.flashmemorizer.exception.VerificationTokenNotFoundException;
import com.uniteam.flashmemorizer.form.ChangePassForm;
import com.uniteam.flashmemorizer.record.RegistrationRequest;
import com.uniteam.flashmemorizer.repository.UserRepository;
import com.uniteam.flashmemorizer.repository.VerificationTokenRepository;
import com.uniteam.flashmemorizer.utility.Utils;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final Logger log = LogManager.getLogger(UserServiceImpl.class);

    private final UserRepository userRepo;
    private final VerificationTokenRepository verificationTokenRepo;
    private final PasswordEncoder passwordEncoder;
    private final VerificationTokenRepository tokenRepository;;
    private final UserMapper userMapper;

    @Override
    public UserDTO add(UserDTO userDTO) {
        User user = userMapper.toEntity(userDTO);
        try {
            User added = userRepo.save(user);
            return userMapper.toDto(added);
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
        user.setEmail(userDTO.getEmail() );
        user.setFullName( userDTO.getFullName() );
        user.setEnabled(userDTO.isEnabled());

        try {
            User updated = userRepo.save(user);
            return userMapper.toDto(updated);
        } catch (Exception e) {
            log.error(e.getMessage());
            return null;
        }
    }

    @Override
    public UserDTO getById(Long id) throws UserNotFoundException {
        User user = userRepo.findById(id)
                .orElseThrow(() -> new UserNotFoundException("Could not find any users with userId=" + id));
        return userMapper.toDto(user);
    }

    @Override
    public List<UserDTO> getUsers() {
        List<User> users = userRepo.findAll();
        return userMapper.toDto(users);
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
        List<UserDTO> users = this.getUsers();
        for (UserDTO user : users) {
            if (user.getUsername().compareTo(username) == 0)
                return user;
        }
        return null;
    }

    @Override
    public Long getCurrentUserId() {
        try{
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            UserHolder user = (UserHolder) authentication.getPrincipal();
            return user.getUserHolder().getId();
        } catch (Exception e){
            log.error(e.getMessage());
            return null;
        }
    }

    @Override
    public UserDTO registerUser(RegistrationRequest request) {
        var newUser = new UserDTO();
        newUser.setUsername(request.username());
        newUser.setPass(passwordEncoder.encode(request.password()));
        newUser.setFullName(request.fullName());
        newUser.setEmail(request.email());
        return add(newUser);
    }

    public boolean isExistsEmail(String email) {
        Optional<UserDTO> isExistEmail = Optional.ofNullable(this.findByEmail(email));
        if(isExistEmail.isPresent())
            return true;
        return false;
    }
    public boolean isExistUsername(String username) {
        Optional<UserDTO> isExistUsername = Optional.ofNullable(this.findByUsername(username));
        if(isExistUsername.isPresent())
            return true;
        return false;
    }

    public boolean passwordNotMatch(String password, String passwordConfirm) {
        if(password.equals(passwordConfirm))
            return false;
        return true;
    }

    @Override
    public void saveUserVerifycationToken(UserDTO theUser, String token) {
        User user = userMapper.toEntity(theUser);
        var verificationToken = new VerificationToken(token, user);
        tokenRepository.save(verificationToken);
    }

    @Override
    public String validateToken(String theToken) {
        if(theToken == null){
            return Utils.INVALID_TOKEN_MSG;
        }
        VerificationToken token = tokenRepository.findByToken(theToken);
        if(token == null){
            return Utils.OLD_LINK_TOKEN_MSG;
        }
        User user = token.getUser();
        Calendar calendar = Calendar.getInstance();
        if(token.getExpirationTime().getTime() -  calendar.getTime().getTime() <= 0){
            tokenRepository.delete(token);
            return Utils.EXPIRED_TOKEN_MSG;
        }
        UserDTO userDTO = userMapper.toDto(user);
        userDTO.setEnabled(true);
        this.updateNotPassword(userDTO);
        return Utils.SUCCESS_TOKEN_MSG;
    }

    public VerificationToken generateNewVerificationCode(String oldToken) {
        VerificationToken verificationToken = tokenRepository.findByToken(oldToken);
        var verificationTokenTime = new VerificationToken();
        verificationToken.setToken(UUID.randomUUID().toString());
        verificationToken.setExpirationTime(verificationTokenTime.getExpirationTime());
        return tokenRepository.save(verificationToken);
    }

    @Override
    public boolean delete(Long userId) {
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
    public boolean changePassword(ChangePassForm passForm) {
        UserDTO user = getById(passForm.getUserId());
        if (!passwordEncoder.matches(passForm.getCurPass(), user.getPass())) {
            throw new PasswordMismatchException(Utils.INCORRECT_PASSWORD_MSG);
        }
        if (!passForm.getNewPass().equals(passForm.getReTypeNewPass())) {
            throw new PasswordMismatchException(Utils.NOT_MATCH_PASSWORD_MSG);
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

    @Override
    public void deleteUnverifiedUsers(){
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(new Date().getTime());
        calendar.add(Calendar.HOUR, -24);
        List<User> users = userRepo.findAll();
        for (User user: users) {
            if(!user.isEnabled()){
                try {
                    VerificationToken verificationToken = getVerificationTokenByUser(user);
                    if (verificationToken.getTokenExpirationTime().getTime() - calendar.getTime().getTime() <= 0) {
                        verificationTokenRepo.delete(verificationToken);
                        userRepo.delete(user);
                    }
                } catch (Exception e){
                    userRepo.delete(user);
                    log.error(e.getMessage());
                }
            }
        }
    }

    private VerificationToken getVerificationTokenByUser(User user){
        return verificationTokenRepo.findByUser(user)
                .orElseThrow(() -> new VerificationTokenNotFoundException("Could not find any tokens with userId=" + user.getId()));
    }
}