package com.universityteam.flashmemorizer.controller;

import com.universityteam.flashmemorizer.dto.CardDTO;
import com.universityteam.flashmemorizer.dto.DeckDTO;
import com.universityteam.flashmemorizer.exception.CardNotFoundException;
import com.universityteam.flashmemorizer.service.CardService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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

    @GetMapping("/edit")
    public String getCardsToEdit(@RequestParam Long cardId, Model m) {
        CardDTO card;
        try {
            card = cardService.getById(cardId);
        } catch (CardNotFoundException e) {
            log.error(e.getMessage());
            card = new CardDTO();
        }
        m.addAttribute("card", card);
        return "edit-card";
    }

    @PostMapping("/update")
    public String update(@ModelAttribute CardDTO card, RedirectAttributes ra) {
        final Long cardId = card.getId();
        try {
            cardService.update(card);
            log.info("Card with Id {} updated successfully!", cardId);
            ra.addFlashAttribute("msg", "Card updated successfully!");
        } catch (CardNotFoundException e) {
            log.error("Error updating card with Id {}: {}", cardId, e.getMessage());
            ra.addFlashAttribute("msg", e.getMessage());
        }
        return "redirect:/decks/edit/" + card.getDeck().getId();
    }

    @GetMapping("/input/{deckId}")
    public String input(@PathVariable Long deckId, Model m) {
        DeckDTO deck = DeckDTO.builder().id(deckId).build();
        CardDTO newCardInDeck = CardDTO.builder().deck(deck).build();
        m.addAttribute("card", newCardInDeck);
        return "input-card";
    }

    @PostMapping("/add")
    public String add(@ModelAttribute CardDTO card, RedirectAttributes ra) {
        final Long cardId = card.getId();
        try {
            cardService.add(card);
            log.info("Card with Id {} added successfully!", cardId);
            ra.addFlashAttribute("msg", "Card added successfully!");
        } catch (Exception e) {
            log.error("Error adding card with Id {}: {}", cardId, e.getMessage());
            ra.addFlashAttribute("msg", e.getMessage());
        }
        return "redirect:/decks/edit/" + card.getDeck().getId();
    }

    @GetMapping("/delete")
    public String delete(@RequestParam Long cardId, @RequestParam Long deckId) {
        try {
            cardService.delete(cardId);
        } catch (CardNotFoundException e) {
            log.error(e.getMessage());
        }
        return "redirect:/decks/edit/" + deckId;
    }
}
