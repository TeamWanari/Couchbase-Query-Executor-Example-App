package com.wanari.cbexample.controller.user.dto;

import com.wanari.cbexample.domain.UserCb;

public class CreateUserRequestDtoCb {
    private String username;
    private String password;
    private Integer age;
    private UserCb.Status status;
    private AddressDtoCb address;

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

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public UserCb.Status getStatus() {
        return status;
    }

    public void setStatus(UserCb.Status status) {
        this.status = status;
    }

    public AddressDtoCb getAddress() {
        return address;
    }

    public void setAddress(AddressDtoCb address) {
        this.address = address;
    }
}
