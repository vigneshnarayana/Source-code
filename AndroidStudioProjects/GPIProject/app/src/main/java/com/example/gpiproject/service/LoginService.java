package com.example.gpiproject.service;

import com.example.gpiproject.model.Login;
import com.example.gpiproject.model.LoginServiceModel;
import com.example.gpiproject.model.Organization;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface LoginService {



    @GET("UserMaster")
    Call<List<LoginServiceModel>> getData();

    @GET("UserMaster")
    Call<List<LoginService>> getData1(
            @Query("userId") String userID,
            @Query("_sort") String password

    );

    @GET("organizationmaster")
    Call<List<Organization>> getoranization ();


}
