package com.example.materialdispatch.Dao;
import android.content.Context;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import com.example.materialdispatch.Model.Material;


@Database(entities = {Material.class},version =1,exportSchema = false)
public  abstract class DataBase extends RoomDatabase {

    public  abstract  MaterialDao materialDao();

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
