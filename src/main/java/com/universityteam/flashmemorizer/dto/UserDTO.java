package com.universityteam.flashmemorizer.dto;

import com.universityteam.flashmemorizer.entity.Deck;
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
public class UserDTO {
    private Long id;
    private String username;
    private String pass;
    private String email;
    private String fullName;
    private Date registration = new Date();
    private Date lastLogin = new Date();
}
