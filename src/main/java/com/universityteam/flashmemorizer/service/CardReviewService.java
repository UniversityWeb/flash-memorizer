package com.universityteam.flashmemorizer.service;

import com.universityteam.flashmemorizer.dto.CardDTO;
import com.universityteam.flashmemorizer.dto.CardReview;
import com.universityteam.flashmemorizer.enums.EReview;

import java.util.List;

public interface CardReviewService {
    List<CardReview> generateTest(EReview eReview, List<CardDTO> cards);
}
