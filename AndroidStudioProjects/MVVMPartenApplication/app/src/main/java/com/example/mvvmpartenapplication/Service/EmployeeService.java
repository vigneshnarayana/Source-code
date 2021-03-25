package com.example.mvvmpartenapplication.Service;

import com.example.mvvmpartenapplication.Model.Employee;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface EmployeeService {

    @GET("usermaster")
    Call<List<Employee>> getemployeeservicedetails();

}
