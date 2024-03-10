package com.brainstation.BrainStationAPI.controller;

import com.brainstation.BrainStationAPI.entity.User;
import com.brainstation.BrainStationAPI.utility.JwtUtil;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {
    @PostMapping("/login")
    public String login(@RequestBody User request) {
        // Authenticate the user (e.g., using Spring Security's authentication manager)
        // If authentication is successful, generate a JWT
        String token = JwtUtil.generateToken(request.getUsername());
        return token;
    }
}