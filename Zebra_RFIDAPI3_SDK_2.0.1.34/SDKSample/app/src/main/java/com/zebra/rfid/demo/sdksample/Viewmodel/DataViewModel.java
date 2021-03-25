package com.zebra.rfid.demo.sdksample.Viewmodel;

import android.app.Activity;
import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.zebra.rfid.demo.sdksample.Repository.DataRepository;
import com.zebra.rfid.demo.sdksample.model.Data;

import java.util.List;

public class DataViewModel extends AndroidViewModel {
    private DataRepository dataRepository;
    LiveData<List<Data>> getDatadetails;
    Activity activity;
    public DataViewModel(@NonNull Application application,Activity activity) {
        super(application);
        dataRepository=new DataRepository(application,activity);
        getDatadetails=dataRepository.getDatadetails();
    }

    public void insertData(Data data){
        dataRepository.insertData(data);
    }
    public LiveData<List<Data>> getDatadetails(){
        return dataRepository.getDatadetails();
    }
}
