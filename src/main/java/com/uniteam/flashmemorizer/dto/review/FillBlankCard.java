package com.uniteam.flashmemorizer.dto.review;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FillBlankCard extends CardReview {
    private String descWithBlanks;
    private List<String> hideTexts = new ArrayList<>();
    private List<String> userFills = new ArrayList<>();

    @Builder
    public FillBlankCard(String question, boolean isCorrect,
                         String descWithBlanks, List<String> hideTexts, List<String> userFills) {
        super(question, isCorrect);
        this.descWithBlanks = descWithBlanks;
        this.hideTexts = hideTexts;
        this.userFills = userFills;
    }
}