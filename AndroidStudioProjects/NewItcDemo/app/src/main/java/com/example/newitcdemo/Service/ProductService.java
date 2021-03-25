package com.example.newitcdemo.Service;

import com.example.newitcdemo.Model.BreakDownmodel;
import com.example.newitcdemo.Model.IntimationModel;
import com.example.newitcdemo.Model.Product;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ProductService {
    @POST("ProductivityPost")
    Call<String> postProductDetails(@Body Map<String, List<Product>> fields);

    @POST("BreakdownPost")
    Call<String> postBreakDownServioce(@Body Map<String, List<BreakDownmodel>> fields);

    @POST("intimationPost")
    Call<String> postIntimationService(@Body Map<String, List<IntimationModel>> fields);

}
