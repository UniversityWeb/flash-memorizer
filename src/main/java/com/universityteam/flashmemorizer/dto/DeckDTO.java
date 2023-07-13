package com.universityteam.flashmemorizer.dto;

import com.universityteam.flashmemorizer.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeckDTO {
    private Long id;
    private String name;
    private String desc;
    private Date creation;
    private Date modified;
    private User user;
}
