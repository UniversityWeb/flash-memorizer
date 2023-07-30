package com.universityteam.flashmemorizer.dto;

import lombok.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode
@ToString
public class DeckDTO {
    private Long id;
    private String name;
    private String desc;
    private Date creation = new Date();
    private Date modified = new Date();

    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private UserDTO user = new UserDTO();

    private List<CardDTO> cards = new ArrayList<>();

    private List<SharedDeckDTO> sharedDecks = new ArrayList<>();
}
