package com.zebra.rfid.demo.sdksample.Dao;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.zebra.rfid.demo.sdksample.model.Data;


@Database(entities = {Data.class},version =1,exportSchema = false)
public  abstract class DataBase extends RoomDatabase {

    public  abstract  DataDao dataDao();

    public  static  volatile DataBase INSTANCE;

    public   static DataBase getDataBase(Context context){
        if(INSTANCE == null){
            synchronized (DataBase.class){
                if(INSTANCE == null){
                    INSTANCE =  Room.databaseBuilder(context, DataBase.class,"Material.db").build();
                }
            }
        }


        return  INSTANCE;
    }

}
