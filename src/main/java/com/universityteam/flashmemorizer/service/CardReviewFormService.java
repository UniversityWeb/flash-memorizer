package com.universityteam.flashmemorizer.service;

import com.universityteam.flashmemorizer.enums.EReview;
import com.universityteam.flashmemorizer.form.CardReviewForm;

public interface CardReviewFormService<T extends CardReviewForm> {
    CardReviewForm initCardReviewForm(EReview eReview);
}