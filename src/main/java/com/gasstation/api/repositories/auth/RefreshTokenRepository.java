package com.gasstation.api.repositories.auth;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;

import com.gasstation.api.model.auth.RefreshToken;
import com.gasstation.api.model.auth.User;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {

    @Override
    Optional<RefreshToken> findById(Long id);

    Optional<RefreshToken> findByToken(String token);
    
    @Modifying
    int deleteByUser(User user);
}
