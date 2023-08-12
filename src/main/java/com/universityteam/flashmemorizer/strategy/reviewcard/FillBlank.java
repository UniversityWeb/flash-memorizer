package com.universityteam.flashmemorizer.strategy.reviewcard;

import com.universityteam.flashmemorizer.dto.CardDTO;
import com.universityteam.flashmemorizer.dto.FillBlankCard;

import java.util.*;
import java.util.stream.Collectors;

/**
 * FillBlank - Review Strategy for Fill in the Blank Card
 * This class implements the ReviewStrategy interface for Fill in the Blank cards.
 * It generates test cards by creating fill in the blank questions based on the provided cards' descriptions.
 * Each description is processed to create a context with blank spaces, and corresponding fill in the blank cards are generated.
 */
public class FillBlank implements ReviewStrategy<FillBlankCard> {

    /**
     * Generates a list of FillBlankCard instances based on the given list of CardDTOs.
     * Each CardDTO's description is processed to create a question.
     *
     * @param cards The list of CardDTO objects representing the flashcards to be reviewed.
     * @return A list of FillBlankCard instances with fill in the blank questions.
     */
    @Override
    public List<FillBlankCard> generateTest(List<CardDTO> cards) {
        return cards.stream()
                .map(card -> {
                    FillBlankCard fillBlankCard = new FillBlankCard();

                    String desc = card.getDesc();
                    String synthesizedBlank = createBlank(desc);
                    String descWithBlanks = createDescWithBlanks(synthesizedBlank, desc);

                    fillBlankCard.setTerm(card.getTerm());
                    fillBlankCard.setAnswer(synthesizedBlank);
                    fillBlankCard.setQuestion(descWithBlanks);

                    return fillBlankCard;
                })
                .collect(Collectors.toList());
    }

    /**
     * Creates a string which includes blank contexts.
     * Randomly selects a few words from the original description to create the blank context.
     * The number of blanks is limited to 3 or the number of words in the description minus 1, whichever is smaller.
     *
     * @param desc The original description from which the blank context will be created.
     * @return A string including blank contexts which is generated from the description.
     */
    private String createBlank(String desc) {
        List<String> blanks = Arrays.asList(desc.split("[\\s.,;!?]+"));
        Collections.shuffle(blanks);
        blanks = blanks.subList(0, Math.min(3, blanks.size() - 1));

        String synthesizedBlank = Arrays.stream(desc.split(" "))
                .filter(blanks::contains)
                .collect(Collectors.joining(" "));

        return synthesizedBlank;
    }

    /**
     * Creates a list of words with blanks based on the description and the provided blank context.
     * Replaces the blank context words in the original description with "blank-context-for-input" to easily recognize in the frontend.
     *
     * @param synthesizedBlank The string blank context which includes blank contexts of the paragraph.
     * @param desc The original description from which the fill in the blank question will be created.
     * @return A string representing a description with blank contexts.
     */
    private String createDescWithBlanks(String synthesizedBlank, String desc) {
        String[] blanks = synthesizedBlank.split(" ");

        for (String blank : blanks) {
            desc = desc.replaceAll("(?<!\\S)" + blank + "(?!\\S)", "blank-context-for-input");
        }

        return desc;
    }
}