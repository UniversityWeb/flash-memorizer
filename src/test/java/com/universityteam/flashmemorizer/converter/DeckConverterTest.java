package com.universityteam.flashmemorizer.converter;

import com.universityteam.flashmemorizer.dto.DeckDTO;
import com.universityteam.flashmemorizer.entity.Deck;
import com.universityteam.flashmemorizer.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@SpringBootTest
@ContextConfiguration(classes = DeckConverter.class)
class DeckConverterTest {
    @Autowired
    private DeckConverter deckConverter;

    @Test
    public void testConvertEntityToDto() {
        // Arrange
        Deck deck = Deck.builder()
                .id(1L)
                .name("Spring boot application")
                .desc("This is web application which can help people improve ability to memory")
                .creation(new Date())
                .modified(new Date())
                .user(new User())
                .build();

        // Act
        DeckDTO deckDTO = deckConverter.convertEntityToDto(deck);

        // Assert
        assertEquals(deckDTO.getId(), deck.getId());
        assertEquals(deckDTO.getName(), deck.getName());
        assertEquals(deckDTO.getDesc(), deck.getDesc());
        assertEquals(deckDTO.getCreation(), deck.getCreation());
        assertEquals(deckDTO.getModified(), deck.getModified());
        assertEquals(deckDTO.getUser(), deck.getUser());
    }

    @Test
    public void testConvertEntityToDtoNullCase() {
        // Arrange
        Deck deck = null;

        // Act
        DeckDTO deckDTO = deckConverter.convertEntityToDto(deck);

        // Assert
        assertNull(deckDTO);
    }

    @Test
    public void testConvertDtoToEntity() {
        // Arrange
        DeckDTO deckDTO = new DeckDTO(1L, "Spring boot application",
                "This is web application which can help people improve ability to memory",
                new Date(), new Date(), new User());

        // Act
        Deck deck = deckConverter.convertDtoToEntity(deckDTO);

        // Assert
        assertEquals(deck.getId(), deckDTO.getId());
        assertEquals(deck.getName(), deckDTO.getName());
        assertEquals(deck.getDesc(), deckDTO.getDesc());
        assertEquals(deck.getCreation(), deckDTO.getCreation());
        assertEquals(deck.getModified(), deckDTO.getModified());
        assertEquals(deck.getUser(), deckDTO.getUser());
    }

    @Test
    public void testConvertDtoToEntityNullCase() {
        // Arrange
        DeckDTO deckDTO = null;

        // Act
        Deck deck = deckConverter.convertDtoToEntity(deckDTO);

        // Assert
        assertNull(deck);
    }
}