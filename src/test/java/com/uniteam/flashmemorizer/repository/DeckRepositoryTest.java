package com.uniteam.flashmemorizer.repository;

import com.uniteam.flashmemorizer.entity.Deck;
import com.uniteam.flashmemorizer.entity.User;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class DeckRepositoryTest {

    @Autowired
    private DeckRepository deckRepo;

    @Autowired
    private UserRepository userRepo;

    private User exitsUser;

    private Deck exitsDeck;

    @BeforeEach
    public void setupBeforeEachTest() {
        exitsUser = userRepo.save(
                User.builder()
                        .username("test")
                        .pass("test")
                        .email("test123@gmail.com")
                        .fullName("Hoang Long")
                        .build());
        exitsDeck = deckRepo.save(createDeck(exitsUser));
    }

    @Test
    @Order(1)
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
                .user(user)
                .build();
        return deckRepo.save(deck);
    }

    @Test
    @Order(2)
    public void testUpdateSuccess() {
        // Act
        exitsDeck.setDesc("Changed");

        Deck deckActual = deckRepo.save(exitsDeck);

        // Assert
        assertEquals(deckActual.getDesc(), exitsDeck.getDesc());
    }

    @Test
    @Order(3)
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