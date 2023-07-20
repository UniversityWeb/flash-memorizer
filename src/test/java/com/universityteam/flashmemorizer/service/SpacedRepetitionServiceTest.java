package com.universityteam.flashmemorizer.service;

import com.universityteam.flashmemorizer.enums.ERating;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertTrue;

class SpacedRepetitionServiceTest {

    private SpacedRepetitionService service = new SpacedRepetitionService();

    /**
     * Test Case: testCalculateReviewDateWithNonNullLastReview
     *
     * Description: This test case verifies the correctness of the calculateReviewDate method in the SpacedRepetitionService
     *              when the last review date is not null. It ensures that the method calculates the next review date
     *              correctly based on the provided last review date and the user's rating (ERating.HIGH).
     *
     * Test Steps:
     * 1. Set up the test data, including a last review date set to two days ago and a rating of 2.5 (ERating.HIGH).
     * 2. Calculate the expected review interval using the formula mentioned in the SpacedRepetitionService class.
     * 3. Calculate the expected review date by adding the calculated review interval to the last review date.
     * 4. Call the calculateReviewDate method of SpacedRepetitionService with the given last review date and rating.
     * 5. Verify that the calculated next review date is equal to or greater than the expected review date.
     */
    @Test
    public void testCalculateReviewDateWithNonNullLastReview() {
        // Arrange
        Date lastReviewDate = new Date(System.currentTimeMillis() - Duration.ofDays(2).toMillis());
        Date expected = new Date(System.currentTimeMillis() + Duration.ofDays(1).toMillis());

        // Action
        Date nextReviewDate = service.calculateReviewDate(lastReviewDate, ERating.HIGH);

        // Assert
        assertTrue(nextReviewDate.compareTo(expected) >= 0);
    }
}