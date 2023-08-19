package com.universityteam.flashmemorizer.form;

import com.universityteam.flashmemorizer.dto.review.MultiChoiceCard;
import lombok.Data;

import java.util.List;

@Data
public class MultiChoiceForm extends CardReviewForm<MultiChoiceCard> {

    @Override
    public List<MultiChoiceCard> getCardReviews() {
        return super.getCardReviews();
    }
}
