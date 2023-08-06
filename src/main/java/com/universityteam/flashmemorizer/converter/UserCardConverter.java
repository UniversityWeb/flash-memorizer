package com.universityteam.flashmemorizer.converter;

import com.universityteam.flashmemorizer.dto.UserCardDTO;
import com.universityteam.flashmemorizer.entity.UserCard;
import org.springframework.stereotype.Component;

@Component
public class UserCardConverter extends BaseConverter<UserCard, UserCardDTO> {
    @Override
    protected Class<UserCardDTO> getDtoClass() {
        return UserCardDTO.class;
    }

    @Override
    protected Class<UserCard> getEntityClass() {
        return UserCard.class;
    }

    @Override
    protected void configuration() {

    }
}
