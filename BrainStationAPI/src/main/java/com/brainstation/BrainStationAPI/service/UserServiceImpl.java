package com.brainstation.BrainStationAPI.service;

import com.brainstation.BrainStationAPI.entity.User;
import com.brainstation.BrainStationAPI.repository.UserRepository;
import com.brainstation.BrainStationAPI.utility.JwtUtil;
import io.jsonwebtoken.Claims;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;

    @Autowired
    private JwtUtil jwtUtil;

    @Override
    public ResponseEntity createUser(User user) {
        try {
            User _user = userRepository
                    .save(new User(user.getUsername(), user.getEmail(),user.getPassword()));
            return new ResponseEntity<>(_user, HttpStatus.CREATED);
        } catch (Exception e) {
            throw (e);
        }
    }
    @Override
    public ResponseEntity<String> login(String email, String password) {
        User user = userRepository.findByEmail(email);
        if (user != null) {
            String jwtToken = jwtUtil.generateToken(email);
            return ResponseEntity.ok(jwtToken); // Return the JWT token directly upon successful login
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
    }
    public ResponseEntity<String> verifyJwt(String jwtToken) {
        if (jwtUtil.validateToken(jwtToken)) {
            Claims claims = jwtUtil.extractClaims(jwtToken);
            String email = claims.getSubject();
            User user = userRepository.findByEmail(email);
            if (user != null) {
                return ResponseEntity.ok("Token valid");
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("User not found");
            }
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Token invalid or expired");
        }
    }

    @Override
    public ResponseEntity<String> refreshJwt(String oldJwtToken) {
        if (jwtUtil.validateToken(oldJwtToken)) {
            String newJwtToken = jwtUtil.refreshToken(oldJwtToken);
            return ResponseEntity.ok(newJwtToken); // Return the new JWT token directly upon successful refresh
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Token invalid or expired. LOGOUT");
        }
    }

    @Override
    public ResponseEntity<List<User>> getAllUsers() {
        try {
            List<User> users = new ArrayList<User>();
            users = userRepository.findAll();
            return new ResponseEntity<>(users, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<User> getUerById(@PathVariable("id") long id) {
        Optional<User> userExist = userRepository.findById(id);

        return userExist.map(user -> new ResponseEntity<>(user, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }


    @Override
    public ResponseEntity<HttpStatus> deleteById(@PathVariable("id") long id) {
        try {
            userRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
