package com.uniteam.flashmemorizer.mapper;

import com.uniteam.flashmemorizer.config.AppConfig;
import com.uniteam.flashmemorizer.dto.CardDTO;
import com.uniteam.flashmemorizer.entity.Card;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@SpringBootTest(classes = AppConfig.class)
@ContextConfiguration(classes = CardMapper.class)
class CardMapperTest {
    @Autowired
    private CardMapper cardMapper;

    @Test
    public void testConvertEntityToDto() {
        // Arrange
        Card card = Card.builder()
                .id(1L)
                .term("RESTFul API")
                .desc("This is web application which can help people improve ability to memory")
                .deck(null)
                .build();

        // Act
        CardDTO cardDTO = cardMapper.toDto(card);

        // Assert
        assertEquals(cardDTO.getId(), card.getId());
        assertEquals(cardDTO.getTerm(), card.getTerm());
        assertEquals(cardDTO.getDesc(), card.getDesc());
        assertEquals(cardDTO.getDeck(), card.getDeck());
    }

    @Test
    public void testConvertEntityToDtoNullCase() {
        // Arrange
        Card card = null;

        // Act
        CardDTO cardDTO = cardMapper.toDto(card);

        // Assert
        assertNull(cardDTO);
    }

    @Test
    public void testConvertDtoToEntity() {
        // Arrange
        CardDTO cardDTO = CardDTO.builder()
                .id(1L)
                .term("Spring boot application")
                .desc("This is web application which can help people improve ability to memory")
                .deck(null)
                .build();

        // Act
        Card card = cardMapper.toEntity(cardDTO);

        // Assert
        assertEquals(cardDTO.getId(), card.getId());
        assertEquals(cardDTO.getTerm(), card.getTerm());
        assertEquals(cardDTO.getDesc(), card.getDesc());
        assertEquals(cardDTO.getDeck(), card.getDeck());
    }

    @Test
    public void testConvertDtoToEntityNullCase() {
        // Arrange
        CardDTO cardDTO = null;

        // Act
        Card card = cardMapper.toEntity(cardDTO);

        // Assert
        assertNull(card);
    }
}