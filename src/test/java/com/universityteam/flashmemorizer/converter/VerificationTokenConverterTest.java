package com.universityteam.flashmemorizer.converter;

import com.universityteam.flashmemorizer.config.AppConfig;
import com.universityteam.flashmemorizer.dto.UserDTO;
import com.universityteam.flashmemorizer.dto.VerificationTokenDTO;
import com.universityteam.flashmemorizer.entity.User;
import com.universityteam.flashmemorizer.entity.VerificationToken;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = AppConfig.class)
@ContextConfiguration(classes = VerificationTokenConverter.class)
class VerificationTokenConverterTest {

    @Autowired
    private VerificationTokenConverter tokenConverter;

    @Test
    public void testConvertEntityToDto() {
        // Arrange
        VerificationToken token = VerificationToken.builder()
                .id(1L)
                .token("as;dfkja;sdlfj")
                .expirationTime(new Date())
                .user(User.builder().id(1L).build())
                .build();

        // Act
        VerificationTokenDTO tokenDTO = tokenConverter.convertEntityToDto(token);

        // Assert
        assertEquals(tokenDTO.getId(), token.getId());
        assertEquals(tokenDTO.getToken(), token.getToken());
        assertEquals(tokenDTO.getExpirationTime(), token.getExpirationTime());
        assertEquals(tokenDTO.getUser().getId(), token.getUser().getId());
    }

    @Test
    public void testConvertEntityToDtoNullCase() {
        // Arrange
        VerificationToken token = null;

        // Act
        VerificationTokenDTO tokenDTO = tokenConverter.convertEntityToDto(token);

        // Assert
        assertNull(tokenDTO);
    }

    @Test
    public void testConvertDtoToEntity() {
        // Arrange
        VerificationTokenDTO tokenDTO = VerificationTokenDTO.builder()
                .id(1L)
                .token("as;dfkja;sdlfj")
                .expirationTime(new Date())
                .user(UserDTO.builder().id(1L).build())
                .build();

        // Act
        VerificationToken token = tokenConverter.convertDtoToEntity(tokenDTO);

        // Assert
        assertEquals(tokenDTO.getId(), token.getId());
        assertEquals(tokenDTO.getToken(), token.getToken());
        assertEquals(tokenDTO.getExpirationTime(), token.getExpirationTime());
        assertEquals(tokenDTO.getUser().getId(), token.getUser().getId());
    }

    @Test
    public void testConvertDtoToEntityNullCase() {
        // Arrange
        VerificationTokenDTO tokenDTO = null;

        // Act
        VerificationToken token = tokenConverter.convertDtoToEntity(tokenDTO);

        // Assert
        assertNull(token);
    }
}