package com.universityteam.flashmemorizer.controller;

import com.universityteam.flashmemorizer.dto.CardDTO;
import com.universityteam.flashmemorizer.dto.DeckDTO;
import com.universityteam.flashmemorizer.exception.CardNotFoundException;
import com.universityteam.flashmemorizer.exception.DeckNotFoundException;
import com.universityteam.flashmemorizer.form.DeckForm;
import com.universityteam.flashmemorizer.service.CardService;
import com.universityteam.flashmemorizer.service.DeckService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/decks")
public class DeckController {

    private final Logger log = LoggerFactory.getLogger(DeckController.class);

    @Autowired
    private DeckService deckService;

    @Autowired
    private CardService cardService;

    @GetMapping("/get")
    public String getDecksByUserId(@RequestParam Long userId, Model m) {
        List<DeckDTO> decks;
        try {
            decks = deckService.getByUser(userId);
        } catch (DeckNotFoundException e) {
            log.error(e.getMessage());
            decks = new ArrayList<>();
        }
        m.addAttribute("decks", decks);
        return "deck";
    }

    @GetMapping("/input")
    public String inputForm() {
        return "input-deck";
    }

    @PostMapping("/add")
    public String add(@ModelAttribute DeckDTO deck, RedirectAttributes ra) {
        deck.setCreation(new Date());
        deck.setModified(new Date());
        try {
            deckService.add(deck);
            log.info("Deck added successfully!");
            ra.addFlashAttribute("msg", "Deck added successfully!");
            return "redirect:/decks/edit/" + deck.getId();
        } catch (Exception e) {
            log.error(e.getMessage());
            ra.addFlashAttribute("msg", "Deck added unsuccessfully!");
            return "redirect:/decks/new";
        }
    }

    @GetMapping("/edit/{deckId}")
    public String getDeckDetails(@PathVariable("deckId") Long deckId, Model m) {
        DeckForm deckForm = new DeckForm();
        DeckDTO deck = getDeckById(deckId);
        if (deck == null) {
            log.error("Deck not found with Id: {}", deckId);
            deck = new DeckDTO();
        } else {
            ArrayList<CardDTO> cardDTOS = (ArrayList) cardService.getByDeckId(deckId);
            deckForm.setCards(cardDTOS);
            log.info("Deck details retrieved successfully for Id: {}", deckId);
        }
        deckForm.setDeck(deck);
        m.addAttribute("deckForm", deckForm);
        return "edit-deck";
    }

    private DeckDTO getDeckById(Long deckId) {
        try {
            return deckService.getById(deckId);
        } catch (DeckNotFoundException e) {
            log.error("Error while fetching deck with Id: {}", deckId, e);
            return null;
        }
    }

    @PostMapping("/update")
    public String update(@ModelAttribute DeckForm deckForm, RedirectAttributes ra) {
        final Long deckId = deckForm.getDeck().getId();
        try {
            deckForm.getDeck().setModified(new Date());
            deckService.update(deckForm.getDeck());
            cardService.saveCardsByDeck(deckId, deckForm.getCards());
            log.info("Deck with Id {} updated successfully!", deckId);
            ra.addFlashAttribute("msg", "Deck updated successfully!");
        } catch (DeckNotFoundException | CardNotFoundException e) {
            log.error("Error updating deckForm with Id {}: {}", deckId, e.getMessage());
            ra.addFlashAttribute("msg", e.getMessage());
        }
        return "redirect:/cards/review/" + deckId;
    }

    @GetMapping("/delete")
    public String delete(@RequestParam Long userId, @RequestParam Long deckId, RedirectAttributes ra) {
        try {
            deckService.delete(deckId);
            ra.addFlashAttribute("message", "The Deck Id=" + deckId + " has been deleted.");
        } catch (DeckNotFoundException e) {
            log.error(e.getMessage());
            ra.addFlashAttribute("message", e.getMessage());
        }
        return "redirect:/decks/get?userId=" + userId;
    }
}