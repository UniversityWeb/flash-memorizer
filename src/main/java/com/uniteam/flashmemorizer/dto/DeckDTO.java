package com.uniteam.flashmemorizer.dto;

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
    private UserDTO user = new UserDTO();
    private Integer quantityOfCards;
}
