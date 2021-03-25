package com.example.gpiproject.Repository;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Application;
import android.content.DialogInterface;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.example.gpiproject.Dao.DataBase;
import com.example.gpiproject.Dao.PurchaseDao;
import com.example.gpiproject.Dao.WeighmentDao;
import com.example.gpiproject.Service.HeaderService;
import com.example.gpiproject.Service.PurchaseService;
import com.example.gpiproject.Service.RetrofitClientInstance;
import com.example.gpiproject.model.FarmerPurchaseModel;
import com.example.gpiproject.model.Header;
import com.example.gpiproject.model.ItemMaster;
import com.example.gpiproject.model.LotCreationModel;
import com.example.gpiproject.model.PurchaseModel;
import com.example.gpiproject.model.WeighmentGet;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PurchaseRepository {
    private DataBase dataBase;
    private PurchaseDao purchaseDao;
    List<PurchaseModel> purchases;
     PurchaseService purchaseService;


    List<FarmerPurchaseModel> farmerPurchaseModels;

    private LiveData<List<ItemMaster>> getitemmasterbyergrade;
    private LiveData<List<ItemMaster>> getitemmasterclassificationgrade;

    Application application;
    Activity activity;

    public PurchaseRepository(Application application, Activity activity) {
        this.application = application;
        this.activity = activity;

        dataBase=DataBase.getDataBase(application);
        purchaseDao=dataBase.purchaseDao();
        getitemmasterbyergrade=purchaseDao.getitemmasterbyergrade();
        getitemmasterclassificationgrade=purchaseDao.getitemmasterclassificationgrade();

        purchaseService= RetrofitClientInstance.getClient().create(PurchaseService.class);


    }
    public Integer getPurchaseRejectedList() {
        Integer count = 0;
        try {
            count = new GetPurchaseRejectedListAsyncTaskCount(purchaseDao).execute().get();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return count;

    }

    public List<FarmerPurchaseModel> getLotCreation() {

        try {
            farmerPurchaseModels = new GetLotrAsyncTask(purchaseDao).execute().get();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return farmerPurchaseModels;

    }

    public List<PurchaseModel> getPurchaseList() {

        try {
            purchases = new GetPurchaseListAsyncTask(purchaseDao).execute().get();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return purchases;

    }

    public void insertPurchase(PurchaseModel purchaseModel) {
        new InsertPurchase(purchaseDao).execute(purchaseModel);

    }

    public LiveData<Header> getheadercropverity(String headerId){
        return purchaseDao.getheadercropverity(headerId);
    }
    public LiveData<List<ItemMaster>> getitemmasterbyergrade(){
        return  dataBase.purchaseDao().getitemmasterbyergrade();
    }

    public LiveData<List<ItemMaster>> getitemmasterclassificationgrade(){
        return dataBase.purchaseDao().getitemmasterclassificationgrade();
    }
    private class GetLotrAsyncTask extends AsyncTask<Void, Void, List<FarmerPurchaseModel>> {
        private PurchaseDao purchaseDao;

        public GetLotrAsyncTask(PurchaseDao purchaseDao) {
            this.purchaseDao = purchaseDao;
        }

        @Override
        protected List<FarmerPurchaseModel> doInBackground(Void... voids) {
            return purchaseDao.getLotCreations();
        }
    }

    private static class InsertPurchase extends AsyncTask<PurchaseModel, Void, Void> {

        private PurchaseDao purchaseDao;

        private InsertPurchase(PurchaseDao purchaseDao) {
            this.purchaseDao = purchaseDao;
        }

        @Override
        protected Void doInBackground(PurchaseModel... purchaseModels) {
            purchaseDao.insertPurchase(purchaseModels[0]);
            return null;
        }
    }





    private class GetPurchaseListAsyncTask extends AsyncTask<Void, Void, List<PurchaseModel>> {
        private PurchaseDao purchaseDao;

        public GetPurchaseListAsyncTask(PurchaseDao purchaseDao) {
            this.purchaseDao = purchaseDao;
        }

        @Override
        protected List<PurchaseModel> doInBackground(Void... voids) {

            return purchaseDao.getPurchaseList();
        }
    }
    private class GetPurchaseRejectedListAsyncTaskCount extends AsyncTask<Void, Void, Integer> {
        private PurchaseDao purchaseDao;

        public GetPurchaseRejectedListAsyncTaskCount(PurchaseDao purchaseDao) {
            this.purchaseDao = purchaseDao;
        }

        @Override
        protected Integer doInBackground(Void... voids) {
            return purchaseDao.getPurchaseRejectedList();
        }
    }


    ///////////////////////////////////////////////////////////////
    public String sendPurchasePostSync(List<PurchaseModel> purchases) {
        Map<String, List<PurchaseModel>> listMap = new HashMap();
        listMap.put("FarmerPurchasePost", purchases);
        Call<String> call = purchaseService.createPostSync(listMap);

        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {

                ///////////////////////////////
                System.out.println("************************** Purchase STARTS **************************");
                if (!response.isSuccessful()) {
                    return;
                }
                String result = response.body();
                System.out.println("************************** purchase ENDS ************************** " + result);
                if (activity != null) {
                    androidx.appcompat.app.AlertDialog.Builder builder = new androidx.appcompat.app.AlertDialog.Builder(activity);
                    builder.setTitle(" !!! Purchase Post Details!!!");

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


            /////////////////////////////


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
        return null;
    }

}
