package com.universityteam.flashmemorizer.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CardDTO {
    private Long id;
    private String term;
    private String desc;
    private Date creation;
    private Date modified;
    private UserDTO user;
    private DeckDTO deck;
}
