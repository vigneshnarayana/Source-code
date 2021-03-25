package com.example.newitcdemo.Service;

import com.example.newitcdemo.Model.Location;
import com.example.newitcdemo.Model.Login;
import com.example.newitcdemo.Model.User;

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
