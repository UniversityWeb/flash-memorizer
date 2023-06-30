package com.universityteam.flashmemorizer.service;

import com.universityteam.flashmemorizer.entity.Deck;

import java.util.List;

public interface DeckService {
    Deck add(Deck deck);
    boolean delete(Long id);
    Deck update(Deck deck);
    List<Deck> getByUser(Long userId);
}
