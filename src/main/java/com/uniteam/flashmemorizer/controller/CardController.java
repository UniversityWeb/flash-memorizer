package com.uniteam.flashmemorizer.controller;

import com.uniteam.flashmemorizer.dto.CardDTO;
import com.uniteam.flashmemorizer.dto.DeckDTO;
import com.uniteam.flashmemorizer.exception.CardNotFoundException;
import com.uniteam.flashmemorizer.service.card.CardService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/cards")
public class CardController {

    private final Logger log = LogManager.getLogger(CardController.class);

    @Autowired
    private CardService cardService;

    @GetMapping("/edit")
    public String getCardsToEdit(@RequestParam Long cardId, Model m) {
        CardDTO card;
        try {
            card = cardService.getById(cardId);
            log.info("Card retrieved successfully for cardId: {}", cardId);
        } catch (CardNotFoundException e) {
            log.error("Error while fetching card with cardId: {}", cardId, e);
            card = new CardDTO();
        }
        m.addAttribute("card", card);
        return "edit-card";
    }

    @PostMapping("/update")
    public String update(@ModelAttribute CardDTO card, RedirectAttributes ra) {
        final Long cardId = card.getId();
        try {
            if (cardService.update(card) != null) {
                log.info("Card with Id {} updated successfully!", cardId);
                ra.addFlashAttribute("successMsg", "Card updated successfully!");
            } else {
                log.info("Card with Id {} updated unsuccessfully!", cardId);
                ra.addFlashAttribute("errorMsg", "Card updated unsuccessfully!");
            }
        } catch (CardNotFoundException e) {
            log.error("Error updating card with Id {}: {}", cardId, e.getMessage());
            ra.addFlashAttribute("errorMsg", e.getMessage());
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
            if (cardService.add(card) != null) {
                log.info("Card with Id {} added successfully!", cardId);
                ra.addFlashAttribute("successMsg", "Card added successfully!");
            } else {
                log.error("Card added unsuccessfully!");
                ra.addFlashAttribute("errorMsg", "Card added unsuccessfully!");
            }
        } catch (Exception e) {
            log.error("Error adding card with Id {}: {}", cardId, e.getMessage());
            ra.addFlashAttribute("errorMsg", e.getMessage());
        }
        return "redirect:/decks/edit/" + card.getDeck().getId();
    }

    @GetMapping("/delete")
    public String delete(@RequestParam Long cardId, @RequestParam Long deckId, RedirectAttributes ra) {
        try {
            if (cardService.delete(cardId)) {
                log.info("Card with Id {} deleted successfully!", cardId);
                ra.addFlashAttribute("successMsg", "Card deleted successfully!");
            } else {
                log.error("Card with Id {} deleted unsuccessfully!", cardId);
                ra.addFlashAttribute("errorMsg", "Card deleted unsuccessfully!");
            }
        } catch (CardNotFoundException e) {
            log.error("Error deleting card with Id {}: {}", cardId, e.getMessage());
            ra.addFlashAttribute("errorMsg", e.getMessage());
        }
        return "redirect:/decks/edit/" + deckId;
    }
}