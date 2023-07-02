package com.universityteam.flashmemorizer.strategy.reviewcard;

import com.universityteam.flashmemorizer.dto.CardReview;
import com.universityteam.flashmemorizer.entity.Card;

import java.util.List;

public class Matching implements ReviewStrategy<CardReview> {
    @Override
    public List<CardReview> generateTest(List<Card> cards) {
        return null;
    }
}
