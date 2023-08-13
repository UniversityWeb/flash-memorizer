package com.universityteam.flashmemorizer.form;

import com.universityteam.flashmemorizer.dto.MultiChoiceCard;
import com.universityteam.flashmemorizer.enums.EReview;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class MultiChoiceForm {
    private Long deckId;
    private String deckName;
    private EReview reviewType;
    private String result;
    private List<MultiChoiceCard> cardReviews = new ArrayList<>();
}