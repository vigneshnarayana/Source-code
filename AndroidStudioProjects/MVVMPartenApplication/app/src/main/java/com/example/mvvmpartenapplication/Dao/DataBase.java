package com.example.mvvmpartenapplication.Dao;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.mvvmpartenapplication.Model.Employee;


@Database(entities = {Employee.class},version =1,exportSchema = false)
public  abstract class DataBase extends RoomDatabase {

    public  abstract   EmployeeDao employeeDao();

    public  static  volatile DataBase INSTANCE;

  public   static DataBase getDataBase(Context context){
        if(INSTANCE == null){
            synchronized (DataBase.class){
                if(INSTANCE == null){
                    INSTANCE =  Room.databaseBuilder(context,DataBase.class,"Employee.db").build();
                }
            }
        }


        return  INSTANCE;
    }

}
