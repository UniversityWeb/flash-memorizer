package com.universityteam.flashmemorizer.converter;

import com.universityteam.flashmemorizer.config.AppConfig;
import com.universityteam.flashmemorizer.dto.SharedDeckDTO;
import com.universityteam.flashmemorizer.entity.SharedDeck;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@SpringBootTest(classes = AppConfig.class)
@ContextConfiguration(classes = SharedDeckConverter.class)
class SharedDeckConverterTest {
    @Autowired
    private SharedDeckConverter shardConverter;

    @Test
    public void testConvertEntityToDto() {
        // Arrange
        SharedDeck sharedDeck = SharedDeck.builder()
                .id(1L)
                .sender(null)
                .recipient(null)
                .deck(null)
                .creation(new Date())
                .build();

        // Act
        SharedDeckDTO sharedDeckDTO = shardConverter.convertEntityToDto(sharedDeck);

        // Assert
        assertEquals(sharedDeckDTO.getId(), sharedDeck.getId());
        assertEquals(sharedDeckDTO.getSender(), sharedDeck.getSender());
        assertEquals(sharedDeckDTO.getRecipient(), sharedDeck.getRecipient());
        assertEquals(sharedDeckDTO.getDeck(), sharedDeck.getDeck());
        assertEquals(sharedDeckDTO.getCreation(), sharedDeck.getCreation());
    }

    @Test
    public void testConvertEntityToDtoNullCase() {
        // Arrange
        SharedDeck sharedDeck = null;

        // Act
        SharedDeckDTO sharedDeckDTO = shardConverter.convertEntityToDto(sharedDeck);

        // Assert
        assertNull(sharedDeckDTO);
    }

    @Test
    public void testConvertDtoToEntity() {
        // Arrange
        SharedDeckDTO sharedDeckDTO = SharedDeckDTO.builder()
                .id(1L)
                .sender(null)
                .recipient(null)
                .deck(null)
                .creation(new Date())
                .build();

        // Act
        SharedDeck sharedDeck = shardConverter.convertDtoToEntity(sharedDeckDTO);

        // Assert
        assertEquals(sharedDeckDTO.getId(), sharedDeck.getId());
        assertEquals(sharedDeckDTO.getSender(), sharedDeck.getSender());
        assertEquals(sharedDeckDTO.getRecipient(), sharedDeck.getRecipient());
        assertEquals(sharedDeckDTO.getDeck(), sharedDeck.getDeck());
        assertEquals(sharedDeckDTO.getCreation(), sharedDeck.getCreation());
    }

    @Test
    public void testConvertDtoToEntityNullCase() {
        // Arrange
        SharedDeckDTO sharedDeckDTO = null;

        // Act
        SharedDeck sharedDeck = shardConverter.convertDtoToEntity(sharedDeckDTO);

        // Assert
        assertNull(sharedDeck);
    }
}