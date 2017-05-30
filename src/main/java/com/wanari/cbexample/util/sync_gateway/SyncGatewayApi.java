package com.wanari.cbexample.util.sync_gateway;

import com.wanari.cbexample.util.sync_gateway.request.CreateSessionRequestBody;
import com.wanari.cbexample.util.sync_gateway.request.CreateUserRequestBody;
import com.wanari.cbexample.util.sync_gateway.response.SessionResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;

@Component
public class SyncGatewayApi {

    private final SyncGatewayRestTemplate rest;
    private final SyncGatewayHeaders headers;
    private final SyncGatewayUrls urls;

    private SyncGatewayApi(SyncGatewayRestTemplate rest, SyncGatewayHeaders headers, SyncGatewayUrls urls) {
        this.rest = rest;
        this.headers = headers;
        this.urls = urls;
    }

    public void createSyncGatewaySession(String username, String password, List<String> adminChannels, int ttl) {
        CreateSessionRequestBody body = new CreateSessionRequestBody();
        body.setName(username);
        body.setTtl(ttl);
        ResponseEntity<SessionResponse> response;

        try {
            response = rest.post(urls.sessionCreation(), body, headers.json(), SessionResponse.class);
        } catch(HttpClientErrorException e) {
            if(e.getStatusCode().value() == 404) {
                createOrUpdateUser(username, password, adminChannels);
                response = rest.post(urls.sessionCreation(), body, headers.json(), SessionResponse.class);
            } else {
                throw new RuntimeException("Couldn't create session for user " + username + " in Sync Gateway", e);
            }
        }
        headers.init(response.getBody().getSessionId());
    }

    private void createOrUpdateUser(String username, String password, List<String> adminChannels) {
        CreateUserRequestBody body = new CreateUserRequestBody();
        body.setName(username);
        body.setPassword(password);
        body.setAdminChannels(adminChannels);
        rest.put(urls.userCreation(username), body, headers.json(), SessionResponse.class);
    }

}
