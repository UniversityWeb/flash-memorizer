package com.universityteam.flashmemorizer.dto;

import com.universityteam.flashmemorizer.enums.ERating;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserCardDTO {
    private Date lastReview;
    private Long reviewCount;
    private Long interval;
    private ERating rating;
    private CardDTO card;
    private UserDTO user;
}
