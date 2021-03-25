package com.example.materialdispatch.Dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.materialdispatch.Model.Material;

import java.util.List;

@Dao
public interface MaterialDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertMaterial(Material material);

    @Query("SELECT * FROM material " )
    LiveData<List<Material>> getMaterialData();

    @Query("SELECT * FROM material where CustomerName =:customerId" )
    LiveData<List<Material>> getMaterialDataSa(String customerId);
}
