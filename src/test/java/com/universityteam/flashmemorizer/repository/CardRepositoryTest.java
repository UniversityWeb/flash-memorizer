package com.universityteam.flashmemorizer.repository;

import com.universityteam.flashmemorizer.entity.Card;
import com.universityteam.flashmemorizer.entity.Deck;
import com.universityteam.flashmemorizer.entity.User;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Description;
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

    @Test
    @Order(0)
    @Description("foreign key constraint error")
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
        // Arrange
        User user = createUser();
        Deck deck = createDeck();

        // Act
        Card card = createCard(user, deck);
        Card savedCard = cardRepo.save(card);

        // Assert
        assertNotNull(savedCard.getId());
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

    private Deck createDeck() {
        Deck deck = Deck.builder()
                .name("Writing set")
                .desc("Some vocabulary for task 1")
                .creation(new Date(2023, 1, 1, 1, 1, 1))
                .modified(new Date())
                .build();
        return deckRepo.save(deck);
    }

    private Card createCard(User user, Deck deck) {
        return Card.builder()
                .term("increased sharply")
                .desc("The same meaning: rise quickly")
                .creation(new Date())
                .modified(new Date())
                .user(user)
                .deck(deck)
                .build();
    }

    @Test
    @Order(2)
    @Rollback(value = false)
    public void testUpdateSuccess() {
        // Arrange
        Long exitId = 1L;
        Optional<Card> optCard = cardRepo.findById(exitId);
        if (optCard.isEmpty()) {
            assertTrue(false);
            return;
        }
        Card cardExpected = optCard.get();

        // Act
        cardExpected.setTerm("Exchanged");
        cardExpected.setDesc("Changed");
        cardExpected.setModified(new Date(2023, 2, 2));

        Card cardActual = cardRepo.save(cardExpected);

        // Assert
        assertEquals(cardActual.getTerm(), cardExpected.getTerm());
        assertEquals(cardActual.getDesc(), cardExpected.getDesc());
        assertEquals(cardActual.getModified(), cardExpected.getModified());
    }

    @Test
    @Order(3)
    @Rollback(value = false)
    public void testDelete() {
        // Arrange
        Long exitId = 1L;
        Optional<Card> optBeforeDelete = cardRepo.findById(exitId);

        // Act
        cardRepo.delete(optBeforeDelete.get());
        Optional<Card> optAfterDelete = cardRepo.findById(exitId);

        // Assert
        assertTrue(optBeforeDelete.isPresent());
        assertTrue(optAfterDelete.isEmpty());
    }

    @Test
    public void testFindByDeckIdExits() {
        // Arrange
        Long deckId = 1L;

        // Act
        List<Card> actualCards = cardRepo.findByDeckId(deckId);

        // Assert
        assertNotNull(actualCards);
    }
}