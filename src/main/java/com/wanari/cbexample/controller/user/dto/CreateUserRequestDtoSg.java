package com.wanari.cbexample.controller.user.dto;

import com.wanari.cbexample.domain.UserSg;

public class CreateUserRequestDtoSg {
    private String username;
    private String password;
    private UserSg.Status status;

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

    public UserSg.Status getStatus() {
        return status;
    }

    public void setStatus(UserSg.Status status) {
        this.status = status;
    }
}
