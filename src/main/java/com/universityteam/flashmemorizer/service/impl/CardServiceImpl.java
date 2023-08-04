package com.universityteam.flashmemorizer.service.impl;

import com.universityteam.flashmemorizer.converter.CardConverter;
import com.universityteam.flashmemorizer.dto.CardDTO;
import com.universityteam.flashmemorizer.entity.Card;
import com.universityteam.flashmemorizer.exception.CardNotFoundException;
import com.universityteam.flashmemorizer.repository.CardRepository;
import com.universityteam.flashmemorizer.service.CardService;
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
    public CardDTO update(CardDTO cardDTO) throws CardNotFoundException {
        Card card = cardRepo
                .findById(cardDTO.getId())
                .orElseThrow(() -> new CardNotFoundException("Could not find any cards with Id=" + cardDTO.getId()));

        card.setTerm( cardDTO.getTerm() );
        card.setDesc( cardDTO.getDesc() );

        try {
            Card updated = cardRepo.save(card);
            return cardConverter.convertEntityToDto(updated);
        } catch (Exception e) {
            log.error(e.getMessage());
            return null;
        }
    }

    @Override
    public List<CardDTO> getByDeckId(Long deckId) {
        List<Card> cards = cardRepo.findByDeckId(deckId);
        return cardConverter.convertEntityToDto(cards);
    }

    @Override
    public CardDTO getById(Long id) throws CardNotFoundException {
        Card card = cardRepo
                .findById(id)
                .orElseThrow(() -> new CardNotFoundException("Could not find any decks with Id=" + id));
        return cardConverter.convertEntityToDto(card);
    }

    @Override
    public Integer countByDeckId(Long deckId) {
        return cardRepo.countByDeckId(deckId);
    }
}
