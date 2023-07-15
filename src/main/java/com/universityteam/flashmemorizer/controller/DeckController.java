package com.universityteam.flashmemorizer.controller;

import com.universityteam.flashmemorizer.dto.CardDTO;
import com.universityteam.flashmemorizer.dto.DeckDTO;
import com.universityteam.flashmemorizer.service.CardService;
import com.universityteam.flashmemorizer.service.DeckService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/deck")
public class DeckController {

    @Autowired
    private DeckService deckService;

    @Autowired
    private CardService cardService;

    @GetMapping("/get")
    public String getDeckByUserId(@RequestParam Long userId, Model m) {
        List<DeckDTO> decks = deckService.getByUser(userId);
        m.addAttribute("decks", decks);
        return "deck";
    }

    @GetMapping("/input")
    public String inputForm() {
        return "input-deck";
    }

    @GetMapping("/edit/{deckId}")
    public String getDeckDetails(@PathVariable("deckId") Long deckId, Model m) {
        DeckDTO deck = deckService.getById(deckId);
        List<CardDTO> cards = cardService.getByDeckId(deckId);
        m.addAttribute("deck", deck);
        m.addAttribute("cards", cards);
        return "edit-deck";
    }

    @PostMapping("/update")
    public String update(@ModelAttribute DeckDTO deck, @ModelAttribute List<CardDTO> cards, HttpSession session) {
        if (deckService.update(deck) != null) {
            cardService.addOrUpdate(cards);
            session.setAttribute("msg", "Deck Update Successfully...");
        } else {
            session.setAttribute("msg", "Deck Update Unsuccessfully...");
        }
        return "redirect:/card";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam Long cardId, @RequestParam Long deckId) {
        deckService.delete(cardId);
        return "redirect:/deck/edit/" + deckId;
    }
}