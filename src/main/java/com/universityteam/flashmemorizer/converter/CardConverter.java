package com.universityteam.flashmemorizer.converter;

import com.universityteam.flashmemorizer.dto.CardDTO;
import com.universityteam.flashmemorizer.entity.Card;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CardConverter {
    public List<CardDTO> convertEntityToDto(List<Card> cards) {
        return cards.stream()
                .map(card -> convertEntityToDto(card))
                .toList();
    }

    public CardDTO convertEntityToDto(Card card) {
        if (card == null) return null;
        ModelMapper modelMapper = new ModelMapper();
        CardDTO cardDTO = modelMapper.map(card, CardDTO.class);
        return cardDTO;
    }

    public List<Card> convertDtoToEntity(List<CardDTO> cardDTOs) {
        return cardDTOs.stream()
                .map(card -> convertDtoToEntity(card))
                .toList();
    }

    public Card convertDtoToEntity(CardDTO cardDTO) {
        if (cardDTO == null) return null;
        ModelMapper modelMapper = new ModelMapper();
        Card card = modelMapper.map(cardDTO, Card.class);
        return card;
    }
}
