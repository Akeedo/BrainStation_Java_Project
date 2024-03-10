package com.brainstation.BrainStationAPI.controller;


import com.brainstation.BrainStationAPI.entity.JwtRequest;
import com.brainstation.BrainStationAPI.entity.User;
import com.brainstation.BrainStationAPI.service.UserService;
import com.brainstation.BrainStationAPI.utility.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin
@RequestMapping("/au th")
public class AuthController {
    @Autowired
    private UserService userService;


    @PostMapping("/login")
    public ResponseEntity login(@RequestBody User loginRequestDto) {
        return userService.login(loginRequestDto.getEmail(), loginRequestDto.getPassword());
    }
    @PostMapping("/verifyJWT")
    public ResponseEntity verifyJWT(@RequestBody JwtRequest jwtRequestDto) {
        return userService.verifyJwt(jwtRequestDto.getJwtToken());
    }
    @PostMapping("/refreshJWT")
    public ResponseEntity refreshJWT(@RequestBody JwtRequest jwtRequestDto) {
        return userService.refreshJwt(jwtRequestDto.getJwtToken());
    }
}