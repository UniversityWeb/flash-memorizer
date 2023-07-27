package com.universityteam.flashmemorizer.strategy.reviewcard;

import com.universityteam.flashmemorizer.dto.CardDTO;
import com.universityteam.flashmemorizer.dto.FillBlankCard;

import java.util.*;
import java.util.stream.Collectors;

public class FillBlank implements ReviewStrategy<FillBlankCard> {
    @Override
    public List<FillBlankCard> generateTest(List<CardDTO> cards) {
        return cards.stream()
                .map(card -> {
                    FillBlankCard fillBlankCard = new FillBlankCard();

                    String desc = card.getDesc();
                    String blankContext = createBlankContext(desc);
                    List<String> descWithBlanks = createDescWithBlanks(blankContext, desc);

                    fillBlankCard.setTerm(card.getTerm());
                    fillBlankCard.setDesc(desc);
                    fillBlankCard.setBlankContext(blankContext);
                    fillBlankCard.setDescWithBlanks(descWithBlanks);

                    return fillBlankCard;
                })
                .collect(Collectors.toList());
    }

    private String createBlankContext(String desc) {
        List<String> blanks = Arrays.asList(desc.split("[\\s.,;!?]+"));
        Collections.shuffle(blanks);
        blanks = blanks.subList(0, Math.min(3, blanks.size() - 1));

        String sortedBlanks = Arrays.stream(desc.split(" "))
                .filter(blanks::contains)
                .collect(Collectors.joining(" "));

        return sortedBlanks;
    }

    private List<String> createDescWithBlanks(String blankContext, String desc) {
        List<String> descWithBlanks = new ArrayList<>();
        String[] blanks = blankContext.split(" ");

        for (String blank : blanks) {
            desc = desc.replaceAll("(?<!\\S)" + blank + "(?!\\S)", "blank-context-for-input");
        }

        String[] words = desc.split(" ");
        Collections.addAll(descWithBlanks, words);
        return descWithBlanks;
    }
}