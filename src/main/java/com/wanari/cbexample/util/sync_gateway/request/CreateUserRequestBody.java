package com.wanari.cbexample.util.sync_gateway.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class CreateUserRequestBody {

    private String name;
    private String password;
    private List<String> adminChannels;

    @JsonProperty("name")
    public String getName() {
        return name;
    }

    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }

    @JsonProperty("password")
    public String getPassword() {
        return password;
    }

    @JsonProperty("password")
    public void setPassword(String password) {
        this.password = password;
    }

    @JsonProperty("admin_channels")
    public List<String> getAdminChannels() {
        return adminChannels;
    }

    @JsonProperty("admin_channels")
    public void setAdminChannels(List<String> adminChannels) {
        this.adminChannels = adminChannels;
    }

}
