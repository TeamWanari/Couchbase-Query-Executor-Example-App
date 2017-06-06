package com.wanari.cbexample.domain;

import com.couchbase.client.java.document.json.JsonArray;
import com.couchbase.client.java.repository.annotation.Field;
import com.couchbase.client.java.repository.annotation.Id;
import org.springframework.data.couchbase.core.mapping.Document;

@Document
public class UserCb {

    @Id
    private String id;

    @Field
    private String username;

    @Field
    private String password;

    @Field
    private Status status;

    @Field
    private Integer age;

    @Field
    private AddressCb address;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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

    public AddressCb getAddress() {
        return address;
    }

    public void setAddress(AddressCb address) {
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
