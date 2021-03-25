package com.example.mvvmdemo.Repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.example.mvvmdemo.Dao.DataBase;
import com.example.mvvmdemo.Dao.UsersDao;
import com.example.mvvmdemo.model.Users;

import java.util.List;

public class UserRepository {

    private DataBase dataBase;
    private UsersDao usersDao;
    private LiveData<List<Users>> userList;

    public UserRepository(Application application) {
        dataBase= DataBase.getDataBase(application);
        usersDao= dataBase.usersDao();
         userList= usersDao.getAllUsers();
    }

    public LiveData<List<Users>>  getAllUsers(){
        return dataBase.usersDao().getAllUsers();
    }

    public  void insertUser(final  Users users){
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                dataBase.usersDao().insertUser(users);
                return null;
            }
        }.execute();


    }

    public  void updateUsers(final  Users users){
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
              dataBase.usersDao().updateUser(users);

                return null;
            }
        }.execute();

    }

    public  void  deleteUsers(final  Users users){
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                dataBase.usersDao().deleteUser(users);
                return null;
            }
        }.execute();
    }
}
