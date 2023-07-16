package com.universityteam.flashmemorizer.converter;

import com.universityteam.flashmemorizer.config.AppConfig;
import com.universityteam.flashmemorizer.dto.CardDTO;
import com.universityteam.flashmemorizer.entity.Card;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@SpringBootTest(classes = AppConfig.class)
@ContextConfiguration(classes = CardConverter.class)
class CardConverterTest {
    @Autowired
    private CardConverter cardConverter;

    @Test
    public void testConvertEntityToDto() {
        // Arrange
        Card card = Card.builder()
                .id(1L)
                .term("RESTFul API")
                .desc("This is web application which can help people improve ability to memory")
                .creation(new Date())
                .modified(new Date())
                .user(null)
                .deck(null)
                .build();

        // Act
        CardDTO cardDTO = cardConverter.convertEntityToDto(card);

        // Assert
        assertEquals(cardDTO.getId(), card.getId());
        assertEquals(cardDTO.getTerm(), card.getTerm());
        assertEquals(cardDTO.getDesc(), card.getDesc());
        assertEquals(cardDTO.getCreation(), card.getCreation());
        assertEquals(cardDTO.getModified(), card.getModified());
        assertEquals(cardDTO.getUser(), card.getUser());
        assertEquals(cardDTO.getDeck(), card.getDeck());
    }

    @Test
    public void testConvertEntityToDtoNullCase() {
        // Arrange
        Card card = null;

        // Act
        CardDTO cardDTO = cardConverter.convertEntityToDto(card);

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
                .creation(new Date())
                .modified(new Date())
                .user(null)
                .deck(null)
                .build();

        // Act
        Card card = cardConverter.convertDtoToEntity(cardDTO);

        // Assert
        assertEquals(cardDTO.getId(), card.getId());
        assertEquals(cardDTO.getTerm(), card.getTerm());
        assertEquals(cardDTO.getDesc(), card.getDesc());
        assertEquals(cardDTO.getCreation(), card.getCreation());
        assertEquals(cardDTO.getModified(), card.getModified());
        assertEquals(cardDTO.getUser(), card.getUser());
        assertEquals(cardDTO.getDeck(), card.getDeck());
    }

    @Test
    public void testConvertDtoToEntityNullCase() {
        // Arrange
        CardDTO cardDTO = null;

        // Act
        Card card = cardConverter.convertDtoToEntity(cardDTO);

        // Assert
        assertNull(card);
    }
}