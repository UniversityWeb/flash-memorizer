package com.universityteam.flashmemorizer.strategy.reviewcard;

import com.universityteam.flashmemorizer.dto.CardDTO;
import com.universityteam.flashmemorizer.dto.review.MatchingCard;
import com.universityteam.flashmemorizer.exception.NotImplementedException;

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
                .mapToObj(i -> mapToMatchingCard(i, descAndTerms))
                .collect(Collectors.toList());

        Collections.shuffle(matchingCards);

        return matchingCards;
    }

    private MatchingCard mapToMatchingCard(int index, List<String> descAndTerms) {
        String word = descAndTerms.get(index);
        int curOrder = index;
        int answerOrder = index % 2 == 0 ? index + 1 : index - 1;

        return MatchingCard.builder()
                .question(word)
                .curOrder(curOrder)
                .answerOrder(answerOrder)
                .build();
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

    @Override
    public String getResult(List<MatchingCard> cardReviews) {
        throw new NotImplementedException("This method is not yet implemented");
    }
}
