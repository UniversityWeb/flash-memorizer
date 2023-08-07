package com.universityteam.flashmemorizer.form;

import com.universityteam.flashmemorizer.enums.EReview;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CardReviewForm {
    private Long id;
    private List<String> answers = new ArrayList<String>();
    private List<String> userChoices = new ArrayList<String>();
    private EReview reviewType;
    private long deckId;
    private String deckName;
    private String result;
}