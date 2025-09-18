package com.example.serviceBot.model;

public class UserSession {

    private Role role;
    private String branch;

    public UserSession() {}

    public UserSession(Role role, String branch) {
        this.role = role;
        this.branch = branch;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }
}