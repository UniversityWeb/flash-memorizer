package com.universityteam.flashmemorizer.service;

import com.universityteam.flashmemorizer.dto.CardDTO;

import java.util.List;

public interface CardService {
    CardDTO add(CardDTO cardDTO);
    boolean delete(Long id);
    boolean delete(CardDTO cardDTO);
    CardDTO update(CardDTO cardDTO);
    List<CardDTO> addOrUpdate(List<CardDTO> cardDTOs);
    List<CardDTO> getByDeckId(Long deckId);
}
