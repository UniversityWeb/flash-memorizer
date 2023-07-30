package com.universityteam.flashmemorizer.dto;

import lombok.*;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode
@ToString
public class CardDTO {
    private Long id;
    private String term;
    private String desc;
    private Date creation = new Date();
    private Date modified = new Date();
    private DeckDTO deck = new DeckDTO();
}
