package com.universityteam.flashmemorizer.dto;

import lombok.*;

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