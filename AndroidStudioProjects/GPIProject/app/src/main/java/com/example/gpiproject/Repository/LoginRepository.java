package com.example.gpiproject.Repository;

import android.app.Application;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import androidx.lifecycle.LiveData;

import com.example.gpiproject.Dao.DataBase;
import com.example.gpiproject.Dao.LoginDao;
import com.example.gpiproject.Dao.OrganizationDao;
import com.example.gpiproject.MainActivity;
import com.example.gpiproject.model.Login;
import com.example.gpiproject.model.Organization;
import com.example.gpiproject.service.LoginService;
import com.example.gpiproject.service.RetrofitClientInstance;
import com.example.gpiproject.service.TestService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginRepository {

    private DataBase database;
    private LoginDao loginDao;
    private OrganizationDao organizationDao;
    LoginService loginService;

    private static final String TAG = "MainActivity";

    private LiveData<List<Login>> loginList;
    private LiveData<List<Organization>> getAllOrganization;

    public LoginRepository(Application application) {
        database=  DataBase.getDataBase(application);
        loginDao=database.loginDao();
        organizationDao=database.organizationDao();
        organizationDao=database.organizationDao();
        loginList=loginDao.getAllloginDetails();
//        getAllOrganization=organizationDao.getAllOrganization();

        loginService = RetrofitClientInstance.getClient().create(LoginService.class);

    }

    public Login getlogin(String username, String password){




        return loginDao.getLogin(username,password);
    }
    public void insertLoginData(Login login) {
        new LoginInsertion(loginDao).execute(login);
    }


    public  LiveData<List<Login>> getAllLoginDetails(){
        return database.loginDao().getAllloginDetails();
    }
//    public LiveData<List<Organization>> getAllOrganization(){
//        return database.organizationDao().getAllOrganization();
//    }
    public  void inser(final  Login login){
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
               database.loginDao().insert(login);
                return null;
            }
        }.execute();
    }
//   public  void insertorganizationdetails(final Organization organization){
//       new AsyncTask<Void, Void, Void>() {
//           @Override
//           protected Void doInBackground(Void... voids) {
//               database.organizationDao().insertorganizationdetails(organization);
//               return null;
//           }
//       }.execute();
//   }

    private static class LoginInsertion extends AsyncTask<Login, Void, Void> {

        private LoginDao loginDao;

        private LoginInsertion(LoginDao loginDao) {

            this.loginDao = loginDao;

        }

        @Override
        protected Void doInBackground(Login... log) {

            loginDao.insertLoginDetail(log[0]);
            return null;

        }

    }
    ///////////////////////////////////////////////////////////

     public  void getoranization(){
        Call<List<Organization>> call=loginService.getoranization();
        call.enqueue(new Callback<List<Organization>>() {
            @Override
            public void onResponse(Call<List<Organization>> call, Response<List<Organization>> response) {
                Log.e(TAG, "onResponse: " +  response.body());
                List<Organization> data=response.body();
                try{
                    for(Organization i: data){
                    Log.d("i", i.getOrganizationCode());

                    Organization organization=new Organization();
                    organization.setOrganizationCode(i.getOrganizationCode());
                    organization.setOrganizationName(i.getOrganizationName());
                    organization.setOrganizationAddress1(i.getOrganizationAddress1());
                    organization.setOrganizationType(i.getOrganizationType());

                    organizationDao.insertorganizationdetails(organization);

//                    Login login=new Login(i.getUserID(),i.getPassword());
//                    loginViewModel.insert(login);
                }}catch ( Exception e){
//                    Toast.makeText(MainActivity.this, "failed", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Organization>> call, Throwable t) {

            }
        });
    }

}
