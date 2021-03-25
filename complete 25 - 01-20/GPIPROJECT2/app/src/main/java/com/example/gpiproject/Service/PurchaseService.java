package com.example.gpiproject.Service;

import com.example.gpiproject.model.PurchaseModel;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface PurchaseService {

    @POST("FarmerPurchasePost")
    Call<String> createPostSync(@Body Map<String, List<PurchaseModel>> fields);
}
