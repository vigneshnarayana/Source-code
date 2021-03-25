package com.example.tvs.activity.Dao;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.tvs.activity.Model.BreakDownmodel;
import com.example.tvs.activity.Model.IntimationModel;
import com.example.tvs.activity.Model.Location;
import com.example.tvs.activity.Model.Login;
import com.example.tvs.activity.Model.OnlinePE;
import com.example.tvs.activity.Model.Product;
import com.example.tvs.activity.Model.User;


@Database(entities = {User.class, Location.class, Product.class, BreakDownmodel.class, IntimationModel.class, Login.class, OnlinePE.class},version =20,exportSchema = false)
public  abstract class DataBase extends RoomDatabase {

    public  abstract   UserDao userDao();
    public  abstract   ProductDao productDao();

    public  static  volatile DataBase INSTANCE;

  public   static DataBase getDataBase(Context context){
        if(INSTANCE == null){
            synchronized (DataBase.class){
                if(INSTANCE == null){
                    INSTANCE =  Room.databaseBuilder(context, DataBase.class,"TVS.db").build();
                }
            }
        }


        return  INSTANCE;
    }

}
