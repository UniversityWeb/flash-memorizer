package com.universityteam.flashmemorizer.controller;

import com.universityteam.flashmemorizer.dto.DeckDTO;
import com.universityteam.flashmemorizer.service.DeckService;
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

    @PostMapping("/update")
    public String update(@ModelAttribute DeckDTO deck) {
        return "";
    }
}
