package com.universityteam.flashmemorizer.converter;

import com.universityteam.flashmemorizer.dto.SharedDeckDTO;
import com.universityteam.flashmemorizer.entity.SharedDeck;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SharedDeckConverter {

    @Autowired
    private ModelMapper mapper;

    public List<SharedDeckDTO> convertEntityToDto(List<SharedDeck> sharedDecks) {
        return sharedDecks.stream()
                .map(this::convertEntityToDto)
                .toList();
    }

    public SharedDeckDTO convertEntityToDto(SharedDeck shareDeck) {
        if (shareDeck == null) return null;
        SharedDeckDTO sharedDTO = mapper.map(shareDeck, SharedDeckDTO.class);
        return sharedDTO;
    }

    public List<SharedDeck> convertDtoToEntity(List<SharedDeckDTO> sharedDeckDTOs) {
        return sharedDeckDTOs.stream()
                .map(this::convertDtoToEntity)
                .toList();
    }

    public SharedDeck convertDtoToEntity(SharedDeckDTO cardDTO) {
        if (cardDTO == null) return null;
        SharedDeck card = mapper.map(cardDTO, SharedDeck.class);
        return card;
    }
}
