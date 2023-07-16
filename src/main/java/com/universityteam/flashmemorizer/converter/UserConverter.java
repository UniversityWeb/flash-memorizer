package com.universityteam.flashmemorizer.converter;

import com.universityteam.flashmemorizer.dto.UserDTO;
import com.universityteam.flashmemorizer.entity.User;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserConverter {

    @Autowired
    private ModelMapper mapper;

    public List<UserDTO> convertEntityToDto(List<User> users) {
        return users.stream()
                .map(this::convertEntityToDto)
                .collect(Collectors.toList());
    }

    public UserDTO convertEntityToDto(User user) {
        return (user == null) ? null : mapper.map(user, UserDTO.class);
    }

    public List<User> convertDtoToEntity(List<UserDTO> userDTOs) {
        return userDTOs.stream()
                .map(this::convertDtoToEntity)
                .collect(Collectors.toList());
    }

    public User convertDtoToEntity(UserDTO userDTO) {
        return (userDTO == null) ? null : mapper.map(userDTO, User.class);
    }
}
