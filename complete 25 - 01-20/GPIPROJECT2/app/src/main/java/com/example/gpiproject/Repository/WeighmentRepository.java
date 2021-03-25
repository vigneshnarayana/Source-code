package com.example.gpiproject.Repository;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Application;
import android.content.DialogInterface;
import android.os.AsyncTask;

import com.example.gpiproject.Dao.DataBase;
import com.example.gpiproject.Dao.WeighmentDao;
import com.example.gpiproject.Service.PurchaseService;
import com.example.gpiproject.Service.RetrofitClientInstance;
import com.example.gpiproject.Service.WeighmentService;
import com.example.gpiproject.model.Weighment;
import com.example.gpiproject.model.WeighmentGet;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WeighmentRepository {
    private DataBase dataBase;
    private WeighmentDao weighmentDao;
    List<WeighmentGet> purchases;
    List<Weighment> weighments;
    private WeighmentService weighmentService;

    Application application;
    Activity activity;

    public WeighmentRepository(Application application, Activity activity) {
        this.application = application;
        this.activity = activity;

        dataBase=DataBase.getDataBase(application);
        weighmentDao =dataBase.weighmentDao();

        weighmentService= RetrofitClientInstance.getClient().create(WeighmentService.class);

    }
    public void insertWeighment(Weighment weighment) {
        new InsertWeighment(weighmentDao).execute(weighment);
    }

    public List<WeighmentGet> getPurchase() {

        try {
            purchases = new GetPurchaseAsyncTask(weighmentDao).execute().get();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return purchases;

    }
    public List<Weighment> getWeighments() {

        try {
            weighments = new GetWeighmentAsyncTask(weighmentDao).execute().get();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return weighments;

    }

    private static class InsertWeighment extends AsyncTask<Weighment, Void, Void> {

        private WeighmentDao weighmentDao;
        private InsertWeighment(WeighmentDao weighmentDao) {
            this.weighmentDao = weighmentDao;
        }

        @Override
        protected Void doInBackground(Weighment... weighments) {
            weighmentDao.insert(weighments[0]);
            return null;
        }
    }
    private class GetWeighmentAsyncTask extends AsyncTask<Void, Void, List<Weighment>> {
        private WeighmentDao weighmentDao;

        public GetWeighmentAsyncTask(WeighmentDao weighmentDao) {
            this.weighmentDao = weighmentDao;
        }

        @Override
        protected List<Weighment> doInBackground(Void... voids) {
            return weighmentDao.getWeightmentList();
        }
    }

    private class GetPurchaseAsyncTask extends AsyncTask<Void, Void, List<WeighmentGet>> {
        private WeighmentDao weighmentDao;

        public GetPurchaseAsyncTask(WeighmentDao weighmentDao) {
            this.weighmentDao = weighmentDao;
        }

        @Override
        protected List<WeighmentGet> doInBackground(Void... voids) {
            return weighmentDao.getPurchaseList();
        }
    }

    //////////////////////////////////////////////////////////////////

    public String sendWeightPostSync(List<Weighment> weighments) {
        Map<String, List<Weighment>> listMap = new HashMap();
        listMap.put("test", weighments);
        Call<String> call = weighmentService.createPostSync(listMap);

        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                ///////////////////////////////
                System.out.println("************************** Weighment STARTS **************************");
                if (!response.isSuccessful()) {
                    return;
                }
                String result = response.body();
                System.out.println("************************** Weighment ENDS ************************** " + result);
                if (activity != null) {
                    androidx.appcompat.app.AlertDialog.Builder builder = new androidx.appcompat.app.AlertDialog.Builder(activity);
                    builder.setTitle(" !!! Weighment Post Details!!!");

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
                System.out.println("************************** WEIGHMENT SYNC FAILED **************************");
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
