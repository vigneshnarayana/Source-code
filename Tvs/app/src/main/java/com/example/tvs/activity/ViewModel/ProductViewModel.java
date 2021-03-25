package com.example.tvs.activity.ViewModel;

import android.app.Activity;
import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;


import com.example.tvs.activity.Model.BreakDownmodel;
import com.example.tvs.activity.Model.IntimationModel;
import com.example.tvs.activity.Model.Location;
import com.example.tvs.activity.Model.OnlinePE;
import com.example.tvs.activity.Model.Product;
import com.example.tvs.activity.Repository.ProductRepository;

import java.util.List;

public class ProductViewModel  extends AndroidViewModel {

    private ProductRepository productRepository;
    Activity activity;

    public ProductViewModel(@NonNull Application application, Activity activity) {
        super(application);

        productRepository=new ProductRepository(application,activity);
    }
    public  void insertProduct(Product product){
        productRepository.insertProduct(product);
    }

    public void insertBreakDown(BreakDownmodel breakDownmodel){
        productRepository.insertBreakDown(breakDownmodel);
    }
    public void  insertIntimation(IntimationModel intimationModel){
        productRepository.insertIntimation(intimationModel);
    }
    public  void  postProductDetails(List<Product> productList){
        productRepository.postProductDetails(productList);
    }
    public void insertOnlinePE(OnlinePE onlinePES){
        productRepository.insertOnlinePE(onlinePES);
    }
    public void postBreakDownServioce(List<BreakDownmodel> breakDownmodels){
        productRepository.postBreakDownServioce(breakDownmodels);
    }
    public void postIntimationService(List<IntimationModel> intimationModels){
        productRepository.postIntimationService(intimationModels);
    }
    public  void  postonlinepeService(List<OnlinePE> onlinePES){
        productRepository.postonlinepeService(onlinePES);
    }
    public List<Product> getproductList(){
        return productRepository.getproductList();
    }
    public List<BreakDownmodel> getBreakDownList(){
        return productRepository.getBreakDownList();
    }
    public List<IntimationModel> getIntimationList(){
        return productRepository.getIntimationList();
    }
    public List<OnlinePE> getOnlinePEList(){
        return productRepository.getOnlinePEList();
    }

    public LiveData<List<Location>> getLocationDatails(String locationid){
        return productRepository.getLocationDatails(locationid);
    }
}
