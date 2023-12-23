package com.uniteam.flashmemorizer.strategy.reviewcard;

import com.uniteam.flashmemorizer.customenum.EReview;
import com.uniteam.flashmemorizer.exception.ReviewTypeNotSupportedException;
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
            throw new ReviewTypeNotSupportedException("Cannot find review type: " + review);
        }
        return reviewStrategies.get(review);
    }
}