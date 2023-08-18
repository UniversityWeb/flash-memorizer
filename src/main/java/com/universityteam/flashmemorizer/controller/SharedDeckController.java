package com.universityteam.flashmemorizer.controller;

import com.universityteam.flashmemorizer.dto.DeckDTO;
import com.universityteam.flashmemorizer.dto.SharedDeckDTO;
import com.universityteam.flashmemorizer.dto.UserDTO;
import com.universityteam.flashmemorizer.service.SharedDeckService;
import lombok.extern.java.Log;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Date;

@Controller
@RequestMapping("/sharedDecks")
public class SharedDeckController {

    private final Logger log = LoggerFactory.getLogger(SharedDeckController.class);

    @Autowired
    private SharedDeckService sharedDeckService;

    @PostMapping("/add")
    public String add(@ModelAttribute SharedDeckDTO sharedDeck, @ModelAttribute DeckDTO deck, @ModelAttribute UserDTO recipient, RedirectAttributes ra) {
        sharedDeck.setCreation(new Date());
        sharedDeck.setDeck(deck);
        sharedDeck.setRecipient(recipient);
        try {
            SharedDeckDTO added = sharedDeckService.add(sharedDeck);
            log.info("Shared deck added successfully!");
            ra.addFlashAttribute("successMsg", "Shared deck added successfully!");
            return "redirect:/decks/review/" + deck.getId();
        } catch (Exception e) {
            log.error(e.getMessage());
            ra.addFlashAttribute("errorMsg", "Shared deck added unsuccessfully!");
            return "redirect:/sharedDecks/new";
        }
    }
}
