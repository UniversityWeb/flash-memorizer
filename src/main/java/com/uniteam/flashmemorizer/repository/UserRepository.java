package com.uniteam.flashmemorizer.repository;

import com.uniteam.flashmemorizer.entity.User;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
    Long countById(Long id);

    User findByUsername(String username);

    @Modifying
    @Transactional
    @Query("UPDATE User u SET u.pass = :password WHERE u.id = :userId")
    void updatePasswordById(Long userId, String password);
}
