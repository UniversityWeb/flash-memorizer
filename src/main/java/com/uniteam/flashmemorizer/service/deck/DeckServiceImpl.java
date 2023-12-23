package com.uniteam.flashmemorizer.service.deck;

import com.uniteam.flashmemorizer.mapper.DeckMapper;
import com.uniteam.flashmemorizer.dto.DeckDTO;
import com.uniteam.flashmemorizer.entity.Deck;
import com.uniteam.flashmemorizer.exception.DeckNotFoundException;
import com.uniteam.flashmemorizer.repository.DeckRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeckServiceImpl implements DeckService {

    private final Logger log = LogManager.getLogger(DeckServiceImpl.class);

    @Autowired
    private DeckRepository deckRepo;

    @Autowired
    private DeckMapper deckMapper;

    @Override
    public DeckDTO add(DeckDTO deckDTO) {
        Deck deck = deckMapper.toEntity(deckDTO);
        try {
             Deck added = deckRepo.save(deck);
             return deckMapper.toDto(added);
        } catch (Exception e) {
            log.error(e.getMessage());
            return null;
        }
    }

    @Override
    public boolean delete(Long id) {
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
    public DeckDTO update(DeckDTO deckDTO) {
        Deck deck = deckRepo
                .findById(deckDTO.getId())
                .orElseThrow(() -> new DeckNotFoundException("Could not find any decks with Id=" + deckDTO.getId()));

        deck.setName( deckDTO.getName() );
        deck.setDesc( deckDTO.getDesc() );

        try {
            Deck updated = deckRepo.save(deck);
            return deckMapper.toDto(updated);
        } catch (Exception e) {
            log.error(e.getMessage());
            return null;
        }
    }

    @Override
    public List<DeckDTO> getByUser(Long userId) {
        List<Deck> decks = deckRepo.findByUserId(userId);
        if (decks == null || decks.isEmpty())
            throw new DeckNotFoundException("Could not find any decks with userId=" + userId);
        return deckMapper.toDto(decks);
    }

    @Override
    public DeckDTO getById(Long id) {
        Deck deck = deckRepo
                .findById(id)
                .orElseThrow(() -> new DeckNotFoundException("Could not find any decks with Id=" + id));
        return deckMapper.toDto(deck);
    }

    @Override
    public List<DeckDTO> getByUsername(String username) {
        List<Deck> decks = deckRepo.findByUsername(username);
        if (decks == null || decks.isEmpty())
            throw new DeckNotFoundException("Could not find any decks with username=" + username);
        return deckMapper.toDto(decks);
    }
}
