package com.universityteam.flashmemorizer.service.impl;

import com.universityteam.flashmemorizer.entity.Card;
import com.universityteam.flashmemorizer.repository.CardRepository;
import com.universityteam.flashmemorizer.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CardServiceImpl implements CardService {

    @Autowired
    private CardRepository cardRepo;

    @Override
    public Card add(Card card) {
        try {
            return cardRepo.save(card);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean delete(Long id) {
        Optional<Card> optCard = cardRepo.findById(id);
        if (optCard.isEmpty()) return false;
        try {
            Card card = optCard.get();
            cardRepo.delete(card);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Card update(Card card) {
        Optional<Card> optCard = cardRepo.findById(card.getId());
        if (optCard.isEmpty()) return null;
        try {
            return cardRepo.save(card);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Card> getByDeckId(Long deckId) {
        return cardRepo.findByDeckId(deckId);
    }
}
