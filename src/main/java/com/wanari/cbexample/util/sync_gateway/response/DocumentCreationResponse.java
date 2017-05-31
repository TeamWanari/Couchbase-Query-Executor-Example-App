package com.wanari.cbexample.util.sync_gateway.response;

public class DocumentCreationResponse {
    private String id;
    private String rev;
    private Boolean ok;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRev() {
        return rev;
    }

    public void setRev(String rev) {
        this.rev = rev;
    }

    public Boolean getOk() {
        return ok;
    }

    public void setOk(Boolean ok) {
        this.ok = ok;
    }
}
