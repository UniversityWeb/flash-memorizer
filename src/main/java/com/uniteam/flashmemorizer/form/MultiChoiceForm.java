package com.uniteam.flashmemorizer.form;

import com.uniteam.flashmemorizer.dto.review.MultiChoiceCard;
import lombok.Data;

import java.util.List;

@Data
public class MultiChoiceForm extends CardReviewForm<MultiChoiceCard> {

    @Override
    public List<MultiChoiceCard> getCardReviews() {
        return super.getCardReviews();
    }
}
