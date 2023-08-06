package com.universityteam.flashmemorizer.converter;

import com.universityteam.flashmemorizer.dto.UserDTO;
import com.universityteam.flashmemorizer.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserConverter extends BaseConverter<User, UserDTO> {

    @Override
    protected Class<UserDTO> getDtoClass() {
        return UserDTO.class;
    }

    @Override
    protected Class<User> getEntityClass() {
        return User.class;
    }

    @Override
    protected void configuration() {

    }
}
