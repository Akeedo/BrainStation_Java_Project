package com.brainstation.BrainstationWebAPI.repository;

import com.brainstation.BrainstationWebAPI.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
}
