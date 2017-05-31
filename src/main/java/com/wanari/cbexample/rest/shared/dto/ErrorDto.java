package com.wanari.cbexample.rest.shared.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class ErrorDto {

    private Integer status;
    private String description;

    @JsonIgnore
    public Integer getStatus() {
        return status;
    }

    @JsonIgnore
    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
