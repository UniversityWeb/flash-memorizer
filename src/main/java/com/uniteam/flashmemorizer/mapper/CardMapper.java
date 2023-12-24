package com.uniteam.flashmemorizer.mapper;

import com.uniteam.flashmemorizer.dto.CardDTO;
import com.uniteam.flashmemorizer.entity.Card;
import org.springframework.stereotype.Component;

@Component
public class CardMapper extends BaseMapper<Card, CardDTO> {

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
