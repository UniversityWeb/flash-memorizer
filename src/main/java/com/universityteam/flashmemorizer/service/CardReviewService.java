package com.universityteam.flashmemorizer.service;

import com.universityteam.flashmemorizer.dto.CardDTO;
import com.universityteam.flashmemorizer.dto.review.CardReview;
import com.universityteam.flashmemorizer.enums.EReview;

import java.util.List;

public interface CardReviewService<T extends CardReview> {
    List<T> generateTest(EReview eReview, List<CardDTO> cards);
    String getResult(EReview eReview, List<T> cardReviews);
}
