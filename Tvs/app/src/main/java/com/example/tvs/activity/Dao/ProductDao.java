package com.example.tvs.activity.Dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;


import com.example.tvs.activity.Model.BreakDownmodel;
import com.example.tvs.activity.Model.IntimationModel;
import com.example.tvs.activity.Model.Location;
import com.example.tvs.activity.Model.OnlinePE;
import com.example.tvs.activity.Model.Product;

import java.util.List;

@Dao
public interface ProductDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertProduct(Product product);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertBreakDown(BreakDownmodel breakDownmodel);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertIntimation(IntimationModel intimationModel);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertOnlinePE(OnlinePE onlinePE);



    @Query("select * from product")
    public List<Product> getproductList();


    @Query("select * from breakdown")
    public List<BreakDownmodel> getBreakDownList();


    @Query("delete from breakdown")
    public void deleteAllbreakdown();

    @Query("delete from intimation")
    public void deleteAllintimation();

    @Query("delete from onlinepe")
    public void deleteAllonlinPe();

    @Query("select * from intimation")
    public List<IntimationModel> getIntimationList();

    @Query("select * from onlinepe")
    public List<OnlinePE> getOnlinePEList();



    @Query("Select * from locationmaster where locationCode =:locationid ")
    LiveData<List<Location>> getLocationDatails(String locationid);

    @Query("delete from product")
    public void deleteAllproduct();
}
