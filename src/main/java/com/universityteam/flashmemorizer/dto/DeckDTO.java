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
public class DeckDTO {
    private Long id;
    private String name;
    private String desc;
    private Date creation;
    private Date modified;
    private UserDTO user;
}
