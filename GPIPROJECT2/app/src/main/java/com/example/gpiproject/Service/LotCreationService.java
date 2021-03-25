package com.example.gpiproject.Service;

import com.example.gpiproject.model.LotCreationModel;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface LotCreationService {

    @POST("headerLotCreation")
    Call<String> lotcreationpost(@Body Map<String, List<LotCreationModel>> fields);

}
