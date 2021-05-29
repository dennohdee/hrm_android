package com.example.hrm;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.security.PrivateKey;

@Entity(tableName = "user")
public class UserRoom {
    @PrimaryKey(autoGenerate = true)
    private int id;
    @ColumnInfo(name="name")
    private String name;


    public UserRoom(int id, String name){
        this.id = id;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }
    public String getName() {
        return name;
    }

    @Ignore
    public UserRoom(String name){
        this.name = name;
    }

}
