package com.universityteam.flashmemorizer.service;

import com.universityteam.flashmemorizer.enums.ERating;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Date;

@Service
public class SpacedRepetitionService {

    private final Integer INITIAL_INTERVAL_HOURS = 1;
    private final int MINIMUM_INTERVAL_HOURS = 24;

    /**
     * Calculate the next review date for a piece of information.
     *
     * @param lastReview The date of the last review of the information.
     * @param rating The user's rating for the recall performance of the information.
     *              The rating provides an "ease factor" to adjust the next review interval.
     * @return The date for the next review.
     */
    public Date calculateReviewDate(Date lastReview, ERating rating) {
        long reviewIntervalHours = getReviewInterval(lastReview, rating);
        LocalDateTime nextReviewDateTime = LocalDateTime.now().plus(Duration.ofHours(reviewIntervalHours));
        return java.sql.Timestamp.valueOf(nextReviewDateTime);
    }

    /**
     * Calculate the next review interval for a piece of information.
     *
     * @param lastReview The date of the last review of the information.
     * @param rating The user's rating for the recall performance of the information.
     *              The rating provides an "ease factor" to adjust the next review interval.
     * @return The next review interval in hours.
     */
    private long getReviewInterval(Date lastReview, ERating rating) {
        if (lastReview == null) {
            return INITIAL_INTERVAL_HOURS;
        }

        double easeFactor = rating.getEaseFactor();
        long hoursLastReview = Duration.between(lastReview.toInstant(), Instant.now()).toHours();
        double nextIntervalInHours = INITIAL_INTERVAL_HOURS *
                Math.pow(easeFactor, hoursLastReview / MINIMUM_INTERVAL_HOURS);
        return Math.round(Math.max(nextIntervalInHours, MINIMUM_INTERVAL_HOURS));
    }
}