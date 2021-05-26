package com.example.hrm;

public class User {
    private String name;
    private String email;
    private String date;
    public User(String name, String email, String date) {
        this.name = name;
        this.date = date;
        this.email = email;
    }
    public String getName() {
        return name;
    }
    public String getDate() {
        return date;
    }
    public String getEmail() {
        return email;
    }
}
