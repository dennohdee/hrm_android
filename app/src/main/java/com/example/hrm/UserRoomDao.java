package com.example.hrm;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface UserRoomDao {
    @Query("Select * from user")
    List<UserRoom> getUserRoomList();
    @Insert
    void insertUserRoom(UserRoom user);
    @Update
    void updateUserRoom(UserRoom user);
    @Delete
    void  deleteUserRoom(UserRoom user);

}
