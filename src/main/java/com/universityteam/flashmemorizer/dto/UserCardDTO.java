package com.universityteam.flashmemorizer.dto;

import com.universityteam.flashmemorizer.enums.ERating;
import lombok.*;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserCardDTO {
    private Date lastReview = new Date();
    private Long reviewCount;
    private Long interval;
    private ERating rating = ERating.AVERAGE;

    private CardDTO card = new CardDTO();

    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private UserDTO user = new UserDTO();
}
