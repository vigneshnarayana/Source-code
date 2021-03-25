package com.example.gpiproject.Dao;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.gpiproject.model.Crop;
import com.example.gpiproject.model.FarmerMasterGet;
import com.example.gpiproject.model.Header;
import com.example.gpiproject.model.HeaderPost;
import com.example.gpiproject.model.Login;
import com.example.gpiproject.model.LotCreationModel;
import com.example.gpiproject.model.Organization;
import com.example.gpiproject.model.Variety;


@Database(entities = {Login.class, Organization.class, Crop.class, Variety.class, FarmerMasterGet.class, Header.class, HeaderPost.class, LotCreationModel.class},version =20,exportSchema = false)
public  abstract class DataBase extends RoomDatabase {
    public  abstract LoginDao loginDao();
    public  abstract HeaderDao headerDao();
    public  abstract LotCreationDao lotCreationDao();

    public  static  volatile DataBase INSTANCE;

  public   static DataBase getDataBase(Context context){
        if(INSTANCE == null){
            synchronized (DataBase.class){
                if(INSTANCE == null){
                    INSTANCE =  Room.databaseBuilder(context, DataBase.class,"GPI.db").build();
                }
            }
        }


        return  INSTANCE;
    }

}
