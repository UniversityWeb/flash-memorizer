package com.universityteam.flashmemorizer.form;

import com.universityteam.flashmemorizer.enums.EReview;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CardReviewForm {
    private Long deckId;
    private String deckName;
    private EReview reviewType;
    private String result;
    private boolean isSubmitted;
}