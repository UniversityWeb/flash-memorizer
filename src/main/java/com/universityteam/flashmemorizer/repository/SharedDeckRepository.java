package com.universityteam.flashmemorizer.repository;

import com.universityteam.flashmemorizer.entity.SharedDeck;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SharedDeckRepository extends JpaRepository<SharedDeck, Long> {
    List<SharedDeck> findBySenderId(Long senderId);
}
