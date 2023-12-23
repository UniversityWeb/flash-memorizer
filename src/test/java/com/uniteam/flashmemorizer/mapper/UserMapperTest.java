package com.uniteam.flashmemorizer.mapper;

import com.uniteam.flashmemorizer.config.AppConfig;
import com.uniteam.flashmemorizer.dto.UserDTO;
import com.uniteam.flashmemorizer.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@SpringBootTest(classes = AppConfig.class)
@ContextConfiguration(classes = UserMapper.class)
class UserMapperTest {
    @Autowired
    private UserMapper userMapper;

    @Test
    public void testConvertEntityToDto() {
        // Arrange
        User user = User.builder()
                .id(1L)
                .username("hihihi")
                .pass("hihi1234")
                .email("hihi@gmail.com")
                .fullName("Hi Tran")
                .build();

        // Act
        UserDTO userDTO = userMapper.toDto(user);

        // Assert
        assertEquals(userDTO.getId(), user.getId());
        assertEquals(userDTO.getUsername(), user.getUsername());
        assertEquals(userDTO.getPass(), user.getPass());
        assertEquals(userDTO.getEmail(), user.getEmail());
        assertEquals(userDTO.getFullName(), user.getFullName());
    }

    @Test
    public void testConvertEntityToDtoNullCase() {
        // Arrange
        User user = null;

        // Act
        UserDTO userDTO = userMapper.toDto(user);

        // Assert
        assertNull(userDTO);
    }

    @Test
    public void testConvertDtoToEntity() {
        // Arrange
        UserDTO userDTO = UserDTO.builder()
                .id(1L)
                .username("hihihi")
                .pass("hihi124")
                .email("hihi@gmail.com")
                .fullName("Hi Tran")
                .build();

        // Act
        User user = userMapper.toEntity(userDTO);

        // Assert
        assertEquals(userDTO.getId(), user.getId());
        assertEquals(userDTO.getUsername(), user.getUsername());
        assertEquals(userDTO.getPass(), user.getPass());
        assertEquals(userDTO.getEmail(), user.getEmail());
        assertEquals(userDTO.getFullName(), user.getFullName());
    }

    @Test
    public void testConvertDtoToEntityNullCase() {
        // Arrange
        UserDTO userDTO = null;

        // Act
        User user = userMapper.toEntity(userDTO);

        // Assert
        assertNull(user);
    }
}