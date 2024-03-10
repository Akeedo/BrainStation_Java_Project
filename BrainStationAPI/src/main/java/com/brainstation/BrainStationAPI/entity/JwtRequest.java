package com.brainstation.BrainStationAPI.entity;

public class JwtRequest {

    public String getJwtToken() {
        return jwtToken;
    }

    public void setJwtToken(String jwtToken) {
        this.jwtToken = jwtToken;
    }

    private String jwtToken;

}
