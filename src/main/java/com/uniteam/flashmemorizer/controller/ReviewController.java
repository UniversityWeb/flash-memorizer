package com.uniteam.flashmemorizer.controller;

import com.uniteam.flashmemorizer.customenum.EReview;
import com.uniteam.flashmemorizer.dto.CardDTO;
import com.uniteam.flashmemorizer.dto.DeckDTO;
import com.uniteam.flashmemorizer.exception.DeckNotFoundException;
import com.uniteam.flashmemorizer.form.CardReviewForm;
import com.uniteam.flashmemorizer.form.FillBlankForm;
import com.uniteam.flashmemorizer.form.MultiChoiceForm;
import com.uniteam.flashmemorizer.service.cardreview.CardReviewService;
import com.uniteam.flashmemorizer.service.card.CardService;
import com.uniteam.flashmemorizer.service.deck.DeckService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/review")
public class ReviewController {

    @Autowired
    private CardService cardService;
    @Autowired
    private DeckService deckService;
    @Autowired
    private CardReviewService reviewService;

    @GetMapping("/get-review")
    public String review(@RequestParam EReview reviewType, @RequestParam Long deckId,
                         Model m, RedirectAttributes ra) throws DeckNotFoundException {
        DeckDTO deck = deckService.getById(deckId);
        List<CardDTO> cards = cardService.getByDeckId(deckId);

        if (cards.size() < CardReviewService.AT_LEAST_CARDS) {
            ra.addFlashAttribute("errorMsg", "There are not enough cards.");
            return "redirect:/decks/review/" + deckId;
        }

        var cardReviews = reviewService.generateTest(reviewType, cards);

        CardReviewForm cardReviewForm = CardReviewForm.builder()
                .deckId(deckId)
                .deckName(deck.getName())
                .reviewType(reviewType)
                .isSubmitted(false)
                .cardReviews(cardReviews)
                .build();

        m.addAttribute("cardReviewForm", cardReviewForm);

        return reviewType.getReviewFile();
    }

    @PostMapping("/submit-fill-blank")
    public String submitFillBlank(@ModelAttribute("cardReviewForm") FillBlankForm cardReviewForm, Model m) {
        return getResultForm(cardReviewForm, m, "fill-blank-result");
    }

    @PostMapping("/submit-multi-choice")
    public String submitMultiChoice(@ModelAttribute("cardReviewForm") MultiChoiceForm cardReviewForm, Model m) {
        return getResultForm(cardReviewForm, m, "multi-choice-result");
    }

    private String getResultForm(CardReviewForm cardReviewForm, Model m, String formName) {
        if (cardReviewForm.isSubmitted()) {
            return "review-result-error";
        }
        cardReviewForm.setSubmitted(true);
        String result = reviewService.getResult(cardReviewForm.getReviewType(), cardReviewForm.getCardReviews());
        cardReviewForm.setResult(result);

        m.addAttribute("cardReviewForm", cardReviewForm);

        return formName;
    }
}