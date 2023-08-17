package com.universityteam.flashmemorizer.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MatchingCard extends CardReview {
    private String displayPart;
    private String hiddenPart;
    @Builder
    public MatchingCard(String question, boolean isCorrect, String displayPart, String hiddenPart) {
        super(question, isCorrect);
        this.displayPart = displayPart;
        this.hiddenPart = hiddenPart;
    }
}