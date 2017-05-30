package com.wanari.cbexample.util.sync_gateway;

import com.wanari.cbexample.util.couchbase.CouchbaseConfiguration;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
class SyncGatewayUrls {

    private final CouchbaseConfiguration couchbaseConfiguration;

    @Value("${sync-gateway.url.placeholder.db}")
    private String dbPlaceholder;

    @Value("${sync-gateway.url.placeholder.name}")
    private String namePlaceholder;

    @Value("${sync-gateway.url.create-session}")
    private String createSession;

    @Value("${sync-gateway.url.create-or-update-user}")
    private String createOrUpdateUser;

    private SyncGatewayUrls(CouchbaseConfiguration couchbaseConfiguration) {
        this.couchbaseConfiguration = couchbaseConfiguration;
    }

    String sessionCreation() {
        return createSession.replaceAll(dbPlaceholder, couchbaseConfiguration.getBucketName());
    }

    String userCreation(String username) {
        return createOrUpdateUser
            .replaceAll(dbPlaceholder, couchbaseConfiguration.getBucketName())
            .replaceAll(namePlaceholder, username);
    }

}
