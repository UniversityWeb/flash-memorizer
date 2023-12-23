package com.uniteam.flashmemorizer.repository;

import com.uniteam.flashmemorizer.entity.User;
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

        User userActual = userRepo.save(exitsUser);

        // Assert
        assertEquals(userActual.getUsername(), exitsUser.getUsername());
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