package com.example.gpiproject.Service;

import com.example.gpiproject.model.Crop;
import com.example.gpiproject.model.FarmerMasterGet;
import com.example.gpiproject.model.Header;
import com.example.gpiproject.model.ItemMaster;
import com.example.gpiproject.model.Login;
import com.example.gpiproject.model.Organization;
import com.example.gpiproject.model.Variety;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface LoginService {


   @GET("organizationmaster")
   Call<List<Organization>> getorganizationservice();

   @GET("crop")
   Call<List<Crop>> getcropservice();

    @GET("UserMaster")
    Call<List<Login>> getData();
    @GET("Variety")
    Call<List<Variety>> getvarietyservice();

    @GET("FarmerMasterGet")
    Call<List<FarmerMasterGet>> getfarmerservice();

    @GET("HeaderId")
    Call<List<Header>> getheaderservice();

    @GET("itemmaster")
    Call<List<ItemMaster>> getitemmaster();

//    @GET("UserMaster")
//    Call<List<LoginService>> getData1(
//            @Query("userId") String userID,
//            @Query("_sort") String password
//
//    );

    @GET("organizationmaster")
    Call<List<Login>> getoranization ();


}
