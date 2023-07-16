package com.universityteam.flashmemorizer.service;

import com.universityteam.flashmemorizer.dto.DeckDTO;

import java.util.List;

public interface DeckService {
    DeckDTO add(DeckDTO deckDTO);
    boolean delete(Long id);
    DeckDTO update(DeckDTO deckDTO);
    List<DeckDTO> getByUser(Long userId);
    DeckDTO getById(Long id);
}
