package com.universityteam.flashmemorizer.dto;

import lombok.*;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SharedDeckDTO {
    private Long id;

    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private UserDTO sender = new UserDTO();

    private UserDTO recipient = new UserDTO();

    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private DeckDTO deck = new DeckDTO();

    private Date creation = new Date();
}
