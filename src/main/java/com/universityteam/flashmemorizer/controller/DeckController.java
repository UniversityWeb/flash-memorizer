package com.universityteam.flashmemorizer.controller;

import com.universityteam.flashmemorizer.dto.CardDTO;
import com.universityteam.flashmemorizer.dto.DeckDTO;
import com.universityteam.flashmemorizer.dto.UserDTO;
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
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/decks")
public class DeckController {

    private final Logger log = LoggerFactory.getLogger(DeckController.class);

    @Autowired
    private DeckService deckService;

    @Autowired
    private CardService cardService;

    @GetMapping("/get-my-decks")
    public String getDecksByUserId(@RequestParam Long userId, Model m) {
        List<DeckDTO> decks;
        try {
            decks = deckService.getByUser(userId);
            for (DeckDTO deck : decks) {
                Integer quantity = cardService.countByDeckId(deck.getId());
                deck.setQuantityOfCards(quantity);
            }
            log.info("Decks retrieved successfully for userId: {}", userId);
        } catch (DeckNotFoundException e) {
            log.error("Error while fetching decks with userId: {}", userId, e);
            decks = new ArrayList<>();
        }
        m.addAttribute("decks", decks);
        m.addAttribute("userId", userId);
        return "edit-decks";
    }

    @GetMapping("/input")
    public String inputForm(@RequestParam Long userId, Model m) {
        UserDTO user = UserDTO.builder().id(userId).build();
        DeckDTO newDeckOfUser = DeckDTO.builder().user(user).build();
        m.addAttribute("deck", newDeckOfUser);
        return "input-deck";
    }

    @PostMapping("/add")
    public String add(@ModelAttribute DeckDTO deck, RedirectAttributes ra) {
        deck.setCreation(new Date());
        deck.setModified(new Date());
        try {
            DeckDTO added = deckService.add(deck);
            log.info("Deck added successfully!");
            ra.addFlashAttribute("successMsg", "Deck added successfully!");
            return "redirect:/decks/edit/" + added.getId();
        } catch (Exception e) {
            log.error(e.getMessage());
            ra.addFlashAttribute("errorMsg", "Deck added unsuccessfully!");
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
            deck.setQuantityOfCards( cardDTOS.size() );
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
    public String updateDeckOnly(@ModelAttribute DeckForm deckForm, RedirectAttributes ra) {
        final Long deckId = deckForm.getDeck().getId();
        try {
            deckForm.getDeck().setModified(new Date());
            deckService.update(deckForm.getDeck());
            log.info("Deck with Id {} updated successfully!", deckId);
            ra.addFlashAttribute("successMsg", "Deck updated successfully!");
        } catch (DeckNotFoundException e) {
            log.error("Error updating deckForm with Id {}: {}", deckId, e.getMessage());
            ra.addFlashAttribute("errorMsg", e.getMessage());
        }
        return "redirect:/cards/review/" + deckId;
    }

    @GetMapping("/delete")
    public String delete(@RequestParam Long userId, @RequestParam Long deckId, RedirectAttributes ra) {
        try {
            deckService.delete(deckId);
            log.info("Deck with Id {} deleted successfully!", deckId);
            ra.addFlashAttribute("successMsg", "Deck deleted successfully!");
        } catch (DeckNotFoundException e) {
            log.error("Error deleting deckForm with Id {}: {}", deckId, e.getMessage());
            ra.addFlashAttribute("errorMsg", e.getMessage());
        }
        return "redirect:/decks/get-my-decks?userId=" + userId;
    }
}