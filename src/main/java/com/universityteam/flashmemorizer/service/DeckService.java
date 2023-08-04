package com.universityteam.flashmemorizer.service;

import com.universityteam.flashmemorizer.dto.DeckDTO;
import com.universityteam.flashmemorizer.exception.DeckNotFoundException;

import java.util.List;

public interface DeckService {
    DeckDTO add(DeckDTO deckDTO);
    boolean delete(Long id) throws DeckNotFoundException;
    DeckDTO update(DeckDTO deckDTO) throws DeckNotFoundException;
    List<DeckDTO> getByUser(Long userId) throws DeckNotFoundException;
    DeckDTO getById(Long id) throws DeckNotFoundException;
}
