package com.universityteam.flashmemorizer.controller;

import com.universityteam.flashmemorizer.dto.CardDTO;
import com.universityteam.flashmemorizer.service.CardService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
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
        List<CardDTO> cards = cardService.getByDeckId(deckId);
        m.addAttribute("cards", cards);
        return "review-card";
    }

    @GetMapping("/delete/")
    public String delete(@ModelAttribute CardDTO card, HttpSession session) {
        if (cardService.delete(card)) {
            session.setAttribute("msg", "Deck Delete Successfully...");
        } else {
            session.setAttribute("msg", "Deck Delete Unsuccessfully...");
        }
        return "redirect:/cards/" + card.getDeck().getId();
    }
}
