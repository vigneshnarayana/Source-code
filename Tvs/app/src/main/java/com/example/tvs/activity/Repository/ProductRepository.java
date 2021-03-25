package com.example.tvs.activity.Repository;

import android.app.Activity;
import android.app.Application;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.widget.Toast;

import androidx.lifecycle.LiveData;


import com.example.tvs.activity.Dao.DataBase;
import com.example.tvs.activity.Dao.ProductDao;
import com.example.tvs.activity.Model.BreakDownmodel;
import com.example.tvs.activity.Model.IntimationModel;
import com.example.tvs.activity.Model.Location;
import com.example.tvs.activity.Model.OnlinePE;
import com.example.tvs.activity.Model.Product;
import com.example.tvs.activity.Service.ProductService;
import com.example.tvs.activity.Service.RetrofitClientInstance;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductRepository {
    private DataBase dataBase;
    private ProductDao productDao;
    private ProductService productService;
    List<Product> products;
    List<BreakDownmodel> breakDownmodels;
    List<IntimationModel> intimationModels;
    List<OnlinePE> onlinePES;
    Application application;
    Activity activity;
    public ProductRepository(Application application, Activity activity) {
        this.application = application;
        this.activity = activity;

        dataBase= DataBase.getDataBase(application);
        productDao=dataBase.productDao();
        productService= RetrofitClientInstance.getClient().create(ProductService.class);

    }
    public void insertProduct(Product product){
        new insertProduct(productDao).execute(product);
    }

    public  void insertBreakDown(BreakDownmodel breakDownmodel){
        new insertBreakDown(productDao).execute(breakDownmodel);
    }
    public void insertIntimation(IntimationModel intimationModel){
        new insertIntimation(productDao).execute(intimationModel);
    }
    public void insertOnlinePE(OnlinePE onlinePE){
        new insertOnlinePE(productDao).execute(onlinePE);
    }
    public void deleteAllproduct(){
        new deleteAllproduct(productDao).execute();
    }

    public void deleteAllbreakdown(){
        new deleteAllbreakdown(productDao).execute();
    }
    public void deleteAllintimation(){
        new deleteAllintimation(productDao).execute();
    }
    public void deleteAllonlinPe(){
        new deleteAllonlinPe(productDao).execute();
    }
    public List<Product> getproductList() {

        try {
            products = new GetPurchaseListAsyncTask(productDao).execute().get();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return products;

    }

    public List<BreakDownmodel> getBreakDownList(){
        try{
            breakDownmodels= new getBreakDownList(productDao).execute().get();

        }catch (Exception e){
            e.printStackTrace();
        }
        return  breakDownmodels;
    }

    public List<IntimationModel> getIntimationList(){
        try{
            intimationModels=new getIntimationList(productDao).execute().get();
        }catch (Exception e){
            e.printStackTrace();
        }
        return intimationModels;
    }
    public List<OnlinePE> getOnlinePEList(){
        try{
            onlinePES=new getOnlinePEList(productDao).execute().get();
        }catch (Exception e){e.printStackTrace();}
        return onlinePES;
    }
    private static class deleteAllproduct extends AsyncTask<Void, Void, Void> {
        private ProductDao productDao;

        public deleteAllproduct(ProductDao productDao) {
            this.productDao = productDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            productDao.deleteAllproduct();
            return null;
        }
    }

    private static class deleteAllbreakdown extends AsyncTask<Void, Void, Void> {
        private ProductDao productDao;

        public deleteAllbreakdown(ProductDao productDao) {
            this.productDao = productDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            productDao.deleteAllbreakdown();
            return null;
        }
    }


    private static class deleteAllintimation extends AsyncTask<Void, Void, Void> {
        private ProductDao productDao;

        public deleteAllintimation(ProductDao productDao) {
            this.productDao = productDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            productDao.deleteAllintimation();
            return null;
        }
    }
    private static class deleteAllonlinPe extends AsyncTask<Void,Void,Void>{
        private ProductDao productDao;

        public deleteAllonlinPe(ProductDao productDao) {
            this.productDao = productDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            productDao.deleteAllonlinPe();
            return null;
        }
    }
    private class GetPurchaseListAsyncTask extends AsyncTask<Void, Void, List<Product>> {
      private  ProductDao productDao;

        public GetPurchaseListAsyncTask(ProductDao productDao) {
            this.productDao = productDao;
        }

        @Override
        protected List<Product> doInBackground(Void... voids) {

            return productDao.getproductList();
        }
    }
    private class getBreakDownList extends AsyncTask<Void, Void, List<BreakDownmodel>> {

        private ProductDao productDao;

        public getBreakDownList(ProductDao productDao) {
            this.productDao = productDao;
        }

        @Override
        protected List<BreakDownmodel> doInBackground(Void... voids) {
            return productDao.getBreakDownList();
        }
    }

    private class getIntimationList extends AsyncTask<Void, Void, List<IntimationModel>> {
        private ProductDao productDao;

        public getIntimationList(ProductDao productDao) {
            this.productDao = productDao;
        }

        @Override
        protected List<IntimationModel> doInBackground(Void... voids) {
            return productDao.getIntimationList();
        }
    }
private class  getOnlinePEList extends  AsyncTask<Void,Void,List<OnlinePE>>{
    private ProductDao productDao;

    public getOnlinePEList(ProductDao productDao) {
        this.productDao = productDao;
    }

    @Override
    protected List<OnlinePE> doInBackground(Void... voids) {
        return productDao.getOnlinePEList();
    }
}

    private static class insertProduct extends AsyncTask<Product, Void, Void> {
        private ProductDao productDao;

        public insertProduct(ProductDao productDao) {
            this.productDao = productDao;
        }

        @Override
        protected Void doInBackground(Product... products) {
            productDao.insertProduct(products[0]);
            return null;
        }

    }
    private static class insertBreakDown extends AsyncTask<BreakDownmodel, Void, Void> {
        private ProductDao productDao;

        public insertBreakDown(ProductDao productDao) {
            this.productDao = productDao;
        }


        @Override
        protected Void doInBackground(BreakDownmodel... breakDownmodels) {
            productDao.insertBreakDown(breakDownmodels[0]);
            return null;
        }
    }
    private static class insertIntimation extends AsyncTask<IntimationModel, Void, Void> {
        private ProductDao productDao;

        public insertIntimation(ProductDao productDao) {
            this.productDao = productDao;
        }

        @Override
        protected Void doInBackground(IntimationModel... intimationModels) {
            productDao.insertIntimation(intimationModels[0]);
            return null;
        }
    }
   private static class insertOnlinePE extends AsyncTask<OnlinePE,Void,Void>{
        private ProductDao productDao;

       public insertOnlinePE(ProductDao productDao) {
           this.productDao = productDao;
       }

       @Override
       protected Void doInBackground(OnlinePE... onlinePES) {
           productDao.insertOnlinePE(onlinePES[0]);
           return null;
       }
   }


    public LiveData<List<Location>> getLocationDatails(String Locationid){
        return productDao.getLocationDatails(Locationid);
    }
    ///////////////////////////////////////

    public String postonlinepeService(List<OnlinePE> onlinePES){
        Map<String, List<OnlinePE>> listMap = new HashMap();
        listMap.put("OnlinePEPost", onlinePES);
        Call<String> call = productService.postonlinepeService(listMap);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                System.out.println("************************** OnlinePE Post STARTS **************************");
                if (!response.isSuccessful()) {
                    return;
                }
                String result = response.body();
                System.out.println("************************** OnlinePE Post ENDS ************************** " + result);
                if (activity != null) {
                    androidx.appcompat.app.AlertDialog.Builder builder = new androidx.appcompat.app.AlertDialog.Builder(activity);
                    builder.setTitle(" !!! OnlinePE Post Details!!!");

                    builder.setMessage(result);
                    if(result.equals(" successfully")){
                        deleteAllonlinPe();
                    }
                    builder.setCancelable(true);
                    builder.setNeutralButton("Ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
//                            Toast.makeText(activity, " Please Check WIFI ", Toast.LENGTH_SHORT).show();
                        }
                    });
                    builder.show();
                }

            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Toast.makeText(activity, " Please Check WIFI ", Toast.LENGTH_SHORT).show();
            }
        });
        return null;

    }

    public String postIntimationService(List<IntimationModel> intimationModels){
        Map<String, List<IntimationModel>> listMap = new HashMap();
        listMap.put("intimationPost", intimationModels);
        Call<String> call = productService.postIntimationService(listMap);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                System.out.println("************************** Intimation Post STARTS **************************");
                if (!response.isSuccessful()) {
                    return;
                }
                String result = response.body();
                System.out.println("************************** Intimation Post ENDS ************************** " + result);
                if (activity != null) {
                    androidx.appcompat.app.AlertDialog.Builder builder = new androidx.appcompat.app.AlertDialog.Builder(activity);
                    builder.setTitle(" !!! Intimation Post Details!!!");

                    builder.setMessage(result);
                    if(result.equals(" successfully")){
                        deleteAllintimation();
                    }
                    builder.setCancelable(true);
                    builder.setNeutralButton("Ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
//                            Toast.makeText(activity, " Please Check WIFI ", Toast.LENGTH_SHORT).show();
                        }
                    });
                    builder.show();
                }

            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Toast.makeText(activity, " Please Check WIFI ", Toast.LENGTH_SHORT).show();

            }
        });

        return  null;
    }
    public String postBreakDownServioce(List<BreakDownmodel> breakDownmodels){
        Map<String, List<BreakDownmodel>> listMap = new HashMap();
        listMap.put("BreakdownPost", breakDownmodels);
        Call<String> call = productService.postBreakDownServioce(listMap);

        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                System.out.println("************************** BreakDown Post STARTS **************************");
                if (!response.isSuccessful()) {
                    return;
                }
                String result = response.body();
                System.out.println("************************** BreakDown Post ENDS ************************** " + result);
                if (activity != null) {
                    androidx.appcompat.app.AlertDialog.Builder builder = new androidx.appcompat.app.AlertDialog.Builder(activity);
                    builder.setTitle(" !!! BreakDown Post Details!!!");

                    builder.setMessage(result);
                    if(result.equals(" successfully")){
                        deleteAllbreakdown();
                    }
                    builder.setCancelable(true);
                    builder.setNeutralButton("Ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
//                            Toast.makeText(activity, " Please Check WIFI ", Toast.LENGTH_SHORT).show();
                        }
                    });
                    builder.show();
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Toast.makeText(activity, " Please Check WIFI ", Toast.LENGTH_SHORT).show();

            }
        });
        return null;
    }

    public String postProductDetails(List<Product> products){
        Map<String, List<Product>> listMap = new HashMap();
        listMap.put("ProductivityPost", products);
        Call<String> call = productService.postProductDetails(listMap);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                System.out.println("************************** Product Post STARTS **************************");
                if (!response.isSuccessful()) {
                    return;
                }
                String result = response.body();
                System.out.println("************************** Product Post ENDS ************************** " + result);
                if (activity != null) {
                    androidx.appcompat.app.AlertDialog.Builder builder = new androidx.appcompat.app.AlertDialog.Builder(activity);
                    builder.setTitle(" !!! Product Post Details!!!");

                    builder.setMessage(result);
                    if(result.equals(" successfully")){
                        deleteAllproduct();
                    }
                    builder.setCancelable(true);

                    builder.setNeutralButton("Ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
//                            Toast.makeText(activity, " Please Check WIFI ", Toast.LENGTH_SHORT).show();
                        }
                    });
                    builder.show();
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Toast.makeText(activity, " Please Check WIFI ", Toast.LENGTH_SHORT).show();


            }
        });

        return null;
    }
}
