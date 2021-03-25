package com.example.gpiproject.Viewmodel;

import android.app.Activity;
import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.gpiproject.Dao.HeaderDao;
import com.example.gpiproject.Repository.HeaderRepository;
import com.example.gpiproject.model.Crop;
import com.example.gpiproject.model.HeaderPost;
import com.example.gpiproject.model.HeaderPostCreation;
import com.example.gpiproject.model.Login;

import java.util.List;

public class HeaderViewModel extends AndroidViewModel {
    private HeaderRepository headerRepository;
    private  LiveData<List<Crop>> getcrop;
    Activity activity;
    public HeaderViewModel(@NonNull Application application,Activity activity) {
        super(application);
        this.activity=activity;
        headerRepository=new HeaderRepository(application,activity);
        getcrop=headerRepository.getcrop();

    }
    public LiveData<Login> getusername(String byerId){
        return headerRepository.getusername(byerId);

    }
    public  void insertheader(HeaderPost headerPost){
        headerRepository.insertheader(headerPost);
    }

    public LiveData<List<Crop>> getcrop(){
        return headerRepository.getcrop();
    }

    public void headerpost(HeaderPostCreation creation){
       headerRepository.headerpost(creation.getHeaderDetails());
    }
}
