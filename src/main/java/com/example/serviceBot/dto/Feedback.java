package com.example.serviceBot.dto;

public class Feedback {
    private String text;
    private String role;
    private String branch;

    public Feedback() {
    }

    public Feedback(String text, String role, String branch) {
        this.text = text;
        this.role = role;
        this.branch = branch;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }
}