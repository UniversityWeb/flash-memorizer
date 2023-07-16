package com.universityteam.flashmemorizer.converter;

import com.universityteam.flashmemorizer.dto.SharedDeckDTO;
import com.universityteam.flashmemorizer.entity.SharedDeck;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class SharedDeckConverter {

    @Autowired
    private ModelMapper mapper;

    public List<SharedDeckDTO> convertEntityToDto(List<SharedDeck> sharedDecks) {
        return sharedDecks.stream()
                .map(this::convertEntityToDto)
                .collect(Collectors.toList());
    }

    public SharedDeckDTO convertEntityToDto(SharedDeck shareDeck) {
        return (shareDeck == null) ? null : mapper.map(shareDeck, SharedDeckDTO.class);
    }

    public List<SharedDeck> convertDtoToEntity(List<SharedDeckDTO> sharedDeckDTOs) {
        return sharedDeckDTOs.stream()
                .map(this::convertDtoToEntity)
                .collect(Collectors.toList());
    }

    public SharedDeck convertDtoToEntity(SharedDeckDTO cardDTO) {
        return (cardDTO == null) ? null : mapper.map(cardDTO, SharedDeck.class);
    }
}