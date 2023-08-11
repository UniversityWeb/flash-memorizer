package com.universityteam.flashmemorizer.service;

import com.universityteam.flashmemorizer.dto.CardDTO;
import com.universityteam.flashmemorizer.exception.CardNotFoundException;

import java.util.ArrayList;
import java.util.List;

public interface CardService {
    CardDTO add(CardDTO cardDTO);
    boolean delete(Long id) throws CardNotFoundException;
    CardDTO update(CardDTO cardDTO) throws CardNotFoundException;
    List<CardDTO> getByDeckId(Long deckId);
    CardDTO getById(Long id) throws CardNotFoundException;
    Integer countByDeckId(Long deckId);

    List<CardDTO> saveAll(List<CardDTO> cards);
}
