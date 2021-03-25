package com.example.gpiproject.Service;

import com.example.gpiproject.model.HeaderPost;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface HeaderService {

    @POST("headerPost")
    Call<String> headerpost(@Body Map<String, List<HeaderPost>> fields);
}
