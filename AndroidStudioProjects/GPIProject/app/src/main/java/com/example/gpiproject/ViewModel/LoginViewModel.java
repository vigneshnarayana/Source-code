package com.example.gpiproject.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.gpiproject.Repository.LoginRepository;
import com.example.gpiproject.model.Login;
import com.example.gpiproject.model.Organization;

import java.util.List;

public class LoginViewModel  extends AndroidViewModel {

    private LoginRepository loginRepository;
    private LiveData<List<Login>> loginList;
    private LiveData<List<Organization>> getAllOrganization;
    public LoginViewModel(@NonNull Application application) {
        super(application);

        loginRepository= new LoginRepository(application);
        loginList=loginRepository.getAllLoginDetails();
//        getAllOrganization=loginRepository.getAllOrganization();
    }
    public Login getlogin(String username, String password){


        return  loginRepository.getlogin(username,password);

    }
    public void insertLoginData(Login login){
        loginRepository.insertLoginData(login);
    }
    public LiveData<List<Login>> getAllLoginDetail(){
        return  loginRepository.getAllLoginDetails();
    }

//    public LiveData<List<Organization>> getAllOrganization(){
//        return loginRepository.getAllOrganization();
//    }
    public  void insert(Login login){
        loginRepository.inser(login);
    }
//////////////////////////////////////////////////////////

   public  void getoranization(){
        loginRepository.getoranization();
   }
}
