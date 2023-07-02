package com.universityteam.flashmemorizer.strategy.reviewcard;

import com.universityteam.flashmemorizer.dto.FillBlankCard;
import com.universityteam.flashmemorizer.entity.Card;

import java.util.List;

public class FillBlank implements ReviewStrategy<FillBlankCard> {
    @Override
    public List<FillBlankCard> generateTest(List<Card> cards) {
        return null;
    }
}
