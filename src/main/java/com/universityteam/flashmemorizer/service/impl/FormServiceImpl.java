package com.universityteam.flashmemorizer.service.impl;
import com.universityteam.flashmemorizer.dto.CardReviewForm;
import com.universityteam.flashmemorizer.enums.EReview;
import com.universityteam.flashmemorizer.service.FormService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@Service
public class FormServiceImpl implements FormService {
    @Override
    public String getResult(CardReviewForm form) {
        List<String> userChoices = form.getUserChoices();
        List<String> answers = form.getAnswers();
        long score = 0;
        if (form.getReviewType() == EReview.MULTI_CHOICE) {
            normalizeMultiChoiceOptions(userChoices);
        }

        else if (form.getReviewType() == EReview.FILL_BLANK) {
            userChoices = flattenList(userChoices);
            answers = flattenList(answers);
        }

        score = calcScore(userChoices, answers);
        return score + "/" + answers.size();
    }

    private void normalizeMultiChoiceOptions(List<String> list) {
        for (int i = 0; i < list.size(); i++) {
            String element = list.get(i);
            list.set(i, element.substring(0, element.length() - 3));
        }
    }

    private List<String> flattenList(List<String> initialList) {
        List<String> resultList = new ArrayList<>();

        for (String item : initialList) {
            String[] words = item.split("\\s+");
            resultList.addAll(Arrays.asList(words));
        }

        return resultList;
    }

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