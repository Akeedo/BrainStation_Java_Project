package com.brainstation.BrainStationAPI.service;

import com.brainstation.BrainStationAPI.entity.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface UserService {
    ResponseEntity createUser(User user);

    ResponseEntity<String> refreshJwt(String oldJwtToken);

    ResponseEntity<List<User>> getAllUsers();

    ResponseEntity<User> getUerById(@PathVariable("id") long id);

    ResponseEntity<HttpStatus> deleteById(@PathVariable("id") long id);

    ResponseEntity<String> login(String email, String password);

    ResponseEntity<String>  verifyJwt(String jwtToken);
}
