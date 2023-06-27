package com.universityteam.flashmemorizer.repository;

import com.universityteam.flashmemorizer.entity.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Repository
public interface CardRepository extends JpaRepository<Card, Long> {

    @Query(value = "SELECT * FROM cards WHERE deck_id = :deckId", nativeQuery = true)
    List<Card> findByDeckId(@PathVariable Long deckId);
}