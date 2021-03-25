package com.example.mvvmdemo.Dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.mvvmdemo.model.Users;

import java.util.List;

@Dao
public interface UsersDao {

    @Insert
    void insertUser(Users users);
    @Update
    void updateUser(Users users);
    @Delete
    void  deleteUser(Users users);

    @Query("SELECT * FROM users")
    LiveData<List<Users>> getAllUsers();


}
