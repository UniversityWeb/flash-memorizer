package com.uniteam.flashmemorizer.repository;

import com.uniteam.flashmemorizer.entity.Card;
import com.uniteam.flashmemorizer.entity.Deck;
import com.uniteam.flashmemorizer.entity.User;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class CardRepositoryTest {

    @Autowired
    private CardRepository cardRepo;

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private DeckRepository deckRepo;

    private User exitsUser;

    private Deck exitsDeck;

    private Card exitsCard;

    @BeforeEach
    public void setupBeforeEachTest() {
        exitsUser = userRepo.save(User.builder()
                .username("test")
                .pass("test")
                .email("test123@gmail.com")
                .fullName("Hoang Long")
                .build());
        exitsDeck = deckRepo.save(Deck.builder()
                .name("Writing set")
                .desc("Some vocabulary for task 1")
                .user(exitsUser)
                .build());
        exitsCard = cardRepo.save( createCard(exitsDeck) );
    }

    @Test
    @Order(0)
    public void testSaveFailure() {
        // Arrange
        Card card = createCard(new Deck());
        Card savedCard;

        // Act
        try {
            savedCard = cardRepo.save(card);
        } catch (Exception e) {
            savedCard = null;
        }

        // Assert
        assertNull(savedCard);
    }

    @Test
    @Order(1)
    public void testSaveSuccess() {
        // Act
        Card card = createCard(exitsDeck);
        Card savedCard = cardRepo.save(card);

        // Assert
        assertNotNull(savedCard.getId());
    }

    private Card createCard(Deck deck) {
        return Card.builder()
                .term("increased sharply")
                .desc("The same meaning: rise quickly")
                .deck(deck)
                .build();
    }

    @Test
    @Order(2)
    public void testUpdateSuccess() {
        // Act
        exitsCard.setTerm("Exchanged");
        exitsCard.setDesc("Changed");

        Card cardActual = cardRepo.save(exitsCard);

        // Assert
        assertEquals(cardActual.getTerm(), exitsCard.getTerm());
        assertEquals(cardActual.getDesc(), exitsCard.getDesc());
    }

    @Test
    @Order(3)
    public void testDelete() {
        // Act
        Optional<Card> optBeforeDelete = cardRepo.findById(exitsCard.getId());
        cardRepo.delete(exitsCard);
        Optional<Card> optAfterDelete = cardRepo.findById(exitsCard.getId());

        // Assert
        assertTrue(optBeforeDelete.isPresent());
        assertTrue(optAfterDelete.isEmpty());
    }

    @Test
    public void testFindByDeckIdExits() {
        // Act
        List<Card> actualCards = cardRepo.findByDeckId(exitsDeck.getId());

        // Assert
        assertEquals(1, actualCards.size());
    }
}