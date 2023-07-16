package com.universityteam.flashmemorizer.converter;

import com.universityteam.flashmemorizer.config.AppConfig;
import com.universityteam.flashmemorizer.dto.UserCardDTO;
import com.universityteam.flashmemorizer.entity.UserCard;
import com.universityteam.flashmemorizer.enums.ERating;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@SpringBootTest(classes = AppConfig.class)
@ContextConfiguration(classes = UserCardConverter.class)
class UserCardConverterTest {
    @Autowired
    private UserCardConverter userCardConverter;

    @Test
    public void testConvertEntityToDto() {
        // Arrange
        UserCard userCard = UserCard.builder()
                .lastReview(new Date())
                .reviewCount(100L)
                .interval(10L)
                .rating(ERating.HIGH)
                .card(null)
                .user(null)
                .build();

        // Act
        UserCardDTO userCardDTO = userCardConverter.convertEntityToDto(userCard);

        // Assert
        assertEquals(userCardDTO.getLastReview(), userCard.getLastReview());
        assertEquals(userCardDTO.getReviewCount(), userCard.getReviewCount());
        assertEquals(userCardDTO.getInterval(), userCard.getInterval());
        assertEquals(userCardDTO.getRating(), userCard.getRating());
        assertEquals(userCardDTO.getCard(), userCard.getCard());
        assertEquals(userCardDTO.getUser(), userCard.getUser());
    }

    @Test
    public void testConvertEntityToDtoNullCase() {
        // Arrange
        UserCard userCard = null;

        // Act
        UserCardDTO userCardDTO = userCardConverter.convertEntityToDto(userCard);

        // Assert
        assertNull(userCardDTO);
    }

    @Test
    public void testConvertDtoToEntity() {
        // Arrange
        UserCardDTO userCardDTO = UserCardDTO.builder()
                .lastReview(new Date())
                .reviewCount(100L)
                .interval(10L)
                .rating(ERating.HIGH)
                .card(null)
                .user(null)
                .build();

        // Act
        UserCard userCard = userCardConverter.convertDtoToEntity(userCardDTO);

        // Assert
        assertEquals(userCardDTO.getLastReview(), userCard.getLastReview());
        assertEquals(userCardDTO.getReviewCount(), userCard.getReviewCount());
        assertEquals(userCardDTO.getInterval(), userCard.getInterval());
        assertEquals(userCardDTO.getRating(), userCard.getRating());
        assertEquals(userCardDTO.getCard(), userCard.getCard());
        assertEquals(userCardDTO.getUser(), userCard.getUser());
    }

    @Test
    public void testConvertDtoToEntityNullCase() {
        // Arrange
        UserCardDTO userCardDTO = null;

        // Act
        UserCard userCard = userCardConverter.convertDtoToEntity(userCardDTO);

        // Assert
        assertNull(userCard);
    }
}