package com.universityteam.flashmemorizer.service.impl;

import com.universityteam.flashmemorizer.form.CardReviewForm;
import com.universityteam.flashmemorizer.service.FormService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Implementation of the FormService interface.
 */
@Service
public class FormServiceImpl implements FormService {

    /**
     * Calculates the user's score based on the provided form.
     *
     * @param form The CardReviewForm containing user choices and answers.
     * @return The user's score in the format "score/total", where score is the number of correct choices and total is the number of answers.
     */
    @Override
    public String getResult(CardReviewForm form) {
        List<String> userChoices = form.getUserChoices();
        List<String> answers = form.getAnswers();
        int score = calcScore(userChoices, answers);

        return score + "/" + answers.size();
    }

    /**
     * Calculates the score by comparing user choices and answers.
     *
     * @param userChoices The list of user's selected choices.
     * @param answers     The list of correct answers.
     * @return The calculated score.
     */
    private int calcScore(List<String> userChoices, List<String> answers) {
        int score = 0;
        int size = Math.min(answers.size(), userChoices.size());

        for (int i = 0; i < size; i++) {
            if (userChoices.get(i) != null && userChoices.get(i).equalsIgnoreCase(answers.get(i))) {
                score++;
            }
        }

        return score;
    }
}