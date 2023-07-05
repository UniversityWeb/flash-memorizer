package com.universityteam.flashmemorizer.strategy.reviewcard;

import com.universityteam.flashmemorizer.dto.MatchingCard;
import com.universityteam.flashmemorizer.entity.Card;

import java.util.List;
import java.util.stream.Collectors;

public class Matching implements ReviewStrategy<MatchingCard> {
    @Override
    public List<MatchingCard> generateTest(List<Card> cards) {
        return cards.stream()
                .map(card -> {
                    MatchingCard matchingCard = new MatchingCard();

                    matchingCard.setTerm(card.getTerm());
                    matchingCard.setDesc(card.getDesc());

                    return matchingCard;
                })
                .collect(Collectors.toList());
    }

    @Override
    public boolean checkAnswer(MatchingCard card) {
        boolean isCorrect = card.getTerm().equals(card.getUserChoose());
        card.setCorrect(isCorrect);
        return isCorrect;
    }
}
