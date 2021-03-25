package com.example.gpiproject.Viewmodel;

import android.app.Activity;
import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.gpiproject.Repository.PurchaseRepository;
import com.example.gpiproject.model.FarmerPurchaseModel;
import com.example.gpiproject.model.Header;
import com.example.gpiproject.model.ItemMaster;
import com.example.gpiproject.model.PurchaseModel;

import java.util.List;

public class PurchaseViewModel extends AndroidViewModel {
    private PurchaseRepository purchaseRepository;
    private LiveData<List<ItemMaster>> getitemmasterbyergrade;
    private LiveData<List<ItemMaster>> getitemmasterclassificationgrade;
    List<FarmerPurchaseModel> farmerPurchaseModels;

    Activity activity;

    public PurchaseViewModel(@NonNull Application application, Activity activity) {
        super(application);
        this.activity = activity;
        purchaseRepository=new PurchaseRepository(application,activity);

        getitemmasterbyergrade=purchaseRepository.getitemmasterbyergrade();
        getitemmasterclassificationgrade=purchaseRepository.getitemmasterclassificationgrade();
        farmerPurchaseModels=purchaseRepository.getLotCreation();
    }

    public List<FarmerPurchaseModel> getLotCreations(){
        return farmerPurchaseModels;
    }
    public void insertPurchase(PurchaseModel purchaseModel){
        purchaseRepository.insertPurchase(purchaseModel);
    }
    public  List<PurchaseModel> getPurchaseList(){
        return purchaseRepository.getPurchaseList();
    }
    public  Integer getRejectedCount(){
        return purchaseRepository.getPurchaseRejectedList();
    }




    public LiveData<Header> getheadercropverity(String headerId){
        return purchaseRepository.getheadercropverity(headerId);
    }

    public LiveData<List<ItemMaster>> getitemmasterbyergrade(){
        return purchaseRepository.getitemmasterbyergrade();
    }

    public LiveData<List<ItemMaster>> getitemmasterclassificationgrade(){
        return purchaseRepository.getitemmasterclassificationgrade();
    }
    public void insertPurchasePostSync(List<PurchaseModel> purchases){
        purchaseRepository.sendPurchasePostSync(purchases);
    }


}
