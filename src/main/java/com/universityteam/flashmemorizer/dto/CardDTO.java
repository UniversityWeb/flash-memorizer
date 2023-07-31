package com.universityteam.flashmemorizer.dto;

import lombok.*;

import java.util.Date;
import java.util.Objects;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CardDTO {
    private Long id;
    private String term;
    private String desc;
    private Date creation = new Date();
    private Date modified = new Date();
    private DeckDTO deck = new DeckDTO();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CardDTO cardDTO = (CardDTO) o;
        return Objects.equals(term, cardDTO.term) && Objects.equals(desc, cardDTO.desc);
    }

    @Override
    public int hashCode() {
        return Objects.hash(term, desc);
    }
}
