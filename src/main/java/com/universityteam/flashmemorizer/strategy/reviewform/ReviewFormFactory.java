package com.universityteam.flashmemorizer.strategy.reviewform;

import com.universityteam.flashmemorizer.enums.EReview;
import com.universityteam.flashmemorizer.exception.ReviewTypeNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class ReviewFormFactory {
    private static Map<EReview, ReviewFormStrategy> reviewStrategies = Map.of(
            EReview.FILL_BLANK, new FillBlank(),
            EReview.MATCHING, new Matching(),
            EReview.MULTI_CHOICE, new MultiChoice()
    );

    public ReviewFormStrategy create(EReview review) {
        if (!reviewStrategies.containsKey(review)) {
            throw new ReviewTypeNotFoundException("Cannot find review type: " + review);
        }
        return reviewStrategies.get(review);
    }
}
