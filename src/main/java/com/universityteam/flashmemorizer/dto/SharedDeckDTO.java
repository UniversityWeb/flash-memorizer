package com.universityteam.flashmemorizer.dto;

import lombok.*;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SharedDeckDTO {
    private Long id;
    private UserDTO recipient = new UserDTO();
    private DeckDTO deck = new DeckDTO();
    private Date creation = new Date();
}
