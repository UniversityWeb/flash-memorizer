package com.universityteam.flashmemorizer.strategy.reviewcard;

import com.universityteam.flashmemorizer.dto.CardDTO;
import com.universityteam.flashmemorizer.dto.MatchingCard;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * The Matching class implements the ReviewStrategy interface for generating
 * and managing matching cards for reviewing.
 */
public class Matching implements ReviewStrategy<MatchingCard> {

    /**
     * Generates a list of MatchingCard objects for review based on the provided list of CardDTO objects.
     *
     * @param cards List of CardDTO objects containing term and description.
     * @return List of generated MatchingCard objects for review.
     */
    @Override
    public List<MatchingCard> generateTest(List<CardDTO> cards) {
        List<String> descAndTerms = generateListOfDescAndTerm(cards);

        List<MatchingCard> matchingCards = IntStream.range(0, descAndTerms.size())
                .mapToObj(i -> {
                    String word = descAndTerms.get(i);
                    int curOrder = i;
                    int answerOrder = i % 2 == 0 ? i + 1 : i - 1;

                    return MatchingCard.builder()
                            .question(word)
                            .curOrder(curOrder)
                            .answerOrder(answerOrder)
                            .build();
                })
                .collect(Collectors.toList());

        Collections.shuffle(matchingCards);

        return matchingCards;
    }

    /**
     * Generates a list of terms and descriptions from the provided list of CardDTO objects.
     *
     * @param cards List of CardDTO objects containing term and description.
     * @return List of terms and descriptions as strings.
     */
    private List<String> generateListOfDescAndTerm(List<CardDTO> cards) {
        List<String> words = cards.stream()
                .flatMap(card -> Stream.of(card.getTerm(), card.getDesc()))
                .collect(Collectors.toList());

        return words;
    }

    /**
     * Generates a review result string based on the list of MatchingCard objects.
     *
     * @param cardReviews List of MatchingCard objects containing review information.
     * @return Review result as a string.
     */
    @Override
    public String getResult(List<MatchingCard> cardReviews) {
        return null;
    }
}
