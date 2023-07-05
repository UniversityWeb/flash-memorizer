package com.universityteam.flashmemorizer.strategy.reviewcard;

import com.universityteam.flashmemorizer.dto.CardReview;
import com.universityteam.flashmemorizer.entity.Card;

import java.util.List;

public interface ReviewStrategy<T extends CardReview> {
    List<T> generateTest(List<Card> cards);
}