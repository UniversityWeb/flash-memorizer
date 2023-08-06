package com.universityteam.flashmemorizer.service;

import com.universityteam.flashmemorizer.dto.UserDTO;
import com.universityteam.flashmemorizer.records.RegistrationRequest;
import com.universityteam.flashmemorizer.entity.VerificationToken;

import java.util.List;

public interface UserService {
    UserDTO add(UserDTO userDTO);

    UserDTO update(UserDTO userDTO);

    UserDTO getById(Long Id);
    List<UserDTO> getUsers();

    UserDTO findByEmail(String email);

    UserDTO registerUser(RegistrationRequest request);

    void saveUserVerifycationToken(UserDTO theUser, String token);

    String validateToken(String theToken);

    VerificationToken generateNewVerificationCode(String oldToken);
}