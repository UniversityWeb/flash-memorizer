package com.universityteam.flashmemorizer.strategy.reviewcard;

import com.universityteam.flashmemorizer.dto.MatchingCard;
import com.universityteam.flashmemorizer.entity.Card;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Matching implements ReviewStrategy<MatchingCard> {
    @Override
    public List<MatchingCard> generateTest(List<Card> cards) {
        List<String> words = createRandomWords(cards);
        return words.stream()
                .map(word -> {
                    MatchingCard matchingCard = new MatchingCard();
                    String hiddenWord = findHiddenPart(cards, word);
                    matchingCard.setDisplayPart(word);
                    matchingCard.setHiddenPart(hiddenWord);
                    return matchingCard;
                })
                .collect(Collectors.toList());
    }

    public boolean isAnswer(MatchingCard movingCard, MatchingCard targetCard) {
        boolean isCorrect = movingCard.getDisplayPart().equals(targetCard.getHiddenPart());
        movingCard.setCorrect(isCorrect);
        movingCard.setVanished(isCorrect);
        targetCard.setCorrect(isCorrect);
        targetCard.setVanished(isCorrect);
        return isCorrect;
    }

    private List<String> createRandomWords(List<Card> cards) {
        List<String> words = cards.stream()
                .flatMap(card -> Stream.of(card.getTerm(), card.getDesc()))
                .collect(Collectors.toList());
        Collections.shuffle(words);
        return words;
    }

    private String findHiddenPart(List<Card> cards, String word) {
        for (Card card : cards) {
            if (word.equals(card.getTerm())) {
                return card.getDesc();
            }
            else if (word.equals(card.getDesc())) {
                return card.getTerm();
            }
        }
        return null;
    }
}
