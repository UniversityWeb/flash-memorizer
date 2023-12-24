package com.uniteam.flashmemorizer.form;

import com.uniteam.flashmemorizer.dto.review.FillBlankCard;
import lombok.Data;

import java.util.List;

@Data
public class FillBlankForm extends CardReviewForm<FillBlankCard> {

    @Override
    public List<FillBlankCard> getCardReviews() {
        return super.getCardReviews();
    }
}
