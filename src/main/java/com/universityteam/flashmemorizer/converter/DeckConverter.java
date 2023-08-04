package com.universityteam.flashmemorizer.converter;

import com.universityteam.flashmemorizer.dto.DeckDTO;
import com.universityteam.flashmemorizer.entity.Deck;
import jakarta.annotation.PostConstruct;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class DeckConverter {

    @Autowired
    private ModelMapper mapper;

    public List<DeckDTO> convertEntityToDto(List<Deck> decks) {
        return decks.stream()
                .map(this::convertEntityToDto)
                .collect(Collectors.toList());
    }

    public DeckDTO convertEntityToDto(Deck deck) {
        return (deck == null) ? null : mapper.map(deck, DeckDTO.class);
    }

    public List<Deck> convertDtoToEntity(List<DeckDTO> deckDTOs) {
        return deckDTOs.stream()
                .map(this::convertDtoToEntity)
                .collect(Collectors.toList());
    }

    public Deck convertDtoToEntity(DeckDTO deckDTO) {
        return (deckDTO == null) ? null : mapper.map(deckDTO, Deck.class);
    }

    @PostConstruct
    public void setupMapper() {
        mapper.addMappings(new PropertyMap<Deck, DeckDTO>() {
            @Override
            protected void configure() {
                skip(destination.getQuantityOfCards());
            }
        });
    }
}
