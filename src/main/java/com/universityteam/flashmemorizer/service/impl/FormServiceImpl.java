package com.universityteam.flashmemorizer.service.impl;

import com.universityteam.flashmemorizer.form.CardReviewForm;
import com.universityteam.flashmemorizer.enums.EReview;
import com.universityteam.flashmemorizer.service.FormService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * FormServiceImpl - Implementation of the FormService Interface
 * This class provides an implementation for the FormService interface.
 * It calculates the score based on the user's choices and correct answers from the CardReviewForm object.
 */
@Service
public class FormServiceImpl implements FormService {

    /**
     * Calculates the score for the given CardReviewForm.
     * The score is based on the user's choices and correct answers for the flashcards in the form.
     * For MULTI_CHOICE review type, it normalizes the user choices by removing the unnecessary characters at the beginning.
     * For FILL_BLANK review type, it flattens the user choices and correct answers lists to determine the score correctly.
     *
     * @param form The CardReviewForm containing user choices and correct answers.
     * @return A string representing the calculated score in the format "score/total" (e.g., "8/10").
     */
    @Override
    public String getResult(CardReviewForm form) {
        List<String> userChoices = form.getUserChoices();
        List<String> answers = form.getAnswers();
        long score = 0;

        if (form.getReviewType() == EReview.MULTI_CHOICE) {
            normalizeMultiChoiceOptions(userChoices);
        } else if (form.getReviewType() == EReview.FILL_BLANK) {
            userChoices = flattenList(userChoices);
            answers = flattenList(answers);
        }

        score = calcScore(userChoices, answers);
        return score + "/" + answers.size();
    }

    /**
     * Normalizes the multi-choice options by removing the error char from frontend (e.g., "Option A" has char ",,," at the end).
     *
     * @param list The list of multi-choice options to be normalized.
     */
    private void normalizeMultiChoiceOptions(List<String> list) {
        for (int i = 0; i < list.size(); i++) {
            String element = list.get(i);
            int countErrChars = 0;
            for (int j = element.length() - 1; j >= 0; j--) {
                if (element.charAt(j) == ',') {
                    countErrChars++;
                }
                else {
                    break;
                }
            }
            if (element.endsWith(",")) {
                list.set(i, element.substring(0, element.length() - countErrChars));
            }
        }
    }

    /**
     * Flattens a list of strings into a single list of words.
     *
     * @param initialList The initial list of strings to be flattened.
     * @return A flattened list of words from the initial list of strings.
     */
    private List<String> flattenList(List<String> initialList) {
        List<String> resultList = new ArrayList<>();

        for (String item : initialList) {
            String[] words = item.split("\\s+");
            resultList.addAll(Arrays.asList(words));
        }

        return resultList;
    }

    /**
     * Calculates the score by comparing user choices with correct answers.
     * The score is the count of correct choices in the user choices list.
     *
     * @param userChoices The list of user choices.
     * @param answers     The list of correct answers.
     * @return The calculated score as a long integer.
     */
    private long calcScore(List<String> userChoices, List<String> answers) {
        long score = 0;

        for (int i = 0; i < answers.size(); i++) {
            String userChoice = userChoices.get(i);
            if (userChoice.equalsIgnoreCase(answers.get(i))) {
                score++;
            }
        }
        return score;
    }
}