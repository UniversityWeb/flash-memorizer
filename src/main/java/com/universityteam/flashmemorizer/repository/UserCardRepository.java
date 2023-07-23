package com.universityteam.flashmemorizer.repository;

import com.universityteam.flashmemorizer.entity.UserCard;
import com.universityteam.flashmemorizer.keys.UserCardId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserCardRepository extends JpaRepository<UserCard, UserCardId> {
}
