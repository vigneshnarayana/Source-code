package com.example.gpiproject.Dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.gpiproject.model.Organization;

import java.util.List;

@Dao
public interface OrganizationDao {
//    @Query("SELECT * FROM organization")
//    LiveData<List<Organization>> getAllOrganization();

    @Insert
    void insertorganizationdetails(Organization organization);



}
