package com.example.gpiproject.Viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.gpiproject.Repository.LoginRepository;
import com.example.gpiproject.model.Header;
import com.example.gpiproject.model.Login;
import com.example.gpiproject.model.Organization;

import java.util.List;

public class LoginViewModel  extends AndroidViewModel {

    private LoginRepository loginRepository;
    private LiveData<List<Organization>> getAllOrganizationDetails;
    private LiveData<List<Login>> getAllloginDetails;
    private LiveData<List<Header>> getAllHeaderID;
    public LoginViewModel(@NonNull Application application) {
        super(application);
        loginRepository = new LoginRepository(application);
        getAllOrganizationDetails=loginRepository.getAllOrganizationDetails();
        getAllloginDetails=loginRepository.getAllloginDetails();
        getAllHeaderID=loginRepository.getAllHeaderID();
    }
    //////Room/////////////////////////////



    ///////////////////////service/////////////
    public  void getData(){
        loginRepository.getData();
    }
    public  void getvarietyservice(){
        loginRepository.getvarietyservice();
    }
    public void getfarmerservice(){
        loginRepository.getfarmerservice();
    }
    public void getheaderservice(){
        loginRepository.getheaderservice();
    }
    public void getcropservice(){
        loginRepository.getcropservice();
    }
    public  void  getorganizationservice(){
        loginRepository.getorganizationservice();
    }

    public LiveData<List<Organization>> getAllOrganizationDetails(){
        return  loginRepository.getAllOrganizationDetails();
    }
    public  LiveData<List<Header>> getAllHeaderID(){
        return loginRepository.getAllHeaderID();
    }

   public  LiveData<List<Login>> getAllloginDetails(){
        return  loginRepository.getAllloginDetails();
   }

    public List<Login> getUserMasterList(){
        return loginRepository.getUserDetailList();
    }
    public List<Organization> getOrgnMasterList(){
        return loginRepository.getOrgnDetailList();
    }
}
