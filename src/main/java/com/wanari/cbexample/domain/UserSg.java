package com.wanari.cbexample.domain;

import com.couchbase.client.java.document.json.JsonArray;
import com.couchbase.client.java.repository.annotation.Field;
import com.wanari.cbexample.util.sync_gateway.domain.SyncGatewayDocument;
import org.springframework.data.couchbase.core.mapping.Document;

@Document
public class UserSg extends SyncGatewayDocument {

    @Field
    private String username;

    @Field
    private String password;

    @Field
    private Status status;

    @Field
    private Integer age;

    @Field
    private AddressSg address;

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

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public AddressSg getAddress() {
        return address;
    }

    public void setAddress(AddressSg address) {
        this.address = address;
    }

    public enum Status {
        ACTIVE,
        DELETED,
        INACTIVE;

        public static String nonDeletedFilter() {
            return JsonArray
                .from(
                    ACTIVE.name(),
                    INACTIVE.name()
                )
                .toString();
        }
    }

}
