package com.brainstation.BrainstationWebAPI.controller;


import com.brainstation.BrainstationWebAPI.entity.User;
import com.brainstation.BrainstationWebAPI.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/users")
public class UserController {
    private UserService userService;

    //Build Add User REST API
    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user){
        ResponseEntity saveUser = userService.createUser(user);
        return saveUser;
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable("id") long id) {
        return userService.getUerById(id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteUserById(@PathVariable("id") long id) {
        return userService.deleteById(id);
    }
}
