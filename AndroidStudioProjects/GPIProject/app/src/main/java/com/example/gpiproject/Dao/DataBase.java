package com.example.gpiproject.Dao;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.gpiproject.model.Login;
import com.example.gpiproject.model.TestTable;


@Database(entities = {Login.class, TestTable.class},version = 1,exportSchema = false)
public  abstract class DataBase extends RoomDatabase {
    public  abstract LoginDao loginDao();
    public  abstract TestDao testDao();
    public  abstract OrganizationDao organizationDao();
    public  static  volatile  DataBase INSTANCE;

  public   static DataBase getDataBase(Context context){
        if(INSTANCE == null){
            synchronized (DataBase.class){
                if(INSTANCE == null){
                    INSTANCE =  Room.databaseBuilder(context,DataBase.class,"GPI.db").build();
                }
            }
        }


        return  INSTANCE;
    }

}
