package com.example.newitcdemo.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.newitcdemo.Model.Location;
import com.example.newitcdemo.Model.Login;
import com.example.newitcdemo.Model.User;
import com.example.newitcdemo.Repository.UserRepository;

import java.util.List;

public class UserViewModel extends AndroidViewModel {
private UserRepository userRepository;
    LiveData<List<Location>> getAllLocationDetails;
    public UserViewModel(@NonNull Application application) {
        super(application);
        userRepository = new UserRepository(application);
//        getAllLocationDetails=userRepository.getAllLocationDetails();

    }
//    public  LiveData<List<Location>> getAllLocationDetails(){
//        return userRepository.getAllLocationDetails();
//    }

    public List<User> getUserMasterList(){
        return userRepository.getUserDetailList();
    }

    public List<Login> getLoginDetailList(){
        return userRepository.getLoginDetailList();
    }

    public void getUserService(){
        userRepository.getUserService();
    }
    public void  getLocation(){
        userRepository.getLocation();
    }
}
