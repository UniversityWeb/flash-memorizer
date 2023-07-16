package com.universityteam.flashmemorizer.converter;

import com.universityteam.flashmemorizer.dto.CardDTO;
import com.universityteam.flashmemorizer.entity.Card;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CardConverter {

    @Autowired
    private ModelMapper mapper;

    public List<CardDTO> convertEntityToDto(List<Card> cards) {
        return cards.stream()
                .map(this::convertEntityToDto)
                .toList();
    }

    public CardDTO convertEntityToDto(Card card) {
        if (card == null) return null;
        CardDTO cardDTO = mapper.map(card, CardDTO.class);
        return cardDTO;
    }

    public List<Card> convertDtoToEntity(List<CardDTO> cardDTOs) {
        return cardDTOs.stream()
                .map(this::convertDtoToEntity)
                .toList();
    }

    public Card convertDtoToEntity(CardDTO cardDTO) {
        if (cardDTO == null) return null;
        Card card = mapper.map(cardDTO, Card.class);
        return card;
    }
}
