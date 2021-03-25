package com.example.gpiproject.Dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.RoomWarnings;

import com.example.gpiproject.model.FarmerMasterGet;
import com.example.gpiproject.model.LotCreationModel;
import com.example.gpiproject.model.LotcreationPost;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

@Dao
public interface LotCreationDao {

    @Query("SELECT * FROM farmerMaster WHERE  farmerCode=:formercode")
    LiveData<FarmerMasterGet> getformerdetails(String formercode);

    @Query("select * from lotcreation WHERE headerId = :hid")
     List<LotCreationModel> getAllLotCreation(String hid);


    @Query("select * from lotcreation ")
    LiveData<LotCreationModel> getlotcountvalue();

    @Query("select series from lotCreation WHERE lotNumber=:lotNumber AND headerId =:headerID order by series ")
     List<Integer> getFarmerSeries(Integer lotNumber,String headerID);

    @SuppressWarnings(RoomWarnings.CURSOR_MISMATCH)
//    @Query("select count( distinct baleNumber)   from lotcreation ")
//    LiveData<LotCreationModel> getformercount();


    @Query("select * from lotCreation")
     List<LotCreationModel> getAllLotCreation();

    @Query("select * from farmerMaster")
     List<FarmerMasterGet> getFarmerMaster();


    @Insert
     void insertLotCreation(LotCreationModel lotCreationModel);


    @Query("select * from lotcreation")
    List<LotCreationModel> getLotcreationdata();

    @Query("SELECT * FROM lotcreation")
    LiveData<List<LotCreationModel>> getlotlivedata();

}
