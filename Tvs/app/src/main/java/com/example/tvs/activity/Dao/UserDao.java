package com.example.tvs.activity.Dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;


import com.example.tvs.activity.Model.Location;
import com.example.tvs.activity.Model.Login;
import com.example.tvs.activity.Model.User;

import java.util.List;

@Dao
public interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertUsers(List<User> user);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void  insertLocation(List<Location> locations);


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void  insertLogin(List<Login> logins);

    @Query("select * from user")
    List<User> getUserDetailList();

    @Query("select * from LoginMasters1")
    List<Login> getLoginDetailList();



//    @Query("SELECT * FROM loginmaster")
//    LiveData<List<Location>> getAllLocationDetails();



}
