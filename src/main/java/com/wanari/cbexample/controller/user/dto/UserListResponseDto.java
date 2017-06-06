package com.wanari.cbexample.controller.user.dto;

import com.wanari.cbexample.controller.shared.dto.SuccessfulResponseDto;

public class UserListResponseDto extends SuccessfulResponseDto {

    private String username;
    private String status;
    private Integer age;
    private AddressDtoCb address;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public AddressDtoCb getAddress() {
        return address;
    }

    public void setAddress(AddressDtoCb address) {
        this.address = address;
    }
}
