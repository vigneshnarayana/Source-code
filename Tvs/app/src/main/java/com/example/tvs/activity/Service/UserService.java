package com.example.tvs.activity.Service;



import com.example.tvs.activity.Model.Location;
import com.example.tvs.activity.Model.Login;
import com.example.tvs.activity.Model.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface UserService {
    @GET("userMaster")
    Call<List<User>>  getUserService();

    @GET("location")
    Call<List<Location>> getLocation();

    @GET("LoginMaster")
    Call<List<Login>> getLogin();




}
