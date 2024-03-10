package com.brainstation.BrainStationAPI.service;

import com.brainstation.BrainStationAPI.entity.User;
import com.brainstation.BrainStationAPI.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;

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
}
