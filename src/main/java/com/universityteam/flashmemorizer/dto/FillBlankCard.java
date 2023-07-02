package com.universityteam.flashmemorizer.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FillBlankCard extends CardReview {
    private String blankContext;
    private List<String> fillAnswers;
    private List<String> userFills;
}
