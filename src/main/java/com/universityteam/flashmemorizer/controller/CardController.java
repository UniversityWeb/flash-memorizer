package com.universityteam.flashmemorizer.controller;

import com.universityteam.flashmemorizer.dto.CardDTO;
import com.universityteam.flashmemorizer.exception.CardNotFoundException;
import com.universityteam.flashmemorizer.service.CardService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/cards")
public class CardController {

    private final Logger log = LoggerFactory.getLogger(CardController.class);

    @Autowired
    private CardService cardService;

    @GetMapping("/review/{deckId}")
    public String getCardsToReview(@PathVariable Long deckId, Model m) {
        List<CardDTO> cards = cardService.getByDeckId(deckId);
        m.addAttribute("cards", cards);
        return "review-card";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam Long cardId, @RequestParam Long deckId) {
        try {
            cardService.delete(cardId);
        } catch (CardNotFoundException e) {
            log.error(e.getMessage());
        }
        return "redirect:/deck/edit/" + deckId;
    }
}
