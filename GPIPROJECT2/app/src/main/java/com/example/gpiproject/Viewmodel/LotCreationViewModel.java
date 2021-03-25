package com.example.gpiproject.Viewmodel;

import android.app.Activity;
import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.gpiproject.Repository.LotCreationRepository;
import com.example.gpiproject.model.FarmerMasterGet;
import com.example.gpiproject.model.LotCreationModel;

import java.util.ArrayList;
import java.util.List;

public class LotCreationViewModel extends AndroidViewModel {
    private LotCreationRepository lotCreationRepository;
    List<LotCreationModel> lotCreationModels;
    List<FarmerMasterGet> farmerMasters;
    LiveData<LotCreationModel> getlotcount;
    LiveData<List<LotCreationModel>> getlotlivedata;

    List<Integer> baleSeries ;
    Activity activity;

    public LotCreationViewModel(@NonNull Application application,Activity activity) {
        super(application);
        lotCreationRepository=new LotCreationRepository(application,activity);
       getlotlivedata=lotCreationRepository.getlotlivedata();
    }

    public LiveData<FarmerMasterGet> getformerdetails(String formercode){
        return  lotCreationRepository.getformerdetails(formercode);
    }

    public LiveData<LotCreationModel> getlotcount(){
        return lotCreationRepository.getlotcount();
    }
//    public  LiveData<LotCreationModel> getformercount(){
//        return lotCreationRepository.getformercount();
//    }
    public List<LotCreationModel> getLotCreations(String headerId){
        lotCreationModels = lotCreationRepository.getLotCreations(headerId);
        return lotCreationModels;
    }

    public List<Integer> getBaleSeries(List<String> validList){
        baleSeries = lotCreationRepository.getBaleSeries(validList);
        return baleSeries;
    }
    public List<LotCreationModel> getAllLotCreation(){
        return lotCreationRepository.getAllLotCreation();
    }

    public List<FarmerMasterGet> getFarmerMasters(){
        farmerMasters = lotCreationRepository.getFarmerMasters();
        return farmerMasters;
    }
    public void insertLotCreation(LotCreationModel lotCreationModel){
        lotCreationRepository.insertLotCreation(lotCreationModel);
    }

    public void lotcreationpost(List<LotCreationModel> lotCreationModels){
        lotCreationRepository.lotcreationpost(lotCreationModels);
    }

    public  List<LotCreationModel> getLotcreationdata(){
        return lotCreationRepository.getLotcreationdata();
    }

    public  LiveData<List<LotCreationModel>> getlotlivedata(){
        return lotCreationRepository.getlotlivedata();
    }
}
