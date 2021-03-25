package com.example.newitcdemo.Dao;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.newitcdemo.BreakDown;
import com.example.newitcdemo.Model.BreakDownmodel;
import com.example.newitcdemo.Model.IntimationModel;
import com.example.newitcdemo.Model.Location;
import com.example.newitcdemo.Model.Login;
import com.example.newitcdemo.Model.Product;
import com.example.newitcdemo.Model.User;


@Database(entities = {User.class, Location.class, Product.class, BreakDownmodel.class, IntimationModel.class, Login.class},version =20,exportSchema = false)
public  abstract class DataBase extends RoomDatabase {

    public  abstract   UserDao userDao();
    public  abstract   ProductDao productDao();

    public  static  volatile DataBase INSTANCE;

  public   static DataBase getDataBase(Context context){
        if(INSTANCE == null){
            synchronized (DataBase.class){
                if(INSTANCE == null){
                    INSTANCE =  Room.databaseBuilder(context, DataBase.class,"material.db").build();
                }
            }
        }


        return  INSTANCE;
    }

}
