package com.universityteam.flashmemorizer.converter;

import com.universityteam.flashmemorizer.dto.UserDTO;
import com.universityteam.flashmemorizer.entity.User;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserConverter {

    @Autowired
    private ModelMapper mapper;

    public List<UserDTO> convertEntityToDto(List<User> users) {
        return users.stream()
                .map(this::convertEntityToDto)
                .toList();
    }

    public UserDTO convertEntityToDto(User user) {
        if (user == null) return null;
        UserDTO userDTO = mapper.map(user, UserDTO.class);
        return userDTO;
    }

    public List<User> convertDtoToEntity(List<UserDTO> userDTOs) {
        return userDTOs.stream()
                .map(this::convertDtoToEntity)
                .toList();
    }

    public User convertDtoToEntity(UserDTO userDTO) {
        if (userDTO == null) return null;
        User user = mapper.map(userDTO, User.class);
        return user;
    }
}
