package com.universityteam.flashmemorizer.strategy.reviewcard;

import com.universityteam.flashmemorizer.enums.EReview;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * ReviewFactory - Factory for Creating Review Strategies
 * This class is a factory responsible for creating different review strategies based on the provided review type.
 * It associates each review type with a specific implementation of the ReviewStrategy interface.
 */
@Component
public class ReviewFactory {

    /**
     * A map that associates each review type (EReview) with its corresponding ReviewStrategy implementation.
     */
    private static Map<EReview, ReviewStrategy> reviewStrategies = Map.of(
            EReview.FILL_BLANK, new FillBlank(),
            EReview.MATCHING, new Matching(),
            EReview.MULTI_CHOICE, new MultiChoice()
    );

    /**
     * Creates a ReviewStrategy instance based on the provided review type.
     *
     * @param review The review type (EReview) for which a ReviewStrategy instance is to be created.
     * @return An instance of the ReviewStrategy corresponding to the provided review type.
     * @throws IllegalArgumentException If the provided review type is not found in the mapping.
     */
    public ReviewStrategy create(EReview review) {
        if (!reviewStrategies.containsKey(review)) {
            throw new IllegalArgumentException("Cannot find review type");
        }
        return reviewStrategies.get(review);
    }
}