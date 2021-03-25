package com.example.tvs.activity.Service;



import com.example.tvs.activity.Model.BreakDownmodel;
import com.example.tvs.activity.Model.IntimationModel;
import com.example.tvs.activity.Model.OnlinePE;
import com.example.tvs.activity.Model.Product;

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


    @POST("OnlinePEPost")
    Call<String> postonlinepeService(@Body Map<String, List<OnlinePE>> fields);

}
