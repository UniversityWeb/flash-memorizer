package com.uniteam.flashmemorizer.dto.review;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
