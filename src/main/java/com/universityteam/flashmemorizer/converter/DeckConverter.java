package com.universityteam.flashmemorizer.converter;

import com.universityteam.flashmemorizer.dto.DeckDTO;
import com.universityteam.flashmemorizer.entity.Deck;
import org.modelmapper.PropertyMap;
import org.springframework.stereotype.Component;

@Component
public class DeckConverter extends BaseConverter<Deck, DeckDTO> {
    @Override
    protected Class<DeckDTO> getDtoClass() {
        return DeckDTO.class;
    }

    @Override
    protected Class<Deck> getEntityClass() {
        return Deck.class;
    }

    @Override
    protected void configuration() {
        mapper.addMappings(new PropertyMap<Deck, DeckDTO>() {
            @Override
            protected void configure() {
                skip(destination.getQuantityOfCards());
            }
        });
    }
}
