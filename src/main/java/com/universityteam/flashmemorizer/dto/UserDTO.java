package com.universityteam.flashmemorizer.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDTO {
    private Long id;
    private String username;
    private String pass;
    private String email;
    private String fullName;
    private Date registration = new Date();
    private Date lastLogin = new Date();
    private boolean isEnable;
}
