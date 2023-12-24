package com.uniteam.flashmemorizer.service.cardreview;

import com.uniteam.flashmemorizer.dto.CardDTO;
import com.uniteam.flashmemorizer.dto.review.CardReview;
import com.uniteam.flashmemorizer.customenum.EReview;
import com.uniteam.flashmemorizer.strategy.reviewcard.ReviewFactory;
import com.uniteam.flashmemorizer.strategy.reviewcard.ReviewStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CardReviewServiceImpl<T extends CardReview> implements CardReviewService<T> {

    @Autowired
    private ReviewFactory reviewFactory;

    @Override
    public List<T> generateTest(EReview eReview, List<CardDTO> cards) {
        ReviewStrategy<T> strategy = reviewFactory.create(eReview);
        return strategy.generateTest(cards);
    }

    @Override
    public String getResult(EReview eReview, List<T> cardReviews) {
        ReviewStrategy<T> strategy = reviewFactory.create(eReview);
        return strategy.getResult(cardReviews);
    }
}