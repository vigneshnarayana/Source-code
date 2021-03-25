package com.example.newitcdemo.Dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.newitcdemo.Intimation;
import com.example.newitcdemo.Model.BreakDownmodel;
import com.example.newitcdemo.Model.IntimationModel;
import com.example.newitcdemo.Model.Location;
import com.example.newitcdemo.Model.Product;
import com.example.newitcdemo.Model.User;

import java.util.List;
@Dao
public interface ProductDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertProduct(Product product);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertBreakDown(BreakDownmodel breakDownmodel);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertIntimation(IntimationModel intimationModel);



    @Query("select * from product")
    public List<Product> getproductList();


    @Query("select * from breakdown")
    public List<BreakDownmodel> getBreakDownList();


    @Query("delete from breakdown")
    public void deleteAllbreakdown();

    @Query("delete from intimation")
    public void deleteAllintimation();

    @Query("select * from intimation")
    public List<IntimationModel> getIntimationList();



    @Query("Select * from locationmaster where locationCode =:locationid ")
    LiveData<List<Location>> getLocationDatails(String locationid);

    @Query("delete from product")
    public void deleteAllproduct();
}
