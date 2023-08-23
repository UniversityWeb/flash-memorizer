package com.universityteam.flashmemorizer.form;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ChangePassForm {
    private Long userId;
    private String curPass = "";
    private String newPass = "";
    private String reTypeNewPass = "";
}
