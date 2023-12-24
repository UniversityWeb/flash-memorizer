package com.uniteam.flashmemorizer.strategy.reviewcard;

import com.uniteam.flashmemorizer.dto.CardDTO;
import com.uniteam.flashmemorizer.dto.review.CardReview;

import java.util.List;

public interface ReviewStrategy<T extends CardReview> {
    List<T> generateTest(List<CardDTO> cards);
    String getResult(List<T> cardReviews);
}