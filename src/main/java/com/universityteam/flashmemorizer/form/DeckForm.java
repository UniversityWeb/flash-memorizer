package com.universityteam.flashmemorizer.form;

import com.universityteam.flashmemorizer.dto.CardDTO;
import com.universityteam.flashmemorizer.dto.DeckDTO;
import lombok.Data;

import java.util.ArrayList;

@Data
public class DeckForm {
    private DeckDTO deck = new DeckDTO();
    private ArrayList<CardDTO> cards = new ArrayList<>();
}
