package com.example.gpiproject.Viewmodel;

import android.app.Activity;
import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.gpiproject.Repository.LotCreationRepository;
import com.example.gpiproject.model.FarmerMasterGet;
import com.example.gpiproject.model.LotCreationModel;

import java.util.List;

public class LotCreationViewModel extends AndroidViewModel {
    private LotCreationRepository lotCreationRepository;
    List<LotCreationModel> lotCreations;
    List<Integer> baleSeries;
    Activity activity;

    public LotCreationViewModel(@NonNull Application application,Activity activity) {
        super(application);
        lotCreationRepository=new LotCreationRepository(application,activity);

    }

    public LiveData<FarmerMasterGet> getformerdetails(String formercode){
        return  lotCreationRepository.getformerdetails(formercode);
    }
    public List<LotCreationModel> getLotCreations(String headerId){
        lotCreations = lotCreationRepository.getLotCreations(headerId);
        return lotCreations;
    }

    public List<Integer> getBaleSeries(List<String> validList){
        baleSeries = lotCreationRepository.getBaleSeries(validList);
        return baleSeries;
    }
    public List<LotCreationModel> getAllLotCreation(){
        return lotCreationRepository.getAllLotCreation();
    }
    public void insertLotCreation(LotCreationModel lotCreation){
        lotCreationRepository.insertLotCreation(lotCreation);
    }
}
