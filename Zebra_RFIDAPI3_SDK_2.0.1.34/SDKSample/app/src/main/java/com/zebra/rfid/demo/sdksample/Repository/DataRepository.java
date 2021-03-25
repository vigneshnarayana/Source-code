package com.zebra.rfid.demo.sdksample.Repository;

import android.app.Activity;
import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.zebra.rfid.demo.sdksample.Dao.DataBase;
import com.zebra.rfid.demo.sdksample.Dao.DataDao;
import com.zebra.rfid.demo.sdksample.model.Data;

import java.util.List;

public class DataRepository {
    private DataBase dataBase;
    private DataDao dataDao;
    Application application;
    Activity activity;
    LiveData<List<Data>> getDatadetails;
    public DataRepository(Application application,Activity activity) {
        this.application = application;
        this.activity = activity;
        dataBase= DataBase.getDataBase(application);
         dataDao=dataBase.dataDao();
         getDatadetails=dataDao.getDatadetails();
    }
    public LiveData<List<Data>>  getDatadetails(){
        return dataBase.dataDao().getDatadetails();
    }
    public void insertData(Data data){
        new insertData(dataDao).execute(data);
    }

    private static class  insertData extends AsyncTask<Data,Void,Void>{
        private DataDao dataDao;

        public insertData(DataDao dataDao) {
            this.dataDao = dataDao;
        }

        @Override
        protected Void doInBackground(Data... data) {
           dataDao.insertData(data[0]);
            return null;
        }
    }
}
