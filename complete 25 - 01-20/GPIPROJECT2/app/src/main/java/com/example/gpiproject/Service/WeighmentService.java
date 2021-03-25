package com.example.gpiproject.Service;

import com.example.gpiproject.model.FarmerPurchaseModel;
import com.example.gpiproject.model.PurchaseModel;
import com.example.gpiproject.model.Weighment;
import com.example.gpiproject.model.WeighmentGet;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface WeighmentService {
    @GET("Weighment")
    Call<List<WeighmentGet>> getweighmentservice();

    @POST("WeighmentPost")
    Call<String> createPostSync(@Body Map<String, List<Weighment>> fields);
}
