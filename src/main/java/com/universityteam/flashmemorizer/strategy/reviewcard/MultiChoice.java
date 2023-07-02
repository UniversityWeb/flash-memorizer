package com.universityteam.flashmemorizer.strategy.reviewcard;

import com.universityteam.flashmemorizer.dto.MultiChoiceCard;
import com.universityteam.flashmemorizer.entity.Card;

import java.util.List;

public class MultiChoice implements ReviewStrategy<MultiChoiceCard> {
    @Override
    public List<MultiChoiceCard> generateTest(List<Card> cards) {
        return null;
    }
}
