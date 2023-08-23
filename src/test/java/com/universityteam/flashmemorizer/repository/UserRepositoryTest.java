package com.universityteam.flashmemorizer.repository;

import com.universityteam.flashmemorizer.entity.User;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class UserRepositoryTest {
    @Autowired
    private UserRepository userRepo;

    private User exitsUser;

    @BeforeEach
    public void setupBeforeEachTest() {
        exitsUser = userRepo.save(
                User.builder()
                        .username("test")
                        .pass("test")
                        .email("test123@gmail.com")
                        .fullName("Hoang Long")
                        .registration(new Date(2023, 1, 1))
                        .lastLogin(new Date())
                        .build());
    }

    @Test
    @Order(1)
    public void testSaveSuccess() {
        // Act
        User user = User.builder()
                .username("test123")
                .pass("test1")
                .email("test345@gmail.com")
                .fullName("Hoang Dieu")
                .registration(new Date(2023, 1, 1))
                .lastLogin(new Date())
                .build();
        User savedUser = userRepo.save(user);

        // Assert
        assertNotNull(savedUser.getId());
    }

    @Test
    @Order(2)
    public void testUpdateSuccess() {
        // Act
        exitsUser.setUsername("Changed");
        exitsUser.setLastLogin(new Date(2023, 2, 2));

        User userActual = userRepo.save(exitsUser);

        // Assert
        assertEquals(userActual.getUsername(), exitsUser.getUsername());
        assertEquals(userActual.getLastLogin(), exitsUser.getLastLogin());
    }

    @Test
    @Order(3)
    public void testFindByIdExits() {
        // Act
        User actualUser = userRepo.findById(exitsUser.getId()).get();

        // Assert
        assertNotNull(actualUser);
    }
}