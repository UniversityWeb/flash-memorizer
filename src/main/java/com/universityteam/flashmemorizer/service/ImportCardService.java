package com.universityteam.flashmemorizer.service;

import com.universityteam.flashmemorizer.dto.CardDTO;

import java.util.List;

public interface ImportCardService {
    List<CardDTO> importCards(String filePath);
    List<CardDTO> importCards(String content, String betweenTermAndDesc, String betweenCards);
}
