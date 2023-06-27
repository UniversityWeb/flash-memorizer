package com.universityteam.flashmemorizer.repository;

import com.universityteam.flashmemorizer.entity.Deck;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeckRepository extends JpaRepository<Deck, Long> {
}
