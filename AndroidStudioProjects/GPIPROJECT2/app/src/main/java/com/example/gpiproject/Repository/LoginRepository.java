package com.example.gpiproject.Repository;

import android.app.Application;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.lifecycle.LiveData;

import com.example.gpiproject.Dao.DataBase;
import com.example.gpiproject.Dao.LoginDao;
import com.example.gpiproject.MainActivity;
import com.example.gpiproject.Service.LoginService;
import com.example.gpiproject.Service.RetrofitClientInstance;
import com.example.gpiproject.model.Crop;
import com.example.gpiproject.model.FarmerMasterGet;
import com.example.gpiproject.model.Header;
import com.example.gpiproject.model.Login;
import com.example.gpiproject.model.Organization;
import com.example.gpiproject.model.Variety;

import java.util.List;
import java.util.concurrent.ExecutionException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginRepository {
      private DataBase database;
      private LoginDao loginDao;
      private   LoginService loginService;
    private LiveData<List<Organization>> getAllOrganizationDetails;
    private LiveData<List<Login>> getAllloginDetails;
    private LiveData<List<Header>> getAllHeaderID;
      private static final String TAG = "MainActivity";
    private ProgressDialog progressDialog = null;


    public LoginRepository(Application application) {
        database=  DataBase.getDataBase(application);
        loginDao=database.loginDao();

        loginService = RetrofitClientInstance.getClient().create(LoginService.class);
        getAllOrganizationDetails=loginDao.getAllOrganizationDetails();
        getAllloginDetails=loginDao.getAllloginDetails();
        getAllHeaderID=loginDao.getAllHeaderID();
    }

    ////////////////////// Room/////////////////////////
    public void InsertLogin(Login login) {
        new InsertLogin(loginDao).execute(login);
    }
    public void insertcrop(Crop crop){
        new insertcrop(loginDao).execute(crop);
    }

    public void insertvariety(Variety variety){
        new insertvariety(loginDao).execute(variety);
    }
    public void insertheader(Header header){
        new insertheader(loginDao).execute(header);
    }
    public void insertfarmer(FarmerMasterGet farmerMasterGet){
        new insertfarmer(loginDao).execute(farmerMasterGet);
    }
    public void insertorganization(Organization organization) {
        new insertorganization(loginDao).execute(organization);
    }

    public LiveData<List<Organization>> getAllOrganizationDetails(){
        return database.loginDao().getAllOrganizationDetails();
    }
    public LiveData<List<Header>> getAllHeaderID(){
        return database.loginDao().getAllHeaderID();
    }
public  LiveData<List<Login>> getAllloginDetails(){
        return  database.loginDao().getAllloginDetails();
}
    public List<Login> getUserDetailList() {

        List<Login> userMasterList = null;

        try {
            userMasterList = new AsyncUserMaster(loginDao).execute().get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return userMasterList;

    }
    public List<Organization> getOrgnDetailList() {

        List<Organization> orgnMasterList= null;

        try {
            orgnMasterList = new AsyncOrgnMaster(loginDao).execute().get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return orgnMasterList;

    }

    private static class insertheader extends  AsyncTask<Header,Void,Void>{
        private LoginDao loginDao;
//        private    Context mContext;
//
//        public insertheader(Context mContext) {
//            this.mContext = mContext;
//        }

        public insertheader(LoginDao loginDao) {
            this.loginDao = loginDao;
        }
        @Override
        protected Void doInBackground(Header... headers) {
            loginDao.insertheader(headers[0]);
            return null;
        }
//        @Override
//        protected void onPreExecute() {
//           ProgressDialog progressDialog = new ProgressDialog(mContext);
//            progressDialog.show();
//        }

    }

    private  static class insertfarmer extends  AsyncTask<FarmerMasterGet,Void,Void>{
        private LoginDao loginDao;

        public insertfarmer(LoginDao loginDao) {
            this.loginDao = loginDao;
        }
        @Override
        protected Void doInBackground(FarmerMasterGet... farmerMasterGets) {
            loginDao.insertfarmer(farmerMasterGets[0]);
            return null;
        }
    }
    private static class insertvariety extends AsyncTask<Variety,Void,Void>{
        private LoginDao loginDao;

        public insertvariety(LoginDao loginDao) {
            this.loginDao = loginDao;
        }
        @Override
        protected Void doInBackground(Variety... varieties) {
            loginDao.insertvariety(varieties[0]);
            return null;
        }
    }

    private static class insertcrop extends AsyncTask<Crop,Void,Void>{
        private LoginDao loginDao;

        public insertcrop(LoginDao loginDao) {
            this.loginDao = loginDao;
        }

        @Override
        protected Void doInBackground(Crop... crops) {
            loginDao.insertcrop(crops[0]);
            return null;
        }
    }
//
    private static class InsertLogin extends AsyncTask<Login, Void, Void> {
        private LoginDao loginDao;

        public InsertLogin(LoginDao loginDao) {
            this.loginDao = loginDao;
        }

        @Override
        protected Void doInBackground(Login... logins) {
           loginDao.insert(logins[0]);
            return null;
        }
    }
 //
 private static class AsyncUserMaster extends AsyncTask<Void, Void, List<Login>> {

     private LoginDao loginDao;

     private AsyncUserMaster(LoginDao loginDao) {
         this.loginDao = loginDao;
     }

     @Override
     protected List<Login> doInBackground(Void... voids) {
         return loginDao.getUserDetailList();
     }
 }
    private static class insertorganization extends  AsyncTask<Organization,Void,Void>{
       private LoginDao loginDao;

        public insertorganization(LoginDao loginDao) {

            this.loginDao = loginDao;
        }

        @Override
        protected Void doInBackground(Organization... organizations) {
            loginDao.insertorganization(organizations[0]);
            return null;
        }
    }

    private static class AsyncOrgnMaster extends AsyncTask<Void, Void, List<Organization>> {

        private LoginDao loginDao;

        private AsyncOrgnMaster(LoginDao loginDao) {
            this.loginDao = loginDao;
        }

        @Override
        protected List<Organization> doInBackground(Void... voids) {
            return loginDao.getOrgnDetailList();
        }
    }
////////////////////////////////////////////////////// Service/////////////////////////////////////////////////////////////////////////////////////////////

    public  void getheaderservice(){
        Call<List<Header>> call=loginService.getheaderservice();
        call.enqueue(new Callback<List<Header>>() {
            @Override
            public void onResponse(Call<List<Header>> call, Response<List<Header>> response) {
                List<Header> data=response.body();
                for(Header header: data){
                    Header header1=new Header(
                            header.getHeaderID(),
                            header.getChequeNumber(),
                            header.getFlag(),
                            header.getOrganizationCode(),
                            header.getPurchaseType(),
                            header.getBuyerCode(),
                            header.getPurchaseDocumentNumber(),
                            header.getInvoiceDate(),
                            header.getPurchaseDate(),
                            header.getCrop(),
                            header.getVariety(),
                            header.getCreatedBy(),
                            header.getAttribute(),
                            header.getStatus(),
                            header.getCreationDate()
                                             );
                    insertheader(header1);

                }
            }

            @Override
            public void onFailure(Call<List<Header>> call, Throwable t) {

            }
        });
    }

    public void getfarmerservice(){
        Call<List<FarmerMasterGet>> call=loginService.getfarmerservice();
        call.enqueue(new Callback<List<FarmerMasterGet>>() {
            @Override
            public void onResponse(Call<List<FarmerMasterGet>> call, Response<List<FarmerMasterGet>> response) {
                List<FarmerMasterGet> data=response.body();

                for(FarmerMasterGet farmerMasterGet:data){


                    FarmerMasterGet farmerMasterGet1=new FarmerMasterGet(
                            farmerMasterGet.getFarmerCode(),
                            farmerMasterGet.getFarmerCategory(),
                            farmerMasterGet.getFarmerName(),
                            farmerMasterGet.getFarM_FATHER_NAME(),
                            farmerMasterGet.getVillageCode(),
                            farmerMasterGet.getSoilType(),
                            farmerMasterGet.getFarM_ADDRESS1(),
                            farmerMasterGet.getFarM_ADDRESS2(),
                            farmerMasterGet.getFarM_ADDRESS3(),
                            farmerMasterGet.getCountry(),
                            farmerMasterGet.getPinCode(),
                            farmerMasterGet.getMobileNo(),
                            farmerMasterGet.getAlertMsg(),
                            farmerMasterGet.getAttributE1(),
                            farmerMasterGet.getAttributE4(),
                            farmerMasterGet.getCreatedBy(),
                            farmerMasterGet.getCreatedDate(),
                            farmerMasterGet.getUpdatedBy(),
                            farmerMasterGet.getUpdatedDate(),
                            farmerMasterGet.getStatus(),
                            farmerMasterGet.getFlag()
                    );
                    insertfarmer(farmerMasterGet1);


                }
            }

            @Override
            public void onFailure(Call<List<FarmerMasterGet>> call, Throwable t) {

            }
        });
    }
public  void getvarietyservice(){
        Call<List<Variety>> call=loginService.getvarietyservice();
        call.enqueue(new Callback<List<Variety>>() {
            @Override
            public void onResponse(Call<List<Variety>> call, Response<List<Variety>> response) {
                List<Variety> data =response.body();
                for(Variety variety:data){
                    Variety variety1=new Variety(
                            variety.getVariety(),
                            variety.getVarietyType(),
                            variety.getVarietyName(),
                            variety.getVarietyDescription(),
                            variety.getStatus()
                    );

                    insertvariety(variety);
                }
            }

            @Override
            public void onFailure(Call<List<Variety>> call, Throwable t) {

            }
        });
}
public  void getData(){
    Call<List<Login>> call=loginService.getData();
    call.enqueue(new Callback<List<Login>>() {
        @Override
        public void onResponse(Call<List<Login>> call, Response<List<Login>> response) {
            List<Login> data =response.body();

            for(Login i: data){
                 Login login= new Login(i.getUserID(),
                         i.getUserName(),
                         i.getPassword(),
                         i.getUserERPName(),
                         i.getEmployeeCode(),
                         i.getDesignation(),
                         i.getDepartment(),
                         i.getUserRights(),
                         i.getSyncID(),
                         i.getSyncPassword(),
                         i.getMobileNumber(),
                         i.getEmailID(),
                         i.getStatus());


                     InsertLogin(login);

                Log.d("i",i.getUserID());


            }
        }

        @Override
        public void onFailure(Call<List<Login>> call, Throwable t) {

        }
    });

}

public  void getcropservice(){
        Call<List<Crop>>  call=loginService.getcropservice();
        call.enqueue(new Callback<List<Crop>>() {
            @Override
            public void onResponse(Call<List<Crop>> call, Response<List<Crop>> response) {
                List<Crop> data= response.body();

                for(Crop a:data){
                    Crop crop=new Crop(a.getCrop(),a.getCropYear(),a.getStatus());
                    Log.d("a",a.getCrop());

                    insertcrop(crop);

                }
            }

            @Override
            public void onFailure(Call<List<Crop>> call, Throwable t) {

            }
        });
}

public  void  getorganizationservice(){
        Call<List<Organization>> call=loginService.getorganizationservice();
        call.enqueue(new Callback<List<Organization>>() {
            @Override
            public void onResponse(Call<List<Organization>> call, Response<List<Organization>> response) {
                List<Organization> data =response.body();
                for(Organization b:data){
                     Organization organization=new Organization(
                                         b.getOrganizationCode(),b.getOrganizationName(),b.getOrganizationType(),b.getOrganizationAddress1(),b.getStatus()
                                        );

                    insertorganization(organization);
                    Log.d("b",b.getOrganizationCode());
                }
            }

            @Override
            public void onFailure(Call<List<Organization>> call, Throwable t) {

            }
        });
}
}
