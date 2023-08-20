package com.universityteam.flashmemorizer.converter;

import com.universityteam.flashmemorizer.dto.CardDTO;
import com.universityteam.flashmemorizer.entity.Card;
import org.springframework.stereotype.Component;

@Component
public class CardConverter extends BaseConverter<Card, CardDTO> {

    @Override
    protected Class<CardDTO> getDtoClass() {
        return CardDTO.class;
    }

    @Override
    protected Class<Card> getEntityClass() {
        return Card.class;
    }

    @Override
    protected void configuration() {

    }
}
