package com.example.CST438_project3_C.data;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {com.example.CST438_project3_C.data.User.class}, version = 1)
public abstract class UserDatabase extends RoomDatabase {
    public static final String DB_NAME = "USER_DATABASE";
    public static final String USER_TABLE = "USER_TABLE";

    public abstract com.example.CST438_project3_C.data.UserDAO getUserDAO();
    private static UserDatabase INSTANCE;

    public static UserDatabase getInMemoryDatabase(Context context) {
        if (INSTANCE == null) {
            INSTANCE =
                    (UserDatabase) Room.databaseBuilder(context, UserDatabase.class, UserDatabase.DB_NAME)
                    .allowMainThreadQueries()
                    .build();
        }
        return INSTANCE;
    }

}

