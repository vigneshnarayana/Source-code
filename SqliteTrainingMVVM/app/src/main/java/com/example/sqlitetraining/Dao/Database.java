package com.example.sqlitetraining.Dao;

import android.content.Context;

import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.sqlitetraining.Model.SqliteModel;

@androidx.room.Database(entities = {SqliteModel.class},version = 1,exportSchema = false)
public abstract class Database extends RoomDatabase {
    public  static  volatile  Database INSTANCE;
    public abstract  SqliteModelDao sqliteModelDao();
    public   static  Database getDataBase(Context context){
        if(INSTANCE == null){
            synchronized (androidx.room.Database.class){
                if(INSTANCE == null){
                    INSTANCE = Room.databaseBuilder(context,Database.class,"Student.db").build();
                }
            }
        }


        return INSTANCE;
    }

}
