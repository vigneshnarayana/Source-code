package com.example.mvvmpartenapplication.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.example.mvvmpartenapplication.Repository.EmployeeRepository;

public class EmloyeeViewModel extends AndroidViewModel {
    private EmployeeRepository employeeRepository;

    public EmloyeeViewModel(@NonNull Application application) {
        super(application);
        employeeRepository=new EmployeeRepository(application);
    }

    public  void getemployeeservicedetails(){
        employeeRepository.getemployeeservicedetails();
    }
}
