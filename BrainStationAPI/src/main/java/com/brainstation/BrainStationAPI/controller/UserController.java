package com.brainstation.BrainStationAPI.controller;

import com.brainstation.BrainStationAPI.entity.User;
import com.brainstation.BrainStationAPI.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
