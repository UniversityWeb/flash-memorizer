package com.uniteam.flashmemorizer.form;

import com.uniteam.flashmemorizer.dto.CardDTO;
import com.uniteam.flashmemorizer.dto.DeckDTO;
import lombok.Data;

import java.util.ArrayList;

@Data
public class DeckForm {
    private DeckDTO deck = new DeckDTO();
    private ArrayList<CardDTO> cards = new ArrayList<>();
}
