package com.codecool.wot.model;

public class Cookie {
    private Integer userId;
    private String sessionId;

    public Cookie(Integer userId, String sessionId) {
        this.userId = userId;
        this.sessionId = sessionId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }
}
