package com.universityteam.flashmemorizer.converter;

import com.universityteam.flashmemorizer.dto.DeckDTO;
import com.universityteam.flashmemorizer.entity.Deck;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DeckConverter {

    @Autowired
    private ModelMapper mapper;

    public List<DeckDTO> convertEntityToDto(List<Deck> decks) {
        return decks.stream()
                .map(deck -> convertEntityToDto(deck))
                .toList();
    }

    public DeckDTO convertEntityToDto(Deck deck) {
        if (deck == null) return null;
        DeckDTO deckDTO = mapper.map(deck, DeckDTO.class);
        return deckDTO;
    }

    public List<Deck> convertDtoToEntity(List<DeckDTO> deckDTOs) {
        return deckDTOs.stream()
                .map(deck -> convertDtoToEntity(deck))
                .toList();
    }

    public Deck convertDtoToEntity(DeckDTO deckDTO) {
        if (deckDTO == null) return null;
        Deck deck = mapper.map(deckDTO, Deck.class);
        return deck;
    }
}
