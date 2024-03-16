package com.brainstation.BrainstationWebAPI.service;


import com.brainstation.BrainstationWebAPI.constant.AppConstant;
import com.brainstation.BrainstationWebAPI.entity.User;
import com.brainstation.BrainstationWebAPI.repository.UserRepository;
import com.brainstation.BrainstationWebAPI.web.response.UserResponse;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.*;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;

    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;
    @Autowired
    private RedisTemplate redisTemplate;

    private static final String KEY ="USER";

    public boolean updateUserMessageForKafka(String user){
        kafkaTemplate.send(AppConstant.USER_CRUD, user);
        return true;
    }

    @KafkaListener(topics = "user-crud", groupId = "user-group")
    public void userConsumer(String user){
        System.out.println(user);
    }

    @Override
    public ResponseEntity createUser(User user) throws InterruptedException {
        try {
            User _user = userRepository
                    .save(new User(user.getUsername(), user.getEmail(),user.getPassword()));
            Map<String, String> result = Map.of("message", "The user is created");
           UserResponse response = new UserResponse(_user, result);
           User lastUser = userRepository.findTopByOrderByIdDesc();
           redisTemplate.opsForHash().put(KEY, lastUser.getId().toString(), lastUser);
           updateUserMessageForKafka(lastUser.getUsername()+ " is created");
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
            updateUserMessageForKafka("Getting all the users");
            return new ResponseEntity<>(users, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<User> getUerById(@PathVariable("id") long id) {
        try{
            Optional<User> userExist;
            User userFromRedis = (User) redisTemplate.opsForHash().get(KEY, id);
            if(userFromRedis == null){
                userExist = userRepository.findById(id);
            } else{
                userExist = Optional.of(userFromRedis);
            }
            updateUserMessageForKafka("Get the user of this id= " + String.valueOf(id));
            return userExist.map(user -> new ResponseEntity<>(user, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
        }
        catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @Override
    public ResponseEntity<User> updateUerById(@PathVariable("id") long id, @RequestBody User userUpdate) {
        try{
            if (!userRepository.existsById(id)) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            Optional<User> userData = userRepository.findById(id);
            User user = userRepository.findById(id)
                    .map(existingUser -> {
                        existingUser.setUsername(userUpdate.getUsername());
                        existingUser.setEmail(userUpdate.getEmail());
                        existingUser.setPassword(userUpdate.getPassword());
                        return userRepository.save(existingUser);
                    })
                    .orElseThrow(() -> new RuntimeException("Unexpected error during user update"));
            redisTemplate.opsForHash().put(KEY, user.getId().toString(), userUpdate);
            updateUserMessageForKafka("Get the user of this id= " + String.valueOf(id));
            return ResponseEntity.ok(user);
        }
        catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }


    @Override
    public ResponseEntity<HttpStatus> deleteById(@PathVariable("id") long id) {
        try {
            userRepository.deleteById(id);
            updateUserMessageForKafka("Get the user of this id= " + String.valueOf(id));
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
