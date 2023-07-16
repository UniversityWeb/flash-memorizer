package com.universityteam.flashmemorizer.dto;

import lombok.*;

import java.util.Date;

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
    private Date registration;
    private Date lastLogin;
}
