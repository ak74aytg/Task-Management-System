package com.register.securityBuffer.DTO;

public class LoginRequest {
    private String email;
    private String password;

    public String getEmail() {
        return email;
    }

    public LoginRequest() {
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LoginRequest(String email, String password) {
        this.email = email;
        this.password = password;
    }

    @Override
    public String toString() {
        return "LoginRequest{" +
                "email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
