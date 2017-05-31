package com.wanari.cbexample.domain;

import com.couchbase.client.java.repository.annotation.Field;
import com.wanari.cbexample.util.sync_gateway.domain.SyncGatewayDocument;
import org.springframework.data.couchbase.core.mapping.Document;

@Document
public class User extends SyncGatewayDocument {

    @Field
    private String username;

    @Field
    private String password;

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

}
