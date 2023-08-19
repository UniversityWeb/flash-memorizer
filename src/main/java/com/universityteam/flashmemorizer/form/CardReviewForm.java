package com.universityteam.flashmemorizer.form;

import com.universityteam.flashmemorizer.dto.review.CardReview;
import com.universityteam.flashmemorizer.enums.EReview;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CardReviewForm<T extends CardReview> {
    protected Long deckId;
    protected String deckName;
    protected EReview reviewType;
    protected String result;
    protected boolean isSubmitted;
    protected List<T> cardReviews;
}