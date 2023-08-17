package com.universityteam.flashmemorizer.service.impl;

import com.universityteam.flashmemorizer.dto.CardDTO;
import com.universityteam.flashmemorizer.dto.CardReview;
import com.universityteam.flashmemorizer.enums.EReview;
import com.universityteam.flashmemorizer.form.CardReviewForm;
import com.universityteam.flashmemorizer.service.CardReviewService;
import com.universityteam.flashmemorizer.strategy.reviewcard.ReviewFactory;
import com.universityteam.flashmemorizer.strategy.reviewcard.ReviewStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CardReviewServiceImpl<T extends CardReview> implements CardReviewService<T> {

    @Autowired
    private ReviewFactory reviewFactory;

    @Override
    public List<CardReview> generateTest(EReview eReview, List<CardDTO> cards) {
        ReviewStrategy strategy = reviewFactory.create(eReview);
        return strategy.generateTest(cards);
    }

    @Override
    public String getResult(EReview eReview, List<T> cardReviews) {
        ReviewStrategy strategy = reviewFactory.create(eReview);
        return strategy.getResult(cardReviews);
    }
}