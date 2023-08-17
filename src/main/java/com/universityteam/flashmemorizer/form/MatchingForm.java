package com.universityteam.flashmemorizer.form;

import com.universityteam.flashmemorizer.dto.MatchingCard;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MatchingForm extends CardReviewForm {
    private List<MatchingCard> cardReviews = new ArrayList<>();
}