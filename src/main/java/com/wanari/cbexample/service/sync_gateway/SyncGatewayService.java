package com.wanari.cbexample.service.sync_gateway;

import com.wanari.cbexample.util.sync_gateway.SyncGatewayApi;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;

@Service
public class SyncGatewayService {

    private final int ttl = Integer.MAX_VALUE;

    @Value("${sync-gateway.username}")
    private String username;

    @Value("${sync-gateway.password}")
    private String password;

    @Value("#{'${sync-gateway.admin-channels}'.split(',')}")
    private List<String> adminChannels;

    private final SyncGatewayApi syncGatewayApi;

    private SyncGatewayService(SyncGatewayApi syncGatewayApi) {
        this.syncGatewayApi = syncGatewayApi;
    }

    @PostConstruct
    private void init() {
        syncGatewayApi.createSyncGatewaySession(username, password, adminChannels, ttl);
    }

}
