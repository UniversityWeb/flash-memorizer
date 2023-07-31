package com.universityteam.flashmemorizer.service;

import com.universityteam.flashmemorizer.dto.CardDTO;
import com.universityteam.flashmemorizer.exception.CardNotFoundException;

import java.util.List;

public interface CardService {
    CardDTO add(CardDTO cardDTO);
    boolean delete(Long id) throws CardNotFoundException;
    List<CardDTO> saveCardsByDeck(Long deckId, List<CardDTO> afterChanged) throws CardNotFoundException;
    List<CardDTO> getByDeckId(Long deckId);
}
