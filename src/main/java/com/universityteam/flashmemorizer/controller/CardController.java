package com.universityteam.flashmemorizer.controller;

import com.universityteam.flashmemorizer.entity.Card;
import com.universityteam.flashmemorizer.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/cards")
public class CardController {

    @Autowired
    private CardService cardService;

    @GetMapping("/{deckId}")
    public String getByDeckId(@PathVariable Long deckId, Model m) {
        List<Card> cards = cardService.getByDeckId(deckId);
        m.addAttribute("cards", cards);
        return "";
    }
}
