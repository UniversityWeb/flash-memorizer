package com.universityteam.flashmemorizer.service;

import com.universityteam.flashmemorizer.dto.CardDTO;
import com.universityteam.flashmemorizer.dto.CardReview;
import com.universityteam.flashmemorizer.enums.EReview;
import com.universityteam.flashmemorizer.form.CardReviewForm;

import java.util.List;

public interface CardReviewService<T extends CardReview> {
    List<CardReview> generateTest(EReview eReview, List<CardDTO> cards);
    String getResult(EReview eReview, List<T> cardReviews);
}
