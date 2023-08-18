package com.universityteam.flashmemorizer.controller;

import com.universityteam.flashmemorizer.dto.DeckDTO;
import com.universityteam.flashmemorizer.dto.SharedDeckDTO;
import com.universityteam.flashmemorizer.dto.UserDTO;
import com.universityteam.flashmemorizer.exception.DeckNotFoundException;
import com.universityteam.flashmemorizer.service.DeckService;
import com.universityteam.flashmemorizer.service.SharedDeckService;
import com.universityteam.flashmemorizer.service.UserService;
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

    @Autowired
private UserService  userService;

    @Autowired
    private DeckService deckService;

    @PostMapping("/add")
    public String add(@ModelAttribute SharedDeckDTO sharedDeck, @RequestParam Long deckId, @RequestParam Long recipientId, RedirectAttributes ra) throws DeckNotFoundException {

        UserDTO userDTO = userService.getById(recipientId);
        DeckDTO deckDTO = deckService.getById(deckId);
        sharedDeck.setCreation(new Date());
        sharedDeck.setDeck(deckDTO);
        sharedDeck.setRecipient(userDTO);
        try {
            SharedDeckDTO added = sharedDeckService.add(sharedDeck);
            log.info("Shared deck added successfully!");
            ra.addFlashAttribute("successMsg", "Shared deck added successfully!");
            return "redirect:/decks/review/" + deckDTO.getId();
        } catch (Exception e) {
            log.error(e.getMessage());
            ra.addFlashAttribute("errorMsg", "Shared deck added unsuccessfully!");
            return "redirect:/sharedDecks/new";
        }
    }
}
