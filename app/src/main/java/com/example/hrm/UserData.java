package com.example.hrm;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class UserData {
    @SerializedName("id")
    private String id;

    @SerializedName("name")
    private String name;

    @SerializedName("email")
    private String email;

    @SerializedName("created_at")
    private String date;

    @SerializedName("data")
    private List<User> userList;

    public String getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public String getEmail() {
        return email;
    }
    public String getDate() {
        return date;
    }

    public ArrayList<User> getUserData() {
        return (ArrayList<User>) userList;
    }
}
