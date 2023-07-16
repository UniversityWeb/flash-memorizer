package com.universityteam.flashmemorizer.service;

import com.universityteam.flashmemorizer.dto.SharedDeckDTO;

import java.util.List;

public interface SharedDeckService {
    SharedDeckDTO add(SharedDeckDTO sharedDeckDTO);
    boolean delete(Long id);
    SharedDeckDTO update(SharedDeckDTO sharedDeckDTO);
    List<SharedDeckDTO> getBySenderId(Long senderId);
}
