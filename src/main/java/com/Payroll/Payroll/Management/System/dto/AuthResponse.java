package com.Payroll.Payroll.Management.System.dto;

import com.Payroll.Payroll.Management.System.entity.User;

public class AuthResponse {
    private String accessToken;
    private User user;

    // Constructors
    public AuthResponse() {}

    public AuthResponse(String accessToken, User user) {
        this.accessToken = accessToken;
        this.user = user;
    }

    // Getters & Setters
    public String getAccessToken() { return accessToken; }
    public void setAccessToken(String accessToken) { this.accessToken = accessToken; }

    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }
}
