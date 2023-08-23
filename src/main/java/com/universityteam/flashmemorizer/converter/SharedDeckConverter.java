package com.universityteam.flashmemorizer.converter;

import com.universityteam.flashmemorizer.dto.SharedDeckDTO;
import com.universityteam.flashmemorizer.entity.SharedDeck;
import org.springframework.stereotype.Component;

@Component
public class SharedDeckConverter extends BaseConverter<SharedDeck, SharedDeckDTO> {

    @Override
    protected Class<SharedDeckDTO> getDtoClass() {
        return SharedDeckDTO.class;
    }

    @Override
    protected Class<SharedDeck> getEntityClass() {
        return SharedDeck.class;
    }

    @Override
    protected void configuration() {

    }
}