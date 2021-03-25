package com.example.materialdispatch.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.materialdispatch.Model.Material;
import com.example.materialdispatch.Repository.MaterialRepository;

import java.util.List;

public class MaterialViewModel extends AndroidViewModel {
    LiveData<List<Material>> getMaterialData;
    private MaterialRepository materialRepository;
    public MaterialViewModel(@NonNull Application application) {
        super(application);

        materialRepository= new MaterialRepository(application);
       getMaterialData=materialRepository.getMaterialData();
    }
    public LiveData<List<Material>> getMaterialData(){
        return  materialRepository.getMaterialData();
    }

    public LiveData<List<Material>> getMaterialDataSa(String Customer){
        return materialRepository.getMaterialDataSa(Customer);
    }
    public  void insertMaterial(Material material){
        materialRepository.insertMaterial(material);
    }
}
