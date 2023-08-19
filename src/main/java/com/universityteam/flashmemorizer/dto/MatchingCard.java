package com.universityteam.flashmemorizer.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MatchingCard extends CardReview {
    private int curOrder;
    private int answerOrder;

    @Builder
    public MatchingCard(String question, boolean isCorrect, int curOrder, int answerOrder) {
        super(question, isCorrect);
        this.curOrder = curOrder;
        this.answerOrder = answerOrder;
    }
}
