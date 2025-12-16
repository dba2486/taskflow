package com.taskflow.domain.user;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    // email 중복 체크
    boolean existsByEmail(String email);

}
