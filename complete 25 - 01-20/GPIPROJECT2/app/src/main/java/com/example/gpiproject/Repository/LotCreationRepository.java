package com.example.gpiproject.Repository;

import android.app.Activity;
import android.app.Application;
import android.content.DialogInterface;
import android.os.AsyncTask;

import androidx.appcompat.app.AlertDialog;
import androidx.lifecycle.LiveData;

import com.example.gpiproject.Dao.DataBase;
import com.example.gpiproject.Dao.LotCreationDao;
import com.example.gpiproject.Service.HeaderService;
import com.example.gpiproject.Service.LotCreationService;
import com.example.gpiproject.Service.RetrofitClientInstance;
import com.example.gpiproject.model.FarmerMasterGet;
import com.example.gpiproject.model.LotCreationModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LotCreationRepository {

    private LotCreationDao lotCreationDao;
    private DataBase dataBase;
    List<FarmerMasterGet> farmerMasters;
    List<LotCreationModel> lotCreationModels;
    Activity activity;
    List<Integer> baleSeries ;
    List<LotCreationModel> lotTable;
    LiveData<LotCreationModel> getlotcount;
    Application application;
    LiveData<List<LotCreationModel>> getlotlivedata;
    private LotCreationService lotCreationService;

    public LotCreationRepository(Application application,Activity activity) {
        this.activity = activity;
        this.application=application;
        dataBase= DataBase.getDataBase(application);
        lotCreationDao=dataBase.lotCreationDao();
        lotCreationService= RetrofitClientInstance.getClient().create(LotCreationService.class);
       getlotlivedata=lotCreationDao.getlotlivedata();

    }
    public  void insertLotCreation(LotCreationModel lotCreationModel){
        new insertLotCreation(lotCreationDao).execute(lotCreationModel);
    }

    public  LiveData<List<LotCreationModel>> getlotlivedata(){
        return dataBase.lotCreationDao().getlotlivedata();
    }



    public LiveData<FarmerMasterGet> getformerdetails(String formercode){
        return  lotCreationDao.getformerdetails(formercode);
    }
    public LiveData<LotCreationModel> getlotcount(){
        return lotCreationDao.getlotcountvalue();
    }
//    public  LiveData<LotCreationModel> getformercount(){
//        return lotCreationDao.getformercount();
//    }

    public List<LotCreationModel> getLotCreations(String headerId) {
        try {
            this.lotCreationModels= new GetLotCreationAsyncTask(lotCreationDao).execute(headerId).get();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return lotCreationModels;
    }
    public List<Integer> getBaleSeries(List<String> validlist) {
        try {
            this.baleSeries = new GetBaleSeriesAsyncTask(lotCreationDao).execute(validlist).get();
        } catch (Exception e) {
        }
        return this.baleSeries;
    }
    public List<LotCreationModel> getAllLotCreation() {
        List<LotCreationModel> lotCreationModels = null;
        try {
            lotCreationModels = new GetAllLotCreationAsyncTask(lotCreationDao).execute().get();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lotCreationModels;
    }
    public List<FarmerMasterGet> getFarmerMasters() {

        try {
            farmerMasters = new GetFarmerAsyncTask(lotCreationDao).execute().get();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return farmerMasters;

    }
    public  List<LotCreationModel> getLotcreationdata(){
        try{

            lotTable = new getLotcreationdata(lotCreationDao).execute().get();
        }catch (Exception e){
            e.printStackTrace();
        }
        return lotTable;
    }


    private class GetFarmerAsyncTask extends AsyncTask<Void, Void, List<FarmerMasterGet>> {
        private LotCreationDao lotCreationDao;

        public GetFarmerAsyncTask(LotCreationDao lotCreationDao) {
            this.lotCreationDao = lotCreationDao;
        }

        @Override
        protected List<FarmerMasterGet> doInBackground(Void... voids) {
            lotCreationDao.getFarmerMaster();
            return lotCreationDao.getFarmerMaster();
        }
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
   private  static class insertLotCreation extends AsyncTask<LotCreationModel,Void,Void>{
       private  LotCreationDao lotCreationDao;

       public insertLotCreation(LotCreationDao lotCreationDao) {
           this.lotCreationDao = lotCreationDao;
       }

       @Override
       protected Void doInBackground(LotCreationModel... lotCreationModels) {
           lotCreationDao.insertLotCreation(lotCreationModels[0]);
           return null;
       }
   }

   private class  getLotcreationdata extends  AsyncTask<Void,Void,List<LotCreationModel>>{
  private  LotCreationDao lotCreationDao;

       public getLotcreationdata(LotCreationDao lotCreationDao) {
           this.lotCreationDao = lotCreationDao;
       }

       @Override
       protected List<LotCreationModel> doInBackground(Void... voids) {
        return lotCreationDao.getLotcreationdata();
           }
   }

   ///////////////////////////////////////////////////////////////////

    public String lotcreationpost(List<LotCreationModel> lotCreationModels){
       Map<String, List<LotCreationModel>> listMap= new HashMap();
       listMap.put("test", lotCreationModels);
        Call<String> call=lotCreationService.lotcreationpost(listMap);


        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                System.out.println("************************** LOT STARTS **************************");
                if (!response.isSuccessful()) {
                    return;
                }
                String result = response.body();
                System.out.println("************************** LOT ENDS ************************** " + result);
                if (activity != null) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(activity);
                    builder.setTitle(" !!! LotCreation Post Details!!!");

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
                System.out.println("************************** LOT FAILED **************************");

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
