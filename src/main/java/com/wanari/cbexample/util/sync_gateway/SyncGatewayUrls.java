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

    @Value("${sync-gateway.url.create-document}")
    private String createDocument;

    @Value("${sync-gateway.url.create-multiple-document}")
    private String createMultipleDocument;

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

    String documentCreation() {
        return createDocument
            .replaceAll(dbPlaceholder, couchbaseConfiguration.getBucketName());
    }

    public String multipleDocumentCreation() {
        return createMultipleDocument
            .replaceAll(dbPlaceholder, couchbaseConfiguration.getBucketName());
    }
}
