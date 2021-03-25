package com.example.gpiproject.Dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;


import com.example.gpiproject.model.FarmerPurchaseModel;
import com.example.gpiproject.model.Header;
import com.example.gpiproject.model.ItemMaster;
import com.example.gpiproject.model.PurchaseModel;

import java.util.List;

@Dao
public interface PurchaseDao {

    @Query("Select * from header where headerID =:headerId ")
    LiveData<Header> getheadercropverity(String headerId);

    @Query("Select  * from ItemMaster where itemGrp =='BCG' ")
    LiveData<List<ItemMaster>> getitemmasterbyergrade();

    @Query("Select DISTINCT * from itemmaster where itemGrp =='CLG'   ")
    LiveData<List<ItemMaster> >getitemmasterclassificationgrade();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insertfarmerpurchase(FarmerPurchaseModel farmerPurchaseModel);

    @Query("select * from farmerpurchasemodel")
    public List<FarmerPurchaseModel> getLotCreations();


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insertPurchase(PurchaseModel purchaseModel);


    @Query("select * from purchase")
    public List<PurchaseModel> getPurchaseList();

    @Query("select count(*) from purchase WHERE rejectedStatus = '1'")
    public Integer getPurchaseRejectedList();



}
