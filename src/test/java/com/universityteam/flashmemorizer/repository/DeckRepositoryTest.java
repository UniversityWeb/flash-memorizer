package com.universityteam.flashmemorizer.repository;

import com.universityteam.flashmemorizer.entity.Deck;
import com.universityteam.flashmemorizer.entity.User;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class DeckRepositoryTest {
    @Autowired
    private DeckRepository deckRepo;

    @Autowired
    private UserRepository userRepo;

    @Test
    @Order(1)
    @Rollback(false)
    public void testSaveSuccess() {
        // Arrange
        User user = createUser();

        // Act
        Deck deck = createDeck(user);
        Deck savedDeck = deckRepo.save(deck);

        // Assert
        assertNotNull(savedDeck.getId());
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

    private Deck createDeck(User user) {
        Deck deck = Deck.builder()
                .name("Writing set")
                .desc("Some vocabulary for task 1")
                .creation(new Date(2023, 1, 1, 1, 1, 1))
                .modified(new Date())
                .user(user)
                .build();
        return deckRepo.save(deck);
    }

    @Test
    @Order(2)
    @Rollback(value = false)
    public void testUpdateSuccess() {
        // Arrange
        Long exitId = 1L;
        Optional<Deck> optDeck = deckRepo.findById(exitId);
        if (optDeck.isEmpty()) {
            assertTrue(false);
            return;
        }
        Deck deckExpected = optDeck.get();

        // Act
        deckExpected.setDesc("Changed");
        deckExpected.setModified(new Date(2023, 2, 2));

        Deck deckActual = deckRepo.save(deckExpected);

        // Assert
        assertEquals(deckActual.getDesc(), deckExpected.getDesc());
        assertEquals(deckActual.getModified(), deckExpected.getModified());
    }

    @Test
    @Order(3)
    @Rollback(value = false)
    public void testDelete() {
        // Arrange
        Long exitId = 1L;
        Optional<Deck> optBeforeDelete = deckRepo.findById(exitId);

        // Act
        deckRepo.delete(optBeforeDelete.get());
        Optional<Deck> optAfterDelete = deckRepo.findById(exitId);

        // Assert
        assertTrue(optBeforeDelete.isPresent());
        assertTrue(optAfterDelete.isEmpty());
    }

    @Test
    public void testFindByUserIdExits() {
        // Arrange
        Long userId = 1L;

        // Act
        List<Deck> actualDecks = deckRepo.findByUserId(userId);

        // Assert
        Assertions.assertThat(actualDecks.size()).isGreaterThan(0);
    }
}