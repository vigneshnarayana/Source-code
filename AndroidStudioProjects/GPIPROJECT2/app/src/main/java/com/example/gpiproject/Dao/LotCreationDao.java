package com.example.gpiproject.Dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.gpiproject.model.FarmerMasterGet;
import com.example.gpiproject.model.LotCreationModel;

import java.util.List;

@Dao
public interface LotCreationDao {

    @Query("SELECT * FROM farmerMaster WHERE  farmerCode=:formercode")
    LiveData<FarmerMasterGet> getformerdetails(String formercode);

    @Query("select * from lotcreation WHERE headerId = :hid")
    public List<LotCreationModel> getAllLotCreation(String hid);

    @Query("select series from lotCreation WHERE lotNumber=:lotNumber AND headerId =:headerID order by series")
    public List<Integer> getFarmerSeries(Integer lotNumber,String headerID);


    @Query("select * from lotCreation")
    public List<LotCreationModel> getAllLotCreation();



    @Insert
    public void insertLotCreation(LotCreationModel data);
}
