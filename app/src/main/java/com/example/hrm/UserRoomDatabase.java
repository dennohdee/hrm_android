package com.example.hrm;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Entity;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = UserRoom.class, exportSchema = false,version = 1)
public abstract class UserRoomDatabase extends RoomDatabase {
    private  static final  String DB_NAME = "user_db";
    private  static UserRoomDatabase instance;

    public  static synchronized UserRoomDatabase getInstance(Context context){
        if(instance == null){
            instance = Room.databaseBuilder(context.getApplicationContext(), UserRoomDatabase.class, DB_NAME)
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }
    public UserRoomDao userRoomDao;
}
