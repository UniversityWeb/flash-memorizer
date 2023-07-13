package com.universityteam.flashmemorizer.converter;

import com.universityteam.flashmemorizer.dto.CardDTO;
import com.universityteam.flashmemorizer.entity.Card;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@SpringBootTest
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
        CardDTO cardDTO = new CardDTO(1L, "Spring boot application",
                "This is web application which can help people improve ability to memory",
                new Date(), new Date(), null, null);

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