package com.example.gpiproject.Dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.gpiproject.model.Crop;
import com.example.gpiproject.model.FarmerMasterGet;
import com.example.gpiproject.model.Header;
import com.example.gpiproject.model.Login;
import com.example.gpiproject.model.Organization;
import com.example.gpiproject.model.Variety;

import java.util.List;

@Dao
public interface LoginDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertorganization(Organization organization);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Login login);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void  insertcrop(Crop crop);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertvariety(Variety variety);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertfarmer(FarmerMasterGet farmerMasterGet);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertheader(Header header);

   @Query("SELECT * FROM login")
    LiveData<List<Login>> getAllloginDetails();

    @Query("SELECT * FROM orgainzation")
    LiveData<List<Organization>> getAllOrganizationDetails();

    @Query("select * from login")
    List<Login> getUserDetailList();


    @Query("select * from orgainzation")
    List<Organization> getOrgnDetailList();

    @Query("SELECT * FROM header")
    LiveData<List<Header>> getAllHeaderID();

}
