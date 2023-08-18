package com.universityteam.flashmemorizer.strategy.reviewform;

import com.universityteam.flashmemorizer.enums.EReview;

public class MultiChoice implements ReviewFormStrategy<MultiChoiceForm>{
    @Override
    public MultiChoiceForm initCardReviewForm(EReview eReview) {
        return new MultiChoiceForm();
    }
}