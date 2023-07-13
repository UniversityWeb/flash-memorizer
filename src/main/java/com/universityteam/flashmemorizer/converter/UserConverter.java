package com.universityteam.flashmemorizer.converter;

import com.universityteam.flashmemorizer.dto.UserDTO;
import com.universityteam.flashmemorizer.entity.User;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserConverter {
    public List<UserDTO> convertEntityToDto(List<User> users) {
        return users.stream()
                .map(user -> convertEntityToDto(user))
                .toList();
    }

    public UserDTO convertEntityToDto(User user) {
        if (user == null) return null;
        ModelMapper modelMapper = new ModelMapper();
        UserDTO userDTO = modelMapper.map(user, UserDTO.class);
        return userDTO;
    }

    public List<User> convertDtoToEntity(List<UserDTO> userDTOs) {
        return userDTOs.stream()
                .map(user -> convertDtoToEntity(user))
                .toList();
    }

    public User convertDtoToEntity(UserDTO userDTO) {
        if (userDTO == null) return null;
        ModelMapper modelMapper = new ModelMapper();
        User user = modelMapper.map(userDTO, User.class);
        return user;
    }
}
