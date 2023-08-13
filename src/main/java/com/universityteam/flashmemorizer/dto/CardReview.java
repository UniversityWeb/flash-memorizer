package com.universityteam.flashmemorizer.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class CardReview {
    private Long id;
    private String term;
    private String desc;
    private String question;
    private boolean isCorrect;
}