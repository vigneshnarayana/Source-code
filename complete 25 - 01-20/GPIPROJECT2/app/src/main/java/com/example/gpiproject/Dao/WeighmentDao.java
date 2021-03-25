package com.example.gpiproject.Dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.gpiproject.model.PurchaseModel;
import com.example.gpiproject.model.Weighment;
import com.example.gpiproject.model.WeighmentGet;

import java.util.List;

@Dao
public interface WeighmentDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertweighmentget(WeighmentGet weighmentGet);

    @Query("select * from weighmentget")
    public List<WeighmentGet> getPurchaseList();

    @Query("select * from weighment")
    public List<Weighment> getWeightmentList();

    @Insert
    public void insert(Weighment weighment);

}
