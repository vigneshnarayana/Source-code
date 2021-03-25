package com.example.gpiproject.Repository;

import android.app.Activity;
import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.example.gpiproject.Dao.DataBase;
import com.example.gpiproject.Dao.LotCreationDao;
import com.example.gpiproject.model.FarmerMasterGet;
import com.example.gpiproject.model.LotCreationModel;

import java.util.List;

public class LotCreationRepository {

    private LotCreationDao lotCreationDao;
    private DataBase dataBase;
    List<LotCreationModel> lotCreations;
    Activity activity;
    List<Integer> baleSeries;

    public LotCreationRepository(Application application,Activity activity) {
        this.activity = activity;
        dataBase= DataBase.getDataBase(application);
        lotCreationDao=dataBase.lotCreationDao();

    }
    public void insertLotCreation(LotCreationModel lotCreation) {
        new insertLotCreation(lotCreationDao).execute(lotCreation);

    }

    public LiveData<FarmerMasterGet> getformerdetails(String formercode){
        return  lotCreationDao.getformerdetails(formercode);
    }
    public List<LotCreationModel> getLotCreations(String headerId) {
        try {
            this.lotCreations = new GetLotCreationAsyncTask(lotCreationDao).execute(headerId).get();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return lotCreations;
    }
    public List<Integer> getBaleSeries(List<String> validlist) {
        try {
            this.baleSeries = new GetBaleSeriesAsyncTask(lotCreationDao).execute(validlist).get();
        } catch (Exception e) {
        }
        return this.baleSeries;
    }
    public List<LotCreationModel> getAllLotCreation() {
        List<LotCreationModel> lotCreations1 = null;
        try {
            lotCreations1 = new GetAllLotCreationAsyncTask(lotCreationDao).execute().get();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lotCreations1;
    }


    private class GetAllLotCreationAsyncTask extends AsyncTask<Void, Void, List<LotCreationModel>> {
        private LotCreationDao lotCreationDao;

        public GetAllLotCreationAsyncTask(LotCreationDao lotCreationDao) {
            this.lotCreationDao = lotCreationDao;
        }

        @Override
        protected List<LotCreationModel> doInBackground(Void... voids) {
            return lotCreationDao.getAllLotCreation();
        }
    }


    private class GetLotCreationAsyncTask extends AsyncTask<String, Void, List<LotCreationModel>> {
        private LotCreationDao lotCreationDao;

        public GetLotCreationAsyncTask(LotCreationDao lotCreationDao) {
            this.lotCreationDao = lotCreationDao;
        }

        @Override
        protected List<LotCreationModel> doInBackground(String... data) {
            return lotCreationDao.getAllLotCreation(data[0]);
        }
    }

    private class GetBaleSeriesAsyncTask extends AsyncTask<List<String>, Void, List<Integer>> {
        private LotCreationDao lotCreationDao;

        public GetBaleSeriesAsyncTask(LotCreationDao lotCreationDao) {
            this.lotCreationDao = lotCreationDao;
        }

        @Override
        protected List<Integer> doInBackground(List<String>... data) {
            String lotNumber = data[0].get(0);
            String hid = data[0].get(1);
            return lotCreationDao.getFarmerSeries(Integer.parseInt(lotNumber),hid);
        }
    }

    private static class insertLotCreation extends AsyncTask<LotCreationModel, Void, Void> {
        private LotCreationDao lotCreationDao;

        private insertLotCreation(LotCreationDao lotCreationDao) {
            this.lotCreationDao = lotCreationDao;
        }

        @Override
        protected Void doInBackground(LotCreationModel... lotCreations) {
            //lotCreationDao.deleteAllLotCreation();
            lotCreationDao.insertLotCreation(lotCreations[0]);
            return null;
        }
    }
}
