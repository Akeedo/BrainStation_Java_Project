package com.brainstation.BrainstationWebAPI.service;

import com.brainstation.BrainstationWebAPI.entity.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface UserService {
    ResponseEntity createUser(User user) throws InterruptedException;

    ResponseEntity<List<User>> getAllUsers();

    ResponseEntity<User> getUerById(@PathVariable("id") long id);

    ResponseEntity<HttpStatus> deleteById(@PathVariable("id") long id);

    boolean updateLocation(String user);

    void userConsumer(String user);

}
