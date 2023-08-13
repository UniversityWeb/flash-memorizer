package com.universityteam.flashmemorizer.strategy.reviewcard;

import com.universityteam.flashmemorizer.dto.CardDTO;
import com.universityteam.flashmemorizer.dto.FillBlankCard;

import java.util.*;
import java.util.stream.Collectors;

/**
 * FillBlank - Review Strategy for Fill in the Blank Card
 * This class implements the ReviewStrategy interface to generate fill in the blank questions
 * based on the descriptions of provided cards. It processes card descriptions, creates contexts with blanks,
 * and generates corresponding fill in the blank cards.
 */
public class FillBlank implements ReviewStrategy<FillBlankCard> {

    /**
     * Generates a list of FillBlankCard instances from the given list of CardDTOs.
     * Each CardDTO's description is used to create a fill in the blank question.
     *
     * @param cards The list of CardDTO objects representing flashcards for review.
     * @return A list of FillBlankCard instances with fill in the blank questions.
     */
    @Override
    public List<FillBlankCard> generateTest(List<CardDTO> cards) {
        return cards.stream()
                .map(card -> {
                    FillBlankCard cardReview = new FillBlankCard();

                    String desc = card.getDesc();
                    List<String> hideTexts = createHideTexts(desc);
                    String descWithBlanks = createDescWithBlanks(hideTexts, desc);

                    cardReview.setTerm(card.getTerm());
                    cardReview.setHideTexts(hideTexts);
                    cardReview.setQuestion(descWithBlanks);

                    return cardReview;
                })
                .collect(Collectors.toList());
    }

    @Override
    public String getResult(List<FillBlankCard> cardReviews) {
        int score = 0;
//        for (FillBlankCard cardReview : cardReviews) {
//            if (cardReview.getUserChoice() != null && cardReview.getUserChoice().equalsIgnoreCase(cardReview.getAnswer())) {
//                cardReview.setCorrect(true);
//                score++;
//            }
//        }

        return score + "/" + cardReviews.size();
    }

    /**
     * Creates a list of words representing hide texts.
     * Randomly selects words from the original description to form the hide texts.
     * The number of texts is limited to 3 or the number of words in the description minus 1, whichever is smaller.
     *
     * @param desc The original description.
     * @return A list of words representing the hide texts.
     */
    private List<String> createHideTexts(String desc) {
        List<String> texts = Arrays.asList(desc.split("[\\s.,;!?]+"));
        Collections.shuffle(texts);
        texts = texts.subList(0, Math.min(3, texts.size()));

        List<String> hideTexts = Arrays.stream(desc.split(" "))
                .filter(texts::contains)
                .collect(Collectors.toList());

        return hideTexts;
    }

    /**
     * Creates a description with blank placeholders based on the original description and blank contexts.
     * Replaces the words in the original description with "blank-context-for-input" to indicate blanks.
     *
     * @param hideTexts A list of words representing the hidden texts.
     * @param desc The original description used to create the fill in the blank question.
     * @return A description with blank placeholders.
     */
    private String createDescWithBlanks(List<String> hideTexts, String desc) {
        for (String text : hideTexts) {
            desc = desc.replaceAll("(?<!\\S)" + text + "(?!\\S)", "blank-context-for-input");
        }

        return desc;
    }
}