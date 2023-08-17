package com.universityteam.flashmemorizer.strategy.reviewcard;

import com.universityteam.flashmemorizer.dto.CardDTO;
import com.universityteam.flashmemorizer.dto.MultiChoiceCard;
import com.universityteam.flashmemorizer.utility.Utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * MultiChoice - Review Strategy for Multiple Choice Cards
 * This class implements the ReviewStrategy interface for Multiple Choice cards.
 * It generates test cards with multiple choice options based on the provided cards.
 * The question for each card is the card's description, and the options are generated from the list of card's terms.
 * Each card's term is used as the correct option, while other terms are used as distractors.
 */
public class MultiChoice implements ReviewStrategy<MultiChoiceCard> {

    /**
     * Generates a list of MultiChoiceCard instances based on the given list of CardDTOs.
     * Each CardDTO's term is used as the correct option, and distractors are randomly chosen from other terms.
     *
     * @param cards The list of CardDTO objects representing the flashcards to be reviewed.
     * @return A list of MultiChoiceCard instances with multiple choice questions.
     */
    @Override
    public List<MultiChoiceCard> generateTest(List<CardDTO> cards) {
        List<String> terms = extractPlainTermsFromCards(cards);

        return cards.stream()
                .map(card -> {
                    String plainTerm = Utils.htmlToPlainText(card.getTerm());
                    List<String> options = generateOptions(terms, plainTerm);

                    return MultiChoiceCard.builder()
                            .question(card.getDesc())
                            .options(options)
                            .answer(plainTerm)
                            .build();
                })
                .collect(Collectors.toList());
    }

    /**
     * Creates a list of terms from the provided list of cards.
     *
     * @param cards The list of CardDTO objects representing the flashcards.
     * @return A list of terms extracted from the CardDTO objects to be used for generating options.
     */
    private List<String> extractPlainTermsFromCards(List<CardDTO> cards) {
        return cards.stream()
                .map(card -> Utils.htmlToPlainText(card.getTerm()))
                .collect(Collectors.toList());
    }

    /**
     * Creates a list of options for the multiple choice question.
     * The correct option (term) is passed separately and removed from the list of terms to avoid duplication.
     * Randomly selects distractors (options) from the remaining terms.
     * The number of distractors is limited to 3 or the number of terms minus 1, whichever is smaller.
     *
     * @param terms The list of terms available to choose from.
     * @param term  The correct term to be used as the correct option in the multiple choice question.
     * @return A list of options with the correct term and randomly chosen distractors.
     */
    private List<String> generateOptions(List<String> terms, String term) {
        final int defaultSizeOfOptions = 3;

        List<String> options = new ArrayList<>(terms);

        options.removeIf(choice -> choice.equals(term));
        Collections.shuffle(options);
        options = options.subList(0, Math.min(defaultSizeOfOptions, options.size()));
        options.add(term);
        Collections.shuffle(options);

        return options;
    }

    @Override
    public String getResult(List<MultiChoiceCard> cardReviews) {
        int score = 0;
        for (MultiChoiceCard cardReview : cardReviews) {
            if (isCorrect(cardReview)) {
                cardReview.setCorrect(true);
                score++;
            }
        }

        return score + "/" + cardReviews.size();
    }

    private boolean isCorrect(MultiChoiceCard cardReview) {
        if (cardReview.getUserChoice() != null && cardReview.getUserChoice().equalsIgnoreCase(cardReview.getAnswer())) {
            return true;
        }
        return false;
    }
}
