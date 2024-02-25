package com.login.login.with.jwt.repository;

import com.login.login.with.jwt.domain.Auth;
import com.login.login.with.jwt.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AuthRepository extends JpaRepository<Auth, Long> {
    boolean existsByUser(User user);

    Optional<Auth> findByRefreshToken(String refreshToken);
}
