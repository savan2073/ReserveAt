package com.example.ReserveAt.Response;

import org.antlr.v4.runtime.Token;

public class LoginMessage {
    private String message;
    private Boolean status;
    private String token;
    private Long userId;

    public LoginMessage(String message, Boolean status, String token, Long userId) {
        this.message = message;
        this.status = status;
        this.token = token;
        this.userId = userId;
    }

    public LoginMessage(String message, Boolean status) {
        this.message = message;
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
