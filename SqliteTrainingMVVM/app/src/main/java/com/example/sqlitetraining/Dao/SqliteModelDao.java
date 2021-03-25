package com.example.sqlitetraining.Dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.sqlitetraining.Model.SqliteModel;

import java.util.List;

@Dao
public interface SqliteModelDao {

    @Insert
    void Insert(SqliteModel sqliteModel);
    @Update
    void Update(SqliteModel sqliteModel);
    @Delete
    void  Delete(SqliteModel sqliteModel);

    @Query("SELECT * FROM sqliteModel")
    LiveData<List<SqliteModel>> getAllStudents();
}