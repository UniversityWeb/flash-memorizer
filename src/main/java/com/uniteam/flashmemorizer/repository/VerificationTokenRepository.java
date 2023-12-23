package com.uniteam.flashmemorizer.repository;

import com.uniteam.flashmemorizer.entity.User;
import com.uniteam.flashmemorizer.entity.VerificationToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VerificationTokenRepository extends JpaRepository<VerificationToken, Long> {
    VerificationToken findByToken(String token);
    Optional<VerificationToken> findByUser(User user);
}
