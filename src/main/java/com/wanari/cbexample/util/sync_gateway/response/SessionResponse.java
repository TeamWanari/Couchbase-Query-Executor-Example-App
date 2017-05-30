package com.wanari.cbexample.util.sync_gateway.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SessionResponse {

    private String cookieName;
    private String expires;
    private String sessionId;

    public String getCookieName() {
        return cookieName;
    }

    @JsonProperty("cookie_name")
    public void setCookieName(String cookieName) {
        this.cookieName = cookieName;
    }

    public String getExpires() {
        return expires;
    }

    @JsonProperty("expires")
    public void setExpires(String expires) {
        this.expires = expires;
    }

    public String getSessionId() {
        return sessionId;
    }

    @JsonProperty("session_id")
    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

}
