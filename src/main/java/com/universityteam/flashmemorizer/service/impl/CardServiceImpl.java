package com.universityteam.flashmemorizer.service.impl;

import com.universityteam.flashmemorizer.converter.CardConverter;
import com.universityteam.flashmemorizer.dto.CardDTO;
import com.universityteam.flashmemorizer.entity.Card;
import com.universityteam.flashmemorizer.entity.Deck;
import com.universityteam.flashmemorizer.repository.CardRepository;
import com.universityteam.flashmemorizer.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CardServiceImpl implements CardService {

    @Autowired
    private CardRepository cardRepo;

    @Autowired
    private CardConverter cardConverter;

    @Override
    public CardDTO add(CardDTO cardDTO) {
        Card card = cardConverter.convertDtoToEntity(cardDTO);
        try {
            Card added = cardRepo.save(card);
            return cardConverter.convertEntityToDto(added);
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
            cardRepo.deleteById(id);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean delete(CardDTO cardDTO) {
        Card card = cardConverter.convertDtoToEntity(cardDTO);
        try {
            cardRepo.delete(card);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public CardDTO update(CardDTO cardDTO) {
        Optional<Card> optCard = cardRepo.findById(cardDTO.getId());
        if (optCard.isEmpty()) return null;
        try {
            Card updated = cardRepo.save(optCard.get());
            return cardConverter.convertEntityToDto(updated);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<CardDTO> saveInOnlyOneDeck(Long deckId, List<CardDTO> cardDTOS) {
        List<Card> exitsCards = cardRepo.findByDeckId(deckId);

        for (CardDTO card : cardDTOS) {
            saveInOnlyOneDeck(exitsCards.get(0).getDeck(), card);
        }
        return new ArrayList<>();
    }

    private CardDTO saveInOnlyOneDeck(Deck deck, CardDTO cardDTO) {
        Optional<Card> optCard = cardRepo.findById(cardDTO.getId());
        Card card;
        if (optCard.isPresent()) {
            card = optCard.get();
            card.setTerm(cardDTO.getTerm());
            card.setDesc(cardDTO.getDesc());
            card.setModified(cardDTO.getModified());
        } else {
            card = new Card();
            card.setTerm(cardDTO.getTerm());
            card.setDesc(cardDTO.getDesc());
            card.setCreation(cardDTO.getCreation());
            card.setModified(cardDTO.getModified());
            card.setDeck(deck);
        }
        Card saved = cardRepo.save(card);
        return cardConverter.convertEntityToDto(saved);
    }

    @Override
    public List<CardDTO> getByDeckId(Long deckId) {
        List<Card> cards = cardRepo.findByDeckId(deckId);
        return cardConverter.convertEntityToDto(cards);
    }
}
