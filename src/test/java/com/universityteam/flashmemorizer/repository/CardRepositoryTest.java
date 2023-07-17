package com.universityteam.flashmemorizer.repository;

import com.universityteam.flashmemorizer.entity.Card;
import com.universityteam.flashmemorizer.entity.Deck;
import com.universityteam.flashmemorizer.entity.User;
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
        exitsUser = addNewUser();
        exitsDeck = addNewDeck(exitsUser);
        exitsCard = cardRepo.save( createCard(exitsUser, exitsDeck) );
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

    private Deck addNewDeck(User user) {
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
    @Order(0)
    public void testSaveFailure() {
        // Arrange
        Card card = createCard(new User(), new Deck());
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
    @Rollback(false)
    public void testSaveSuccess() {
        // Act
        Card card = createCard(exitsUser, exitsDeck);
        Card savedCard = cardRepo.save(card);

        // Assert
        assertNotNull(savedCard.getId());
    }

    private Card createCard(User user, Deck deck) {
        return Card.builder()
                .term("increased sharply")
                .desc("The same meaning: rise quickly")
                .creation(new Date())
                .modified(new Date())
                .deck(deck)
                .build();
    }

    @Test
    @Order(2)
    @Rollback(value = false)
    public void testUpdateSuccess() {
        // Act
        exitsCard.setTerm("Exchanged");
        exitsCard.setDesc("Changed");
        exitsCard.setModified(new Date(2023, 2, 2));

        Card cardActual = cardRepo.save(exitsCard);

        // Assert
        assertEquals(cardActual.getTerm(), exitsCard.getTerm());
        assertEquals(cardActual.getDesc(), exitsCard.getDesc());
        assertEquals(cardActual.getModified(), exitsCard.getModified());
    }

    @Test
    @Order(3)
    @Rollback
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