package com.universityteam.flashmemorizer.strategy.reviewform;

import com.universityteam.flashmemorizer.enums.EReview;
import com.universityteam.flashmemorizer.form.MatchingForm;

public class Matching implements ReviewFormStrategy<MatchingForm> {
    @Override
    public MatchingForm initCardReviewForm(EReview eReview) {
        return new MatchingForm();
    }
}