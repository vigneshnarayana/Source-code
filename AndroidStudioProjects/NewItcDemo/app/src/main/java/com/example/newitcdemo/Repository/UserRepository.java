package com.example.newitcdemo.Repository;

import android.app.Application;
import android.os.AsyncTask;
import android.util.Log;

import androidx.lifecycle.LiveData;

import com.example.newitcdemo.Dao.DataBase;
import com.example.newitcdemo.Dao.UserDao;
import com.example.newitcdemo.Model.Location;
import com.example.newitcdemo.Model.Login;
import com.example.newitcdemo.Model.User;
import com.example.newitcdemo.Service.RetrofitClientInstance;
import com.example.newitcdemo.Service.UserService;

import java.util.List;
import java.util.concurrent.ExecutionException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserRepository {

    private DataBase dataBase;
    private UserDao userDao;
    private UserService userService;
    LiveData<List<Location>> getAllLocationDetails;

    public UserRepository(Application application) {
        dataBase= DataBase.getDataBase(application);
        userDao=dataBase.userDao();
//        getAllLocationDetails=userDao.getAllLocationDetails();
        userService = RetrofitClientInstance.getClient().create(UserService.class);


    }
//    public  LiveData<List<Location>> getAllLocationDetails(){
//        return dataBase.userDao().getAllLocationDetails();
//    }

    public void insertUsers(List<User> user){
        new insertUsers(userDao).execute(user);
    }

    public void  insertLocation(List<Location> locations){
        new insertLocation(userDao).execute(locations);
    }
    public void insertLogin(List<Login> logins){
        new insertLogin(userDao).execute(logins);
    }

    private static  class insertUsers extends AsyncTask<List<User>,Void,Void>{
        private UserDao userDao;

        public insertUsers(UserDao userDao) {
            this.userDao = userDao;
        }

        @Override
        protected Void doInBackground(List<User>... users) {
            userDao.insertUsers(users[0]);
            return null;
        }
    }
    private static  class  insertLocation extends AsyncTask<List<Location> ,Void, Void>{
        private UserDao userDao;

        public insertLocation(UserDao userDao) {
            this.userDao = userDao;
        }

        @Override
        protected Void doInBackground(List<Location>... lists) {
            userDao.insertLocation(lists[0]);
            return null;
        }
    }
    private static class  insertLogin extends  AsyncTask<List<Login>,Void,Void>{
        private UserDao userDao;

        public insertLogin(UserDao userDao) {
            this.userDao = userDao;
        }

        @Override
        protected Void doInBackground(List<Login>... lists) {
            userDao.insertLogin(lists[0]);
            return null;
        }
    }

    public List<User> getUserDetailList() {

        List<User> userMasterList = null;
        try {
            userMasterList = new AsyncUserMaster(userDao).execute().get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return userMasterList;

    }

    public List<Login> getLoginDetailList(){
        List<Login> loginMasterList=null;
        try{
             loginMasterList=new getLoginDetailList(userDao).execute().get();
        }catch (Exception e){}
        return loginMasterList;
    }

    private static class AsyncUserMaster extends AsyncTask<Void, Void, List<User>> {

        private UserDao userDao;

        public AsyncUserMaster(UserDao userDao) {
            this.userDao = userDao;
        }

        @Override
        protected List<User> doInBackground(Void... voids) {
            return userDao.getUserDetailList();
        }
    }
    private static class getLoginDetailList extends AsyncTask<Void,Void,List<Login>>{
        private UserDao userDao;

        public getLoginDetailList(UserDao userDao) {
            this.userDao = userDao;
        }

        @Override
        protected List<Login> doInBackground(Void... voids) {
            return userDao.getLoginDetailList();
        }
    }

    ////////////////////////////////////////////

    public void  getLocation(){
        Call<List<Location>> call=userService.getLocation();
        call.enqueue(new Callback<List<Location>>() {
            @Override
            public void onResponse(Call<List<Location>> call, Response<List<Location>> response) {
                System.out.println("************************** Location STARTS **************************");
                try {
                    if (!response.isSuccessful()) {
                        return;
                    }
                    List<Location> locations = response.body();
                    if(locations!=null){
//                    deleteUser();
                        for(Location userMaster:locations){
                            System.out.print("<<<<<<<<<<<<<<>>>>>>>>>>>>>>>>>>>><<<<<<<<<<<>>>>>>>>>>>>"+userMaster);
                        }
                        insertLocation(locations);
                    }
                    System.out.println("************************** Location  ENDS **************************");
                } catch (Exception e) {
                    System.out.println("Exception >>>>>>>>>> "+e);
                }
            }

            @Override
            public void onFailure(Call<List<Location>> call, Throwable t) {

            }
        });
    }

    public  void getUserService(){
        Call<List<Login>> call=userService.getLogin();
        call.enqueue(new Callback<List<Login>>() {
            @Override
            public void onResponse(Call<List<Login>> call, Response<List<Login>> response) {
//



                System.out.println("************************** USER STARTS **************************");
                try {
                    if (!response.isSuccessful()) {
                        return;
                    }
                    List<Login> userMasters = response.body();
                    if(userMasters!=null){
//                    deleteUser();
                        for(Login userMaster:userMasters){
                            System.out.print("<<<<<<<<<<<<<<>>>>>>>>>>>>>>>>>>>><<<<<<<<<<<>>>>>>>>>>>>"+userMaster);
                        }
                        insertLogin(userMasters);
                    }
                    System.out.println("************************** USER ENDS **************************");
                } catch (Exception e) {
                    System.out.println("Exception >>>>>>>>>> "+e);
                }

            }

            @Override
            public void onFailure(Call<List<Login>> call, Throwable t) {

            }
        });
    }
}
