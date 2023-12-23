package com.uniteam.flashmemorizer.dto.review;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CardReview {
    private String question;
    private boolean isCorrect;
}