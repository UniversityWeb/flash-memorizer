package com.universityteam.flashmemorizer.service;

import com.universityteam.flashmemorizer.dto.UserCardDTO;
import com.universityteam.flashmemorizer.keys.UserCardId;

public interface UserCardService {
    UserCardDTO add(UserCardDTO userDTO);
    UserCardDTO update(UserCardDTO userDTO);
    UserCardDTO getById(UserCardId Id);
}
