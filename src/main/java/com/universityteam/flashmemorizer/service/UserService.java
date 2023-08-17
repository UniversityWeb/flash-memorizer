package com.universityteam.flashmemorizer.service;

import com.universityteam.flashmemorizer.dto.UserDTO;
import com.universityteam.flashmemorizer.entity.User;
import com.universityteam.flashmemorizer.exception.PasswordMismatchException;
import com.universityteam.flashmemorizer.form.ChangePassForm;
import com.universityteam.flashmemorizer.records.LoginRequest;
import com.universityteam.flashmemorizer.records.RegistrationRequest;
import com.universityteam.flashmemorizer.entity.VerificationToken;
import com.universityteam.flashmemorizer.exception.UserNotFoundException;

import java.util.List;

public interface UserService {
    UserDTO add(UserDTO userDTO);
    UserDTO updateNotPassword(UserDTO userDTO) throws UserNotFoundException;
    List<UserDTO> getUsers();
    UserDTO findByEmail(String email);
    UserDTO findByUsername(String username);
    UserDTO loginUser(LoginRequest login);
    UserDTO registerUser(RegistrationRequest request);
    boolean isExistsEmail(String email);
    boolean isExistUsername(String username);
    boolean passwordNotMatch(String password, String passwordConfirm);
    void saveUserVerifycationToken(UserDTO theUser, String token);
    String validateToken(String theToken);
    VerificationToken generateNewVerificationCode(String oldToken);
    UserDTO getById(Long id) throws UserNotFoundException;
    boolean delete(Long userId) throws UserNotFoundException;
    boolean changePassword(ChangePassForm passForm) throws UserNotFoundException, PasswordMismatchException;
}