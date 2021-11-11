package com.example.appsales20072021.model;

public class UserModel {
    public String userId;
    public String fullName;
    public String email;
    public String phone;
    public String address;
    public String role;
    public String createdAt;
    public String updatedAt;
    public String token;

    @Override
    public String toString() {
        return "UserModel{" +
                "userId='" + userId + '\'' +
                ", fullName='" + fullName + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", address='" + address + '\'' +
                ", role='" + role + '\'' +
                ", createdAt='" + createdAt + '\'' +
                ", updatedAt='" + updatedAt + '\'' +
                ", token='" + token + '\'' +
                '}';
    }
}
