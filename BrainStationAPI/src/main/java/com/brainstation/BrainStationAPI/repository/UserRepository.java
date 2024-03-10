package com.brainstation.BrainStationAPI.repository;

import com.brainstation.BrainStationAPI.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
}
