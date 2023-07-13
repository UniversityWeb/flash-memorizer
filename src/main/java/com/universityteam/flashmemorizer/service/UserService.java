package com.universityteam.flashmemorizer.service;

import com.universityteam.flashmemorizer.dto.UserDTO;

public interface UserService {
    UserDTO add(UserDTO userDTO);
    UserDTO update(UserDTO userDTO);
    UserDTO getById(Long Id);
}
