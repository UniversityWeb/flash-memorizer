package com.universityteam.flashmemorizer.form;

import com.universityteam.flashmemorizer.dto.review.FillBlankCard;
import lombok.Data;

import java.util.List;

@Data
public class FillBlankForm extends CardReviewForm<FillBlankCard> {

    @Override
    public List<FillBlankCard> getCardReviews() {
        return super.getCardReviews();
    }
}
