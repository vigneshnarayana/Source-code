package com.example.gpiproject.service;

import com.example.gpiproject.model.TestTable;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface TestService {
    @POST("TesttableData")
    Call<String> createpost(@Body Map<String, List<TestTable>> fields);
}
