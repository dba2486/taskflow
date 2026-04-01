package com.taskflow.domain.user;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    // email 중복 체크
    boolean existsByEmail(String email);

    // email 조회
    Optional<User> findByEmail(String email);
}
