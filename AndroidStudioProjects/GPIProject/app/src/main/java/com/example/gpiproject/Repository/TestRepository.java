package com.example.gpiproject.Repository;

import android.app.Activity;
import android.app.Application;
import android.content.DialogInterface;
import android.os.AsyncTask;

import androidx.appcompat.app.AlertDialog;
import androidx.lifecycle.LiveData;

import com.example.gpiproject.Dao.DataBase;
import com.example.gpiproject.Dao.TestDao;
import com.example.gpiproject.model.TestTable;
import com.example.gpiproject.service.RetrofitClientInstance;
import com.example.gpiproject.service.TestService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TestRepository {
    private TestService testService;
   private TestDao testDao;
   private DataBase dataBase;
   List<TestTable> testTables;
    private LiveData<List<TestTable>> getAll;

    Application application;
    Activity activity;
    public TestRepository(Application application,Activity activity) {
      this.application=application;
      this.activity=activity;

       dataBase= DataBase.getDataBase(application);
      this.testDao=dataBase.testDao();
      getAll=testDao.getAll();

       testService = RetrofitClientInstance.getClient().create(TestService.class);
    }
    public  LiveData<List<TestTable>> getAll(){
        return getAll;
    }

    public void insertTest(TestTable testTable) {
        //deleteHeader();
        new insertTest(testDao).execute(testTable);

    }

    private static class insertTest extends AsyncTask<TestTable, Void, Void> {

        private TestDao testDao;

        public insertTest(TestDao testDao) {
            this.testDao = testDao;
        }

        @Override
        protected Void doInBackground(TestTable... log) {

            testDao.inserttest(log[0]);
            return null;

        }

    }

    ////////////////
    public List<TestTable> getTabletata() {

        try {
            testTables = new getAllTable(testDao).execute().get();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return testTables;

    }
    //////////////

    private class getAllTable extends AsyncTask<Void, Void, List<TestTable>> {
        private TestDao testDao;

        public getAllTable(TestDao testDao) {
            this.testDao = testDao;
        }

        @Override
        protected List<TestTable> doInBackground(Void... voids) {

            return testDao.gettableData();
        }
    }
    ///////////////


    public String SendTestPost(List<TestTable> testTables){
        Map<String, List<TestTable>> listMap=new HashMap<>();
        listMap.put("sadf",testTables);
        Call<String> call= testService.createpost(listMap);
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




        return null;
    }
}
