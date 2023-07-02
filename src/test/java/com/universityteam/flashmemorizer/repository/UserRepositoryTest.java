package com.universityteam.flashmemorizer.repository;

import com.universityteam.flashmemorizer.entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class UserRepositoryTest {
    @Autowired
    private UserRepository userRepo;

    private User exitsUser;

    @BeforeEach
    public void setupBeforeEachTest() {
        exitsUser = userRepo.save(createUser());
    }

    @Test
    @Order(1)
    @Rollback(false)
    public void testSaveSuccess() {
        // Act
        User user = createUser();
        User savedUser = userRepo.save(user);

        // Assert
        assertNotNull(savedUser.getId());
    }

    private User createUser() {
        User user = User.builder()
                .username("test")
                .pass("test")
                .email("test123@gmail.com")
                .fullName("Hoang Long")
                .registration(new Date(2023, 1, 1))
                .lastLogin(new Date())
                .build();
        return userRepo.save(user);
    }

    @Test
    @Order(2)
    @Rollback(value = false)
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
    public void testFindByIdExits() {
        // Act
        User actualUser = userRepo.findById(exitsUser.getId()).get();

        // Assert
        assertNotNull(actualUser);
    }
}