package com.uniteam.flashmemorizer.service.cardreview;

import com.uniteam.flashmemorizer.dto.CardDTO;
import com.uniteam.flashmemorizer.dto.review.CardReview;
import com.uniteam.flashmemorizer.customenum.EReview;

import java.util.List;

public interface CardReviewService<T extends CardReview> {
    int AT_LEAST_CARDS = 3;

    List<T> generateTest(EReview eReview, List<CardDTO> cards);
    String getResult(EReview eReview, List<T> cardReviews);
}
