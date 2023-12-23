package com.uniteam.flashmemorizer.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VerificationTokenDTO {
    private Long id;
    private String token;
    private Date expirationTime;
    private UserDTO user;
}
