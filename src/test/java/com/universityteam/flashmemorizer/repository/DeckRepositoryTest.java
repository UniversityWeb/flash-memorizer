package com.universityteam.flashmemorizer.repository;

import com.universityteam.flashmemorizer.entity.Deck;
import com.universityteam.flashmemorizer.entity.User;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
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

    private User exitsUser;

    private Deck exitsDeck;

    @BeforeEach
    public void setupBeforeEachTest() {
        exitsUser = addNewUser();
        exitsDeck = deckRepo.save(createDeck(exitsUser));
    }

    private User addNewUser() {
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
    @Order(1)
    @Rollback(false)
    public void testSaveSuccess() {
        // Act
        Deck deck = createDeck(exitsUser);
        Deck savedDeck = deckRepo.save(deck);

        // Assert
        assertNotNull(savedDeck.getId());
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
        // Act
        exitsDeck.setDesc("Changed");
        exitsDeck.setModified(new Date(2023, 2, 2));

        Deck deckActual = deckRepo.save(exitsDeck);

        // Assert
        assertEquals(deckActual.getDesc(), exitsDeck.getDesc());
        assertEquals(deckActual.getModified(), exitsDeck.getModified());
    }

    @Test
    @Order(3)
    @Rollback(value = false)
    public void testDelete() {
        // Act
        Optional<Deck> optBeforeDelete = deckRepo.findById(exitsDeck.getId());
        deckRepo.delete(exitsDeck);
        Optional<Deck> optAfterDelete = deckRepo.findById(exitsDeck.getId());

        // Assert
        assertTrue(optBeforeDelete.isPresent());
        assertTrue(optAfterDelete.isEmpty());
    }

    @Test
    @Order(3)
    public void testFindByUserIdExits() {
        // Act
        List<Deck> actualDecks = deckRepo.findByUserId(exitsUser.getId());

        // Assert
        Assertions.assertThat(actualDecks.size()).isGreaterThan(0);
    }
}