package com.uniteam.flashmemorizer.service.deck;

import com.uniteam.flashmemorizer.dto.DeckDTO;

import java.util.List;

public interface DeckService {
    DeckDTO add(DeckDTO deckDTO);
    boolean delete(Long id);
    DeckDTO update(DeckDTO deckDTO);
    List<DeckDTO> getByUser(Long userId);
    DeckDTO getById(Long id);
    List<DeckDTO> getByUsername(String username);
}
