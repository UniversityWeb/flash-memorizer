package com.universityteam.flashmemorizer.strategy.reviewcard;

import com.universityteam.flashmemorizer.enums.EReview;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class ReviewFactory {

    private static Map<EReview, ReviewStrategy> reviewStrategies = Map.of(
            EReview.FILL_BLANK, new FillBlank(),
            EReview.MATCHING, new Matching(),
            EReview.MULTI_CHOICE, new MultiChoice()
    );

    public ReviewStrategy create(EReview review) {
        if (!reviewStrategies.containsKey(review)) {
            throw new IllegalArgumentException("Cannot find review type");
        }
        return reviewStrategies.get(review);
    }
}
