package com.uniteam.flashmemorizer.mapper;

import com.uniteam.flashmemorizer.dto.DeckDTO;
import com.uniteam.flashmemorizer.entity.Deck;
import org.modelmapper.PropertyMap;
import org.springframework.stereotype.Component;

@Component
public class DeckMapper extends BaseMapper<Deck, DeckDTO> {
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
