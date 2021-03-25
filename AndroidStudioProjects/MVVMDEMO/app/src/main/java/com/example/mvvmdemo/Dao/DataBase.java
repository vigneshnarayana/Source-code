package com.example.mvvmdemo.Dao;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.mvvmdemo.model.Users;

@Database(entities = {Users.class},version = 1,exportSchema = false)
public  abstract class DataBase extends RoomDatabase {
    public  abstract UsersDao usersDao();
    public  static  volatile  DataBase INSTANCE;

  public   static  DataBase getDataBase(Context context){
        if(INSTANCE == null){
            synchronized (Database.class){
                if(INSTANCE == null){
                    INSTANCE = Room.databaseBuilder(context,DataBase.class,"Users.db").build();
                }
            }
        }


        return INSTANCE;
    }

}
