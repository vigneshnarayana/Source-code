package com.example.mvvmdemo.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.mvvmdemo.Repository.UserRepository;
import com.example.mvvmdemo.model.Users;

import java.util.List;

public class UserViewModel extends AndroidViewModel {

private UserRepository userRepository;
private LiveData<List<Users>>  userList;
    public UserViewModel(@NonNull Application application) {
        super(application);

        userRepository=new UserRepository(application);
        userList=userRepository.getAllUsers();

    }

        public  LiveData<List<Users>> getAllUsers(){
        return  userRepository.getAllUsers();
        }

        public  void  insertUser(Users users){
        userRepository.insertUser(users);
        }
        public  void updateUsers(Users users){
        userRepository.updateUsers(users);

        }
        public  void  deleteUsers(Users users){
        userRepository.deleteUsers(users);
        }
}
