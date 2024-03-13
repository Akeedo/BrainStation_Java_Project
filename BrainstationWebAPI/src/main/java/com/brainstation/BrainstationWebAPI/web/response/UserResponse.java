package com.brainstation.BrainstationWebAPI.web.response;

import com.brainstation.BrainstationWebAPI.entity.User;

import java.util.Map;

public class UserResponse {
    private User user;
    private Map<String, String> message;

    public UserResponse(User user, Map<String, String> message) {
        this.user = user;
        this.message = message;
    }

    // Getters and Setters
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Map<String, String> getMessage() {
        return message;
    }

    public void setMessage(Map<String, String> message) {
        this.message = message;
    }
}
