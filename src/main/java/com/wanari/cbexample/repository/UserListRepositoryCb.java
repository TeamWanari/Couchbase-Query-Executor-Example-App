package com.wanari.cbexample.repository;

import com.couchbase.client.java.document.json.JsonObject;
import com.wanari.cbexample.domain.UserCb;
import com.wanari.utils.couchbase.CouchbaseQueryExecutor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
public class UserListRepositoryCb {

    private final CouchbaseQueryExecutor<UserCb> couchbaseQueryExecutor;

    public UserListRepositoryCb(CouchbaseQueryExecutor<UserCb> couchbaseQueryExecutor) {
        this.couchbaseQueryExecutor = couchbaseQueryExecutor;
    }

    public Page<UserCb> findAll(JsonObject filters, Pageable pageable) {
        return couchbaseQueryExecutor.find(filters, pageable, UserCb.class);
    }
}
