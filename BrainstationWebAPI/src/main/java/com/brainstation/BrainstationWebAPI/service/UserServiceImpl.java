package com.brainstation.BrainstationWebAPI.service;


import com.brainstation.BrainstationWebAPI.constant.AppConstant;
import com.brainstation.BrainstationWebAPI.entity.User;
import com.brainstation.BrainstationWebAPI.repository.UserRepository;
import com.brainstation.BrainstationWebAPI.web.response.UserResponse;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.*;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;

    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;

    public boolean updateLocation(String user){
        kafkaTemplate.send(AppConstant.USER_SAVE, user);
        return true;
    }

    @KafkaListener(topics = "user-save", groupId = "user-group")
    public void userConsumer(String user){
        System.out.println(user);
    }

    @Override
    public ResponseEntity createUser(User user) throws InterruptedException {
        try {
            User _user = userRepository
                    .save(new User(user.getUsername(), user.getEmail(),user.getPassword()));
            Map<String, String> result = Map.of("message", "The user is created");
            Thread.sleep(1000);
            UserResponse response = new UserResponse(_user, result);

            int range = 100;
            while (range > 0){
                updateLocation(Math.random() + " , " + Math.random());
                range--;
            }

            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (Exception e) {
            throw (e);
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
