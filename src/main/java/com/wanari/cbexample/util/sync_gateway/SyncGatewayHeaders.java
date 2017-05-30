package com.wanari.cbexample.util.sync_gateway;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
class SyncGatewayHeaders {

    private HttpHeaders json;
    private String sessionId;

    void init(String sessionId) {
        this.sessionId = sessionId;
        json = initFor(MediaType.APPLICATION_JSON);
    }

    HttpHeaders json() {
        return json;
    }

    private HttpHeaders initFor(MediaType contentType) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(contentType);
        List<MediaType> mediaTypes = new ArrayList<>();
        mediaTypes.add(MediaType.APPLICATION_JSON);
        httpHeaders.setAccept(mediaTypes);
        httpHeaders.set("Cookie", "SyncGatewaySession=" + sessionId);
        return httpHeaders;
    }

}
