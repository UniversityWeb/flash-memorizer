package com.universityteam.flashmemorizer.controller;

import com.universityteam.flashmemorizer.dto.CardDTO;
import com.universityteam.flashmemorizer.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/cards")
public class CardController {

    @Autowired
    private CardService cardService;

    @GetMapping("/{deckId}")
    public String getByDeckId(@PathVariable Long deckId, Model m) {
        List<CardDTO> cards = cardService.getByDeckId(deckId);
        m.addAttribute("cards", cards);
        return "review-card";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam Long cardId, @RequestParam Long deckId) {
        cardService.delete(cardId);
        return "redirect:/deck/edit/" + deckId;
    }
}
