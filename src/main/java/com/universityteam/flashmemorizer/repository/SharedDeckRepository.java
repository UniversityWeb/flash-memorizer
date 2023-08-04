package com.universityteam.flashmemorizer.repository;

import com.universityteam.flashmemorizer.entity.SharedDeck;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SharedDeckRepository extends JpaRepository<SharedDeck, Long> {

    @Query(value = "SELECT sd.* FROM shared_decks sd " +
            "INNER JOIN decks d ON sd.deck_id = d.id " +
            "WHERE d.user_id = :sharerId", nativeQuery = true)
    List<SharedDeck> findBySharerId(Long sharerId);
}
