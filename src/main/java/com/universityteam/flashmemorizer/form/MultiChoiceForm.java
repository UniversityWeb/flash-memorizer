package com.universityteam.flashmemorizer.form;

import com.universityteam.flashmemorizer.dto.MultiChoiceCard;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MultiChoiceForm extends CardReviewForm {
    private List<MultiChoiceCard> cardReviews = new ArrayList<>();
}