package com.universityteam.flashmemorizer.service.impl;

import com.universityteam.flashmemorizer.converter.CardConverter;
import com.universityteam.flashmemorizer.dto.CardDTO;
import com.universityteam.flashmemorizer.entity.Card;
import com.universityteam.flashmemorizer.exception.CardNotFoundException;
import com.universityteam.flashmemorizer.repository.CardRepository;
import com.universityteam.flashmemorizer.service.CardService;
import com.universityteam.flashmemorizer.utility.Utils;
import lombok.NonNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CardServiceImpl implements CardService {

    private final Logger log = LoggerFactory.getLogger(CardServiceImpl.class);

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
            log.error(e.getMessage());
            return null;
        }
    }

    @Override
    public boolean delete(Long id) throws CardNotFoundException {
        Long count = cardRepo.countById(id);
        if (count == null || count == 0)
            throw new CardNotFoundException("Could not find any cards with Id=" + id);
        try {
            cardRepo.deleteById(id);
            return true;
        } catch (Exception e) {
            log.error(e.getMessage());
            return false;
        }
    }

    @Override
    public List<CardDTO> saveCardsByDeck(Long deckId, @NonNull List<CardDTO> afterChanged) throws CardNotFoundException {
        List<CardDTO> beforeChanged = getByDeckId(deckId);
        if (beforeChanged == null) {
            throw new IllegalArgumentException("Invalid deckId: " + deckId);
        }
        return saveWithBeforeAndAfter(deckId, beforeChanged, afterChanged);
    }

    private List<CardDTO> saveWithBeforeAndAfter(
            Long deckId,
            @NonNull List<CardDTO> beforeChanged,
            @NonNull List<CardDTO> afterChanged
    ) throws CardNotFoundException {
        List<CardDTO> addCards = Utils.getElementsInAWithoutB(afterChanged, beforeChanged);
        addAll(addCards);

        List<CardDTO> deleteCards = Utils.getElementsInAWithoutB(beforeChanged, afterChanged);
        deleteAll(deleteCards);

        return getByDeckId(deckId);
    }

    private void addAll(List<CardDTO> cards) {
        cards.forEach(this::add);
    }

    private void deleteAll(List<CardDTO> cards) throws CardNotFoundException {
        for (CardDTO card : cards) {
            delete(card.getId());
        }
    }

    @Override
    public List<CardDTO> getByDeckId(Long deckId) {
        List<Card> cards = cardRepo.findByDeckId(deckId);
        return cardConverter.convertEntityToDto(cards);
    }
}
