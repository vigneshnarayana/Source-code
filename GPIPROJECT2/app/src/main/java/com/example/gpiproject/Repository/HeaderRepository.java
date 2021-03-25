package com.example.gpiproject.Repository;

import android.app.Activity;
import android.app.Application;
import android.content.DialogInterface;
import android.os.AsyncTask;

import androidx.appcompat.app.AlertDialog;
import androidx.lifecycle.LiveData;

import com.example.gpiproject.Dao.DataBase;
import com.example.gpiproject.Dao.HeaderDao;
import com.example.gpiproject.Dao.LoginDao;
import com.example.gpiproject.Service.HeaderService;
import com.example.gpiproject.Service.RetrofitClientInstance;
import com.example.gpiproject.model.Crop;
import com.example.gpiproject.model.HeaderPost;
import com.example.gpiproject.model.Login;
import com.example.gpiproject.model.Organization;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HeaderRepository {
    private DataBase database;
    private HeaderDao headerDao;
    private LiveData<List<Crop>> getcrop;
    private HeaderService headerService;

    Application application;
    Activity activity;
    public HeaderRepository(Application application,Activity activity) {
        this.application=application;
        this.activity=activity;
        database= DataBase.getDataBase(application);
        headerDao=database.headerDao();
        getcrop=headerDao.getcrop();
        headerService= RetrofitClientInstance.getClient().create(HeaderService.class);

    }

    /////////////////////ROOM///////////////////
    public LiveData<Login> getusername(String byerId){
        return  headerDao.getusername(byerId);
    }
    public LiveData<List<Crop>> getcrop(){
        return database.headerDao().getcrop();
    }
  public  void insertheader(HeaderPost headerPost){
        new insertheader(headerDao).execute(headerPost);
  }

    private static class insertheader extends AsyncTask<HeaderPost, Void, Void> {

        private HeaderDao headerDao;

        public insertheader(HeaderDao headerDao) {
            this.headerDao = headerDao;
        }

        @Override
        protected Void doInBackground(HeaderPost... headerPosts) {
            headerDao.insertheader(headerPosts[0]);
            return null;
        }



    }

    //////////////////Service////////////////

    public String headerpost(List<HeaderPost> headerPosts){
        Map<String, List<HeaderPost>> listMap=new HashMap<>();
        listMap.put("sadf",headerPosts);
        Call<String> call= headerService.headerpost(listMap);
//        System.out.println(">>>>>>>>>>>>>>>>" + testTables.get(0));
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                System.out.println("************************** Header STARTS **************************");
                if (!response.isSuccessful()) {
                    return;
                }
                String result = response.body();
                System.out.println("************************** Header ENDS ************************** " + result);
                if (activity != null) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(activity);
                    builder.setTitle(" !!! Header Post Details!!!");

                    builder.setMessage(result);
                    builder.setCancelable(true);
                    builder.setNeutralButton("Ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            //Toast.makeText(activity, " Please Check WIFI ", Toast.LENGTH_SHORT).show();
                        }
                    });
                    builder.show();
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                System.out.println("************************** FAILED **************************");
                if (activity != null) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(activity);
                    builder.setTitle(" !!! ALERT NETWORK FAILURE !!!");

                    builder.setMessage("PLEASE TRY SYNC");
                    builder.setCancelable(true);
                    builder.setNeutralButton("Ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            //Toast.makeText(activity, " Please Check WIFI ", Toast.LENGTH_SHORT).show();
                        }
                    });
                    builder.show();
                }
            }
        });




        return  null;
    }
}
