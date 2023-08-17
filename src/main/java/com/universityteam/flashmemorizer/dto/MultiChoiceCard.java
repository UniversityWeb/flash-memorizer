package com.universityteam.flashmemorizer.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MultiChoiceCard extends CardReview {
    private List<String> options = new ArrayList<>();
    private String answer;
    private String userChoice;
    @Builder
    public MultiChoiceCard(String question, boolean isCorrect, List<String> options, String answer, String userChoice) {
        super(question, isCorrect);
        this.options = options;
        this.answer = answer;
        this.userChoice = userChoice;
    }
}