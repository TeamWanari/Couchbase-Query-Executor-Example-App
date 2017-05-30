package com.wanari.cbexample.util.sync_gateway.request;

public class CreateSessionRequestBody {

    private String name;
    private int ttl;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTtl() {
        return ttl;
    }

    public void setTtl(int ttl) {
        this.ttl = ttl;
    }

}
