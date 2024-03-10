package com.brainstation.BrainStationAPI.service;

import com.brainstation.BrainStationAPI.entity.User;
import org.springframework.http.ResponseEntity;

public interface UserService {
    ResponseEntity createUser(User user);

}
