package com.universityteam.flashmemorizer.converter;

import com.universityteam.flashmemorizer.dto.UserCardDTO;
import com.universityteam.flashmemorizer.entity.UserCard;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserCardConverter {

    @Autowired
    private ModelMapper mapper;

    public List<UserCardDTO> convertEntityToDto(List<UserCard> userCards) {
        return userCards.stream()
                .map(this::convertEntityToDto)
                .collect(Collectors.toList());
    }

    public UserCardDTO convertEntityToDto(UserCard userCard) {
        return (userCard == null) ? null : mapper.map(userCard, UserCardDTO.class);
    }

    public List<UserCard> convertDtoToEntity(List<UserCardDTO> cardDTOs) {
        return cardDTOs.stream()
                .map(this::convertDtoToEntity)
                .collect(Collectors.toList());
    }

    public UserCard convertDtoToEntity(UserCardDTO userCardDTO) {
        return (userCardDTO == null) ? null : mapper.map(userCardDTO, UserCard.class);
    }
}
