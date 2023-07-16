package com.universityteam.flashmemorizer.converter;

import com.universityteam.flashmemorizer.dto.CardDTO;
import com.universityteam.flashmemorizer.entity.Card;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CardConverter {

    @Autowired
    private ModelMapper mapper;

    public List<CardDTO> convertEntityToDto(List<Card> cards) {
        return cards.stream()
                .map(this::convertEntityToDto)
                .collect(Collectors.toList());
    }

    public CardDTO convertEntityToDto(Card card) {
        return (card == null) ? null : mapper.map(card, CardDTO.class);
    }

    public List<Card> convertDtoToEntity(List<CardDTO> cardDTOs) {
        return cardDTOs.stream()
                .map(this::convertDtoToEntity)
                .collect(Collectors.toList());
    }

    public Card convertDtoToEntity(CardDTO cardDTO) {
        return (cardDTO == null) ? null : mapper.map(cardDTO, Card.class);
    }
}
