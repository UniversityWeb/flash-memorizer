package com.universityteam.flashmemorizer.strategy.reviewcard;

import com.universityteam.flashmemorizer.dto.CardDTO;
import com.universityteam.flashmemorizer.dto.review.FillBlankCard;
import com.universityteam.flashmemorizer.utility.Utils;

import java.util.*;
import java.util.stream.Collectors;

/**
 * FillBlank - Review Strategy for Fill in the Blank Cards
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
                .map(this::mapToFillBlankCard)
                .collect(Collectors.toList());
    }

    private FillBlankCard mapToFillBlankCard(CardDTO card) {
        String plainDesc = extractPlainDescFromCard(card);
        List<String> hiddenWords = generateHiddenWords(plainDesc);
        String descWithBlanks = generateDescWithBlanks(hiddenWords, plainDesc);

        return FillBlankCard.builder()
                .question(card.getTerm())
                .descWithBlanks(descWithBlanks)
                .hideTexts(hiddenWords)
                .build();
    }

    private String extractPlainDescFromCard(CardDTO card) {
        return Utils.htmlToPlainText(card.getDesc());
    }

    /**
     * Creates a list of words representing hidden words for blanks.
     * Randomly selects words from the original description to form the hidden words.
     * The number of words is limited to 3 or the number of words in the description minus 1, whichever is smaller.
     *
     * @param desc The original description.
     * @return A list of words representing the hidden words.
     */
    private List<String> generateHiddenWords(String desc) {
        final int defaultSizeOfHiddenWords = 3;

        List<String> words = Arrays.asList(desc.split("[\\s.,;!?]+"));
        Collections.shuffle(words);
        words = words.subList(0, Math.min(defaultSizeOfHiddenWords, words.size()));

        List<String> hiddenWords = Arrays.stream(desc.split(" "))
                .filter(words::contains)
                .collect(Collectors.toList());

        return hiddenWords;
    }

    /**
     * Creates a description with blank placeholders based on the original description and hidden words.
     * Replaces the words in the original description with "blank-context-for-input" to indicate blanks.
     *
     * @param hiddenWords A list of words representing the hidden words.
     * @param desc The original description used to create the fill in the blank question.
     * @return A description with blank placeholders.
     */
    private String generateDescWithBlanks(List<String> hiddenWords, String desc) {
        final String blankText = "blank-context-for-input";

        String descWithBlanks = desc;
        for (String word : hiddenWords) {
            descWithBlanks = descWithBlanks.replaceAll("(?<!\\S)" + word + "(?!\\S)", blankText);
        }

        return descWithBlanks;
    }

    @Override
    public String getResult(List<FillBlankCard> cardReviews) {
        int score = 0;
        for (FillBlankCard cardReview : cardReviews) {
            List<String> userResponses = removeNulls(cardReview.getUserFills());
            if (compareStringListsIgnoreCase(cardReview.getHideTexts(), userResponses)) {
                cardReview.setCorrect(true);
                score++;
            }
        }

        return score + "/" + cardReviews.size();
    }

    private List<String> removeNulls(List<String> inputList) {
        return inputList.stream()
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    private boolean compareStringListsIgnoreCase(List<String> list1, List<String> list2) {
        if (list1.size() != list2.size()) {
            return false;
        }

        for (int i = 0; i < list1.size(); i++) {
            String str1 = list1.get(i).trim();
            String str2 = list2.get(i).trim();

            if (!str1.equalsIgnoreCase(str2)) {
                return false;
            }
        }

        return true;
    }
}
