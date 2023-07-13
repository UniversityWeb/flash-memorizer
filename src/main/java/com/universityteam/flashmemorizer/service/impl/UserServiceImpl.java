package com.universityteam.flashmemorizer.service.impl;

import com.universityteam.flashmemorizer.converter.UserConverter;
import com.universityteam.flashmemorizer.dto.UserDTO;
import com.universityteam.flashmemorizer.entity.User;
import com.universityteam.flashmemorizer.repository.UserRepository;
import com.universityteam.flashmemorizer.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepo;

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
}
