package com.universityteam.flashmemorizer.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DeckDTO {
    private Long id;
    private String name;
    private String desc;
    private Date creation = new Date();
    private Date modified = new Date();
    private UserDTO user;
    private List<CardDTO> cards = new ArrayList<>();
}
