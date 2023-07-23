package com.universityteam.flashmemorizer.service.impl;

import com.universityteam.flashmemorizer.converter.DeckConverter;
import com.universityteam.flashmemorizer.dto.DeckDTO;
import com.universityteam.flashmemorizer.entity.Deck;
import com.universityteam.flashmemorizer.exception.DeckNotFoundException;
import com.universityteam.flashmemorizer.repository.DeckRepository;
import com.universityteam.flashmemorizer.service.DeckService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeckServiceImpl implements DeckService {

    private final Logger log = LoggerFactory.getLogger(DeckServiceImpl.class);

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
    public boolean delete(Long id) throws DeckNotFoundException {
        Long count = deckRepo.countById(id);
        if (count == null || count == 0)
            throw new DeckNotFoundException("Could not find any decks with Id=" + id);
        try {
            deckRepo.deleteById(id);
            return true;
        } catch (Exception e) {
            log.error(e.getMessage());
            return false;
        }
    }

    @Override
    public DeckDTO update(DeckDTO deckDTO) throws DeckNotFoundException {
        Deck deck = deckRepo
                .findById(deckDTO.getId())
                .orElseThrow(() -> new DeckNotFoundException("Could not find any decks with Id=" + deckDTO.getId()));
        try {
            Deck updated = deckRepo.save(deck);
            return deckConverter.convertEntityToDto(updated);
        } catch (Exception e) {
            log.error(e.getMessage());
            return null;
        }
    }

    @Override
    public List<DeckDTO> getByUser(Long userId) throws DeckNotFoundException {
        List<Deck> decks = deckRepo.findByUserId(userId);
        if (decks == null || decks.isEmpty())
            throw new DeckNotFoundException("Could not find any decks with userId=" + userId);
        return deckConverter.convertEntityToDto(decks);
    }

    @Override
    public DeckDTO getById(Long id) throws DeckNotFoundException {
        Deck deck = deckRepo
                .findById(id)
                .orElseThrow(() -> new DeckNotFoundException("Could not find any decks with Id=" + id));
        return deckConverter.convertEntityToDto(deck);
    }
}
