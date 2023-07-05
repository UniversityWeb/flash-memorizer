package com.universityteam.flashmemorizer.strategy.reviewcard;

import com.universityteam.flashmemorizer.dto.MultiChoiceCard;
import com.universityteam.flashmemorizer.entity.Card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class MultiChoice implements ReviewStrategy<MultiChoiceCard> {
    @Override
    public List<MultiChoiceCard> generateTest(List<Card> cards) {
        List<String> choices = cards.stream()
                .map(Card::getTerm)
                .collect(Collectors.toList());

        return cards.stream()
                .map(card -> {
                    MultiChoiceCard multiChoiceCard = new MultiChoiceCard();

                    String term = card.getTerm();
                    List<String> options = generateOptions(choices, term);
                    int indexChoose = findAnswer(options, term);

                    multiChoiceCard.setDesc(card.getDesc());
                    multiChoiceCard.setOptions(options);
                    multiChoiceCard.setIndexAnswer(indexChoose);

                    return multiChoiceCard;
                })
                .collect(Collectors.toList());
    }

    @Override
    public boolean checkAnswer(MultiChoiceCard card) {
        boolean isCorrect = card.getIndexAnswer() == card.getIndexChoose();
        card.setCorrect(isCorrect);
        return isCorrect;
    }

    private List<String> generateOptions(List<String> choices, String term) {
        List<String> options = new ArrayList<>(choices);
        options.removeIf(choice -> choice.equals(term));
        Collections.shuffle(options);
        options = options.subList(0, Math.min(3, options.size()));
        options.add(term);
        Collections.shuffle(options);
        return options;
    }

    private int findAnswer(List<String> options, String cardDes) {
        return options.indexOf(cardDes);
    }
}
