package com.wanari.cbexample.repository;

import com.couchbase.client.java.document.json.JsonObject;
import com.wanari.cbexample.domain.UserSg;
import com.wanari.utils.couchbase.CouchbaseQueryExecutor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepositorySg {

    private final CouchbaseQueryExecutor<UserSg> couchbaseQueryExecutor;

    public UserRepositorySg(CouchbaseQueryExecutor<UserSg> couchbaseQueryExecutor) {
        this.couchbaseQueryExecutor = couchbaseQueryExecutor;
    }

    public Page<UserSg> findAll(JsonObject filters, Pageable pageable) {
        return couchbaseQueryExecutor.find(filters, pageable, UserSg.class);
    }

}
