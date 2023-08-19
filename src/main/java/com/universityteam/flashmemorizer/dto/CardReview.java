package com.universityteam.flashmemorizer.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CardReview {
    private String question;
    private boolean isCorrect;
}