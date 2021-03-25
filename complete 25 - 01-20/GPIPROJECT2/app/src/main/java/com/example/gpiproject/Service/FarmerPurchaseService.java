package com.example.gpiproject.Service;

import com.example.gpiproject.model.FarmerMasterGet;
import com.example.gpiproject.model.FarmerPurchaseModel;


import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface FarmerPurchaseService {

    @GET("farmerpurchase")
    Call<List<FarmerPurchaseModel>> getfarmerpurchaseservice();
}
