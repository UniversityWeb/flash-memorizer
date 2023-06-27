package com.universityteam.flashmemorizer.service;

import com.universityteam.flashmemorizer.entity.Card;

import java.util.List;

public interface CardService {
    Card add(Card card);
    boolean delete(Long id);
    Card update(Card card);
    List<Card> getByDeck(Long deckId);
}
