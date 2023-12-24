package com.uniteam.flashmemorizer.service.card;


import com.uniteam.flashmemorizer.dto.CardDTO;

import java.util.List;

public interface CardService {
    CardDTO add(CardDTO cardDTO);
    boolean delete(Long id);
    CardDTO update(CardDTO cardDTO);
    List<CardDTO> getByDeckId(Long deckId);
    CardDTO getById(Long id);
    Integer countByDeckId(Long deckId);
}
