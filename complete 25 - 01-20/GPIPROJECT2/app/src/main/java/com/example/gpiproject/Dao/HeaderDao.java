package com.example.gpiproject.Dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.gpiproject.model.Crop;
import com.example.gpiproject.model.HeaderPost;
import com.example.gpiproject.model.Login;

import java.util.List;

@Dao
public interface HeaderDao {
    @Query("SELECT * FROM login WHERE userID=:byerId")
    LiveData<Login> getusername(String byerId);

    @Query("SELECT * FROM crop")
    LiveData<List<Crop>> getcrop();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertheader(HeaderPost headerPost);
}
