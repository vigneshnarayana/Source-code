package com.example.gpiproject.Viewmodel;

import android.app.Activity;
import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.example.gpiproject.Repository.WeighmentRepository;
import com.example.gpiproject.model.Weighment;
import com.example.gpiproject.model.WeighmentGet;

import java.util.List;

public class WeighmentViewModel extends AndroidViewModel {
    private WeighmentRepository weighmentRepository;

    Activity activity;

    public WeighmentViewModel(@NonNull Application application, Activity activity) {
        super(application);
        this.activity = activity;

        weighmentRepository= new WeighmentRepository(application,activity);
    }

    public List<WeighmentGet> getPurchaseList() {
        return weighmentRepository.getPurchase();
    }
    public List<Weighment> getWeighmentList(){
        return weighmentRepository.getWeighments();
    }
    public void insertWeighment(Weighment weighment){
        weighmentRepository.insertWeighment(weighment);
    }

    ///////////////////////////////////////

    public void sendWeightPostList(List<Weighment> weighment){
        weighmentRepository.sendWeightPostSync(weighment);
    }


}
