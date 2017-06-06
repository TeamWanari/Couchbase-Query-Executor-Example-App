package com.wanari.cbexample.repository;

import com.wanari.cbexample.domain.UserCb;
import com.wanari.utils.couchbase.CouchbaseQueryExecutor;
import com.wanari.utils.couchbase.parameter.Parameters;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
public class UserListRepositoryCb {

    private final CouchbaseQueryExecutor<UserCb> couchbaseQueryExecutor;

    public UserListRepositoryCb(CouchbaseQueryExecutor<UserCb> couchbaseQueryExecutor) {
        this.couchbaseQueryExecutor = couchbaseQueryExecutor;
    }

    public Page<UserCb> findAll(Parameters params, Pageable pageable) {
        return couchbaseQueryExecutor.find(params, pageable, UserCb.class);
    }
}
