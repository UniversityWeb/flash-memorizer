package com.universityteam.flashmemorizer.service;

import com.universityteam.flashmemorizer.dto.UserDTO;
import com.universityteam.flashmemorizer.exception.UserNotFoundException;

public interface UserService {
    UserDTO add(UserDTO userDTO);
    UserDTO update(UserDTO userDTO);
    UserDTO getById(Long Id);
    UserDTO getByUsername(String username) throws UserNotFoundException;
}
