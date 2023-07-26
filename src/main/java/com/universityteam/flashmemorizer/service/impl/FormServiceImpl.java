package com.universityteam.flashmemorizer.service.impl;
import com.universityteam.flashmemorizer.dto.CardReviewForm;
import com.universityteam.flashmemorizer.service.FormService;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class FormServiceImpl implements FormService {

    @Override
    public String getResult(CardReviewForm form) {
        long score = 0;
        List<String> userChoices = form.getUserChoices();
        List<String> answers = form.getAnswers();
        for (int i = 0; i < answers.size(); i++) {
            String userChoice = userChoices.get(i);
            if (userChoice.substring(0, userChoice.length() - 3).equals(answers.get(i))) {
                score++;
            }
        }
        return score + "/" + answers.size();
    }
}
