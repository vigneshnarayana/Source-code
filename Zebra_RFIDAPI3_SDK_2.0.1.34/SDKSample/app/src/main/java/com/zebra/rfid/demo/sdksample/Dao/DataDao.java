package com.zebra.rfid.demo.sdksample.Dao;


import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.zebra.rfid.demo.sdksample.model.Data;

import java.util.List;

@Dao
public interface DataDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertData(Data data);


    @Query("SELECT * FROM Data " )
    LiveData<List<Data>> getDatadetails();

}
