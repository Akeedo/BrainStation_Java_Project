package com.brainstation.BrainstationWebAPI.service;

import com.brainstation.BrainstationWebAPI.entity.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;


public interface JWTService {

    String generateToken(User user);
    String extractUserName(String token);

    boolean isTokenValid(String token, UserDetails userDetails);
}
