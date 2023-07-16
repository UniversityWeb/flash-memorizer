package com.universityteam.flashmemorizer.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SharedDeckDTO {
    private Long id;
    private UserDTO sender;
    private UserDTO recipient;
    private DeckDTO deck;
    private Date creation;
}
