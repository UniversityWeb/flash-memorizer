package com.uniteam.flashmemorizer.strategy.reviewcard;

import com.uniteam.flashmemorizer.dto.CardDTO;
import com.uniteam.flashmemorizer.dto.review.FillBlankCard;
import com.uniteam.flashmemorizer.utility.Utils;

import java.util.*;
import java.util.stream.Collectors;

public class FillBlank implements ReviewStrategy<FillBlankCard> {

    private static final int DEFAULT_SIZE_OF_HIDDEN_WORDS = 1;
    private static final String BLANK_TEXT = "blank-context-for-input";

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
        List<String> words = Arrays.asList(plainDesc.split(" "));
        PriorityQueue<Integer> hiddenIndexes = generateHiddenIndexes(words.size(), DEFAULT_SIZE_OF_HIDDEN_WORDS);
        List<String> hiddenWords = getHiddenWords(words, hiddenIndexes);
        String descWithBlanks = generateDescWithBlanks(words, hiddenIndexes);

        return FillBlankCard.builder()
                .question(card.getTerm())
                .descWithBlanks(descWithBlanks)
                .hideTexts(hiddenWords)
                .build();
    }

    private String extractPlainDescFromCard(CardDTO card) {
        return Utils.htmlToPlainText(card.getDesc());
    }

    private PriorityQueue<Integer> generateHiddenIndexes(int size, int numberOfHiddenWords) {
        List<Integer> originalIndexes = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            originalIndexes.add(i);
        }

        Collections.shuffle(originalIndexes);
        originalIndexes = originalIndexes.subList(0, numberOfHiddenWords);

        return new PriorityQueue<>(originalIndexes);
    }

    private List<String> getHiddenWords(List<String> words, PriorityQueue<Integer> hiddenIndexes) {
        List<String> hiddenWords = new ArrayList<>();
        List<Integer> indexList = new ArrayList<>(hiddenIndexes);
        for (Integer index : indexList) {
            String hiddenWord = words.get(index);
            hiddenWords.add(hiddenWord);
        }
        return hiddenWords;
    }

    private String generateDescWithBlanks(List<String> words, PriorityQueue<Integer> hiddenIndexes) {
        List<Integer> indexList = new ArrayList<>(hiddenIndexes);
        for (Integer index : indexList) {
            words.set(index, BLANK_TEXT);
        }
        return String.join(" ", words);
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
