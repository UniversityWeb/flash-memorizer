package com.universityteam.flashmemorizer.strategy.reviewform;

import com.universityteam.flashmemorizer.enums.EReview;
import com.universityteam.flashmemorizer.form.FillBlankForm;

public class FillBlank implements ReviewFormStrategy<FillBlankForm> {
    @Override
    public FillBlankForm initCardReviewForm(EReview eReview) {
        return new FillBlankForm();
    }
}