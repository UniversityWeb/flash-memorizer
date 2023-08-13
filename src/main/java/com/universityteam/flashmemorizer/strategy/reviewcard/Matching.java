package com.universityteam.flashmemorizer.strategy.reviewcard;

import com.universityteam.flashmemorizer.dto.CardDTO;
import com.universityteam.flashmemorizer.dto.MatchingCard;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Matching - Review Strategy for Matching Card
 * This class implements the ReviewStrategy interface for Matching cards.
 * It generates test cards by creating matching pairs based on the provided cards' terms and descriptions.
 * Each card's term or description is displayed, and the corresponding term or description is hidden.
 */
public class Matching implements ReviewStrategy<MatchingCard> {

    /**
     * Generates a list of MatchingCard instances based on a list of CardDTO instances.
     *
     * @param cards List of CardDTO instances to generate MatchingCard instances from.
     * @return List of MatchingCard instances.
     */
    @Override
    public List<MatchingCard> generateTest(List<CardDTO> cards) {
        List<String> descAndTerms = generateListOfDescAndTerm(cards);

        return descAndTerms.stream()
                .map(word -> {
                    MatchingCard matchingCard = new MatchingCard();

                    String hiddenWord = findHiddenPart(cards, word);

                    matchingCard.setDisplayPart(word);
                    matchingCard.setHiddenPart(hiddenWord);

                    return matchingCard;
                })
                .collect(Collectors.toList());
    }

    @Override
    public String getResult(List<MatchingCard> cardReviews) {
        return null;
    }

    /**
     * Generates a shuffled list of term and description strings from a list of CardDTO instances.
     *
     * @param cards List of CardDTO instances.
     * @return Shuffled list of term and description strings.
     */
    private List<String> generateListOfDescAndTerm(List<CardDTO> cards) {
        List<String> words = cards.stream()
                .flatMap(card -> Stream.of(card.getTerm(), card.getDesc()))
                .collect(Collectors.toList());

        Collections.shuffle(words);

        return words;
    }

    /**
     * Finds the hidden part (term or description) corresponding to a given word.
     *
     * @param cards List of CardDTO instances.
     * @param word  The word for which to find the hidden part.
     * @return The hidden part (term or description) corresponding to the given word.
     */
    private String findHiddenPart(List<CardDTO> cards, String word) {
        for (CardDTO card : cards) {
            if (word.equals(card.getTerm())) {
                return card.getDesc();
            } else if (word.equals(card.getDesc())) {
                return card.getTerm();
            }
        }

        return null;
    }
}