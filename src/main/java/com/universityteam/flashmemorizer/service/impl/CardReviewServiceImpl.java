package com.universityteam.flashmemorizer.service.impl;

import com.universityteam.flashmemorizer.dto.CardReview;
import com.universityteam.flashmemorizer.entity.Card;
import com.universityteam.flashmemorizer.enums.EReview;
import com.universityteam.flashmemorizer.service.CardReviewService;
import com.universityteam.flashmemorizer.strategy.reviewcard.ReviewFactory;
import com.universityteam.flashmemorizer.strategy.reviewcard.ReviewStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CardReviewServiceImpl implements CardReviewService {

    @Autowired
    private ReviewFactory reviewFactory;

    @Override
    public List<CardReview> generateTest(EReview eReview, List<Card> cards) {
        ReviewStrategy strategy = reviewFactory.create(eReview);
        return strategy.generateTest(cards);
    }
}
