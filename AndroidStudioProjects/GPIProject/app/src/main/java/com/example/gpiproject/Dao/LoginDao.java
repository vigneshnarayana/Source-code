package com.example.gpiproject.Dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.gpiproject.model.Login;
import com.example.gpiproject.model.Organization;

import java.util.List;

@Dao
public interface LoginDao {


    @Insert
    void insert(Login login);

   @Query("SELECT * FROM login")
    LiveData<List<Login>> getAllloginDetails();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertLoginDetail(Login data);


   @Query("SELECT * FROM login WHERE userName= (:username) and (password= :password)")
    Login getLogin(String username,String password);



}
