package com.universityteam.flashmemorizer.keys;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;

import java.io.Serializable;

@Data
@EnableAutoConfiguration
@NoArgsConstructor
@AllArgsConstructor
public class SharedDeckId implements Serializable {
    private Long recipientId;
    private Long deckId;
}
