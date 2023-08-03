package com.universityteam.flashmemorizer.controller;

import com.universityteam.flashmemorizer.dto.CardDTO;
import com.universityteam.flashmemorizer.dto.CardReview;
import com.universityteam.flashmemorizer.dto.CardReviewForm;
import com.universityteam.flashmemorizer.dto.DeckDTO;
import com.universityteam.flashmemorizer.enums.EReview;
import com.universityteam.flashmemorizer.service.CardReviewService;
import com.universityteam.flashmemorizer.dto.DeckDTO;
import com.universityteam.flashmemorizer.exception.CardNotFoundException;
import com.universityteam.flashmemorizer.service.CardService;
import com.universityteam.flashmemorizer.service.DeckService;
import com.universityteam.flashmemorizer.service.FormService;
import jakarta.servlet.http.HttpSession;
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
    @Autowired
    private DeckService deckService;

    @Autowired
    private CardReviewService reviewService;

    @Autowired
    private FormService formService;

    Boolean submitted = false;
    @GetMapping("/{deckId}")
    public String getByDeckId(@PathVariable Long deckId, Model m) {
        List<CardDTO> cards = cardService.getByDeckId(deckId);
        m.addAttribute("cards", cards);
        return "review-card";
    }

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
            cardService.update(card);
            log.info("Card with Id {} updated successfully!", cardId);
            ra.addFlashAttribute("successMsg", "Card updated successfully!");
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
            cardService.add(card);
            log.info("Card with Id {} added successfully!", cardId);
            ra.addFlashAttribute("successMsg", "Card added successfully!");
        } catch (Exception e) {
            log.error("Error adding card with Id {}: {}", cardId, e.getMessage());
            ra.addFlashAttribute("errorMsg", e.getMessage());
        }
        return "redirect:/decks/edit/" + card.getDeck().getId();
    }

    @GetMapping("/delete")
    public String delete(@RequestParam Long cardId, @RequestParam Long deckId, RedirectAttributes ra) {
        try {
            cardService.delete(cardId);
            log.info("Card with Id {} deleted successfully!", cardId);
            ra.addFlashAttribute("successMsg", "Card deleted successfully!");
        } catch (CardNotFoundException e) {
            log.error("Error deleting card with Id {}: {}", cardId, e.getMessage());
            ra.addFlashAttribute("errorMsg", e.getMessage());
        }
        return "redirect:/decks/edit/" + deckId;
    }

    @GetMapping("/review")
    public String review(@RequestParam EReview reviewType, @RequestParam Long deckId, Model m) {
        submitted = false;
        DeckDTO deck = deckService.getById(deckId);
        List<CardDTO> cards = cardService.getByDeckId(deckId);
        List<CardReview> cardReviews = reviewService.generateTest(reviewType, cards);

        CardReviewForm cardReviewForm = new CardReviewForm();
        cardReviewForm.setDeckName(deck.getName());
        cardReviewForm.setDeckId(deck.getId());
        cardReviewForm.setReviewType(reviewType);

        m.addAttribute("cardReviews", cardReviews);
        m.addAttribute("cardReviewForm", cardReviewForm);

        return reviewType.getHtmlFile();
    }

    @PostMapping("/review/submit-answers")
    public String submitAnswers(@ModelAttribute("cardReviewForm") CardReviewForm cardReviewForm, Model m) {
        if (!submitted) {
            String result = formService.getResult(cardReviewForm);
            cardReviewForm.setResult(result);
            m.addAttribute("cardReviewForm", cardReviewForm);
            submitted = true;
        }
        else {
            return "review-result-error";
        }

        return "review-result";
    }
}