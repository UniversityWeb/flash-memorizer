package com.uniteam.flashmemorizer.service.card;

import com.uniteam.flashmemorizer.mapper.CardMapper;
import com.uniteam.flashmemorizer.dto.CardDTO;
import com.uniteam.flashmemorizer.entity.Card;
import com.uniteam.flashmemorizer.exception.CardNotFoundException;
import com.uniteam.flashmemorizer.repository.CardRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CardServiceImpl implements CardService {

    private final Logger log = LogManager.getLogger(CardServiceImpl.class);

    @Autowired
    private CardRepository cardRepo;

    @Autowired
    private CardMapper cardMapper;

    @Override
    public CardDTO add(CardDTO cardDTO) {
        Card card = cardMapper.toEntity(cardDTO);
        try {
            Card added = cardRepo.save(card);
            return cardMapper.toDto(added);
        } catch (Exception e) {
            log.error(e.getMessage());
            return null;
        }
    }

    @Override
    public boolean delete(Long id) {
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
    public CardDTO update(CardDTO cardDTO) {
        Card card = cardRepo
                .findById(cardDTO.getId())
                .orElseThrow(() -> new CardNotFoundException("Could not find any cards with Id=" + cardDTO.getId()));

        card.setTerm( cardDTO.getTerm() );
        card.setDesc( cardDTO.getDesc() );

        try {
            Card updated = cardRepo.save(card);
            return cardMapper.toDto(updated);
        } catch (Exception e) {
            log.error(e.getMessage());
            return null;
        }
    }

    @Override
    public List<CardDTO> getByDeckId(Long deckId) {
        List<Card> cards = cardRepo.findByDeckId(deckId);
        return cardMapper.toDto(cards);
    }

    @Override
    public CardDTO getById(Long id) {
        Card card = cardRepo
                .findById(id)
                .orElseThrow(() -> new CardNotFoundException("Could not find any decks with Id=" + id));
        return cardMapper.toDto(card);
    }

    @Override
    public Integer countByDeckId(Long deckId) {
        return cardRepo.countByDeckId(deckId);
    }
}
