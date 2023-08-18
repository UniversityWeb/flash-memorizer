package com.universityteam.flashmemorizer.service.impl;

import com.universityteam.flashmemorizer.enums.EReview;
import com.universityteam.flashmemorizer.form.CardReviewForm;
import com.universityteam.flashmemorizer.service.CardReviewFormService;
import com.universityteam.flashmemorizer.strategy.reviewform.ReviewFormStrategy;
import com.universityteam.flashmemorizer.strategy.reviewform.ReviewFormFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CardReviewFormServiceImpl<T extends CardReviewForm> implements CardReviewFormService<MultiChoiceForm> {

    @Autowired
    private ReviewFormFactory reviewFormFactory;

    @Override
    public CardReviewForm initCardReviewForm(EReview eReview) {
        ReviewFormStrategy strategy = reviewFormFactory.create(eReview);
        return strategy.initCardReviewForm(eReview);
    }
}