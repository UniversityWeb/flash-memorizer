package com.universityteam.flashmemorizer.strategy.reviewform;

import com.universityteam.flashmemorizer.enums.EReview;
import com.universityteam.flashmemorizer.form.MultiChoiceForm;

public class MultiChoice implements ReviewFormStrategy<MultiChoiceForm>{
    @Override
    public MultiChoiceForm initCardReviewForm(EReview eReview) {
        return new MultiChoiceForm();
    }
}