package com.universityteam.flashmemorizer.service.impl;

import com.universityteam.flashmemorizer.entity.Deck;
import com.universityteam.flashmemorizer.repository.DeckRepository;
import com.universityteam.flashmemorizer.service.DeckService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DeckServiceImpl implements DeckService {
    @Autowired
    private DeckRepository deckRepo;

    @Override
    public Deck add(Deck deck)
    {
        try {
            return deckRepo.save(deck);
        } catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }
    @Override
    public boolean delete(Long id) {
        Optional<Deck> optDeck = deckRepo.findById(id);
        if (optDeck.isEmpty()) return false;
        try {
            deckRepo.deleteById(id);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Deck update(Deck deck) {
        Optional<Deck> optDeck = deckRepo.findById(deck.getId());
        if (optDeck.isEmpty()) return null;
        try {
            return deckRepo.save(deck);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Deck> getByUser(Long userId) {
        return deckRepo.findByUserId(userId);
    }
}
