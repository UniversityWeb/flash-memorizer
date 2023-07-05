package com.universityteam.flashmemorizer.strategy.reviewcard;

import com.universityteam.flashmemorizer.dto.FillBlankCard;
import com.universityteam.flashmemorizer.entity.Card;

import java.util.*;
import java.util.stream.Collectors;

public class FillBlank implements ReviewStrategy<FillBlankCard> {
    @Override
    public List<FillBlankCard> generateTest(List<Card> cards) {
        return cards.stream()
                .map(card -> {
                    FillBlankCard fillBlankCard = new FillBlankCard();

                    String blank = "_____";
                    String desc = card.getDesc();
                    List<String> answers = createAnswers(desc);

                    fillBlankCard.setTerm(card.getTerm());
                    fillBlankCard.setFillAnswers(answers);
                    fillBlankCard.setDesc(resetDesc(answers, desc, blank));
                    fillBlankCard.setBlankContext(blank);

                    return fillBlankCard;
                })
                .collect(Collectors.toList());
    }

    @Override
    public boolean checkAnswer(FillBlankCard card) {
        boolean isCorrect = card.getFillAnswers().equals(card.getUserFills());
        card.setCorrect(isCorrect);
        return isCorrect;
    }

    private List<String> createAnswers(String desc) {
        List<String> answers = Arrays.asList(desc.split(" "));
        Collections.shuffle(answers);
        answers = answers.subList(0, Math.min(2, answers.size() - 1));
        List<String> sortedAnswers = Arrays.stream(desc.split(" "))
                .filter(answers::contains)
                .collect(Collectors.toList());
        return sortedAnswers;
    }

    private String resetDesc(List<String> answers, String desc, String blank) {
        for (String answer : answers) {
            desc = desc.replaceFirst(answer, blank);
        }
        return desc;
    }
}
