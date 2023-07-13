package com.universityteam.flashmemorizer.converter;

import com.universityteam.flashmemorizer.dto.DeckDTO;
import com.universityteam.flashmemorizer.entity.Deck;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DeckConverter {

    public List<DeckDTO> convertEntityToDto(List<Deck> decks) {
        return decks.stream()
                .map(deck -> convertEntityToDto(deck))
                .toList();
    }

    public DeckDTO convertEntityToDto(Deck deck) {
        if (deck == null) return null;
        ModelMapper modelMapper = new ModelMapper();
        DeckDTO deckDTO = modelMapper.map(deck, DeckDTO.class);
        return deckDTO;
    }

    public List<Deck> convertDtoToEntity(List<DeckDTO> deckDTOs) {
        return deckDTOs.stream()
                .map(deck -> convertDtoToEntity(deck))
                .toList();
    }

    public Deck convertDtoToEntity(DeckDTO deckDTO) {
        if (deckDTO == null) return null;
        ModelMapper modelMapper = new ModelMapper();
        Deck deck = modelMapper.map(deckDTO, Deck.class);
        return deck;
    }
}
