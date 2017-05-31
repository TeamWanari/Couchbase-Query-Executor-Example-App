package com.wanari.cbexample.controller.user.dto;

import com.wanari.cbexample.controller.shared.dto.SuccessfulResponseDto;

public class UserListResponseDto extends SuccessfulResponseDto {

    private String username;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

}
