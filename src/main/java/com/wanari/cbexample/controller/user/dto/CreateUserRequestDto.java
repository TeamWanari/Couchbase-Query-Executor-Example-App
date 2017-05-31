package com.wanari.cbexample.controller.user.dto;

import com.wanari.cbexample.domain.User;

public class CreateUserRequestDto {
    private String username;
    private String password;
    private User.Status status;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public User.Status getStatus() {
        return status;
    }

    public void setStatus(User.Status status) {
        this.status = status;
    }
}
