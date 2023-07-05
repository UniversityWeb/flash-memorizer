package com.universityteam.flashmemorizer.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@AllArgsConstructor
@NoArgsConstructor
public class MatchingCard extends CardReview {
    private String displayPart;
    private String hiddenPart;
    private boolean isVanished;
}
