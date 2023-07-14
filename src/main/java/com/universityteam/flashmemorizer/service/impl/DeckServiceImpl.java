package com.universityteam.flashmemorizer.service.impl;

import com.universityteam.flashmemorizer.converter.DeckConverter;
import com.universityteam.flashmemorizer.dto.DeckDTO;
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

    @Autowired
    private DeckConverter deckConverter;

    @Override
    public DeckDTO add(DeckDTO deckDTO) {
        Deck deck = deckConverter.convertDtoToEntity(deckDTO);
        try {
             Deck added = deckRepo.save(deck);
             return deckConverter.convertEntityToDto(added);
        } catch (Exception e) {
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
            return false;
        }
    }

    @Override
    public DeckDTO update(DeckDTO deck) {
        Optional<Deck> optDeck = deckRepo.findById(deck.getId());
        if (optDeck.isEmpty()) return null;
        try {
            Deck updated = deckRepo.save(optDeck.get());
            return deckConverter.convertEntityToDto(updated);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<DeckDTO> getByUser(Long userId) {
        List<Deck> decks = deckRepo.findByUserId(userId);
        return deckConverter.convertEntityToDto(decks);
    }

    @Override
    public DeckDTO getById(Long id) {
        Optional<Deck> optDeck = deckRepo.findById(id);
        if (optDeck.isEmpty())
            return null;
        return deckConverter.convertEntityToDto(optDeck.get());
    }
}
