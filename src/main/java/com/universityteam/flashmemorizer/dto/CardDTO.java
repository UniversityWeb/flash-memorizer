package com.universityteam.flashmemorizer.dto;

import lombok.*;
import org.apache.tomcat.util.codec.binary.Base64;

import java.util.Objects;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode
@ToString
public class CardDTO {
    private Long id;
    private String term;
    private String desc;
    private byte[] img;

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

    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private DeckDTO deck = new DeckDTO();

    public String generateBase64Image()
    {
        return Base64.encodeBase64String(this.img);
    }
}
