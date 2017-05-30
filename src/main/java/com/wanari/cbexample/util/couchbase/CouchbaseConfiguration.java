package com.wanari.cbexample.util.couchbase;

import com.wanari.utils.couchbase.CouchbaseQueryExecutorConfiguration;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.couchbase.config.AbstractCouchbaseConfiguration;

import java.util.Collections;
import java.util.List;

//@EnableCouchbaseRepositories("com.wanari.cbexample.repository") TODO
@Configuration
public class CouchbaseConfiguration extends AbstractCouchbaseConfiguration implements CouchbaseQueryExecutorConfiguration {

    @Value("${couchbase.bucket}")
    private String bucketName;

    @Value("${couchbase.password}")
    private String password;

    @Value("${couchbase.ip}")
    private String ip;

    @Override
    public String getBucketName() {
        return this.bucketName;
    }

    @Override
    protected String getBucketPassword() {
        return this.password;
    }

    @Override
    protected List<String> getBootstrapHosts() {
        return Collections.singletonList(this.ip);
    }

}
