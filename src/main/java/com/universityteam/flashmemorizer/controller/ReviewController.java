package com.universityteam.flashmemorizer.controller;

import com.universityteam.flashmemorizer.dto.CardDTO;
import com.universityteam.flashmemorizer.dto.CardReview;
import com.universityteam.flashmemorizer.dto.DeckDTO;
import com.universityteam.flashmemorizer.enums.EReview;
import com.universityteam.flashmemorizer.exception.DeckNotFoundException;
import com.universityteam.flashmemorizer.form.CardReviewForm;
import com.universityteam.flashmemorizer.form.FillBlankForm;
import com.universityteam.flashmemorizer.form.MultiChoiceForm;
import com.universityteam.flashmemorizer.service.CardReviewService;
import com.universityteam.flashmemorizer.service.CardService;
import com.universityteam.flashmemorizer.service.DeckService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/review")
public class ReviewController {
    private final Logger log = LoggerFactory.getLogger(CardController.class);

    @Autowired
    private CardService cardService;
    @Autowired
    private DeckService deckService;

    @Autowired
    private CardReviewService reviewService;

    Boolean submitted = false;

    @GetMapping("/get-review")
    public String review(@RequestParam EReview reviewType, @RequestParam Long deckId, Model m) throws DeckNotFoundException {
        submitted = false;
        DeckDTO deck = deckService.getById(deckId);
        List<CardDTO> cards = cardService.getByDeckId(deckId);
        List<CardReview> cardReviews = reviewService.generateTest(reviewType, cards);

        CardReviewForm cardReviewForm = new CardReviewForm();
        cardReviewForm.setDeckName(deck.getName());
        cardReviewForm.setDeckId(deck.getId());
        cardReviewForm.setReviewType(reviewType);
        cardReviewForm.setCardReviews(cardReviews);

        m.addAttribute("cardReviewForm", cardReviewForm);

        return reviewType.getHtmlFile();
    }

    @PostMapping("/submit-multi-choice")
    public String submitMultiChoice(@ModelAttribute("cardReviewForm") MultiChoiceForm cardReviewForm, Model m) {
        if (!submitted) {
            String result = reviewService.getResult(cardReviewForm.getReviewType(), cardReviewForm.getCardReviews());
            cardReviewForm.setResult(result);

            m.addAttribute("cardReviewForm", cardReviewForm);

            System.out.println("cardReviews " + cardReviewForm.getCardReviews());

            submitted = true;
        }
        else {
            return "review-result-error";
        }

        return "multi-choice-result";
    }

    @PostMapping("/submit-fill-blank")
    public String submitFillBlank(@ModelAttribute("cardReviewForm") FillBlankForm cardReviewForm, Model m) {
        if (!submitted) {
            String result = reviewService.getResult(cardReviewForm.getReviewType(), cardReviewForm.getCardReviews());
            cardReviewForm.setResult(result);

            m.addAttribute("cardReviewForm", cardReviewForm);

            System.out.println("cardReviews " + cardReviewForm.getCardReviews());

            submitted = true;
        }
        else {
            return "review-result-error";
        }

        return "review-result";
    }
}