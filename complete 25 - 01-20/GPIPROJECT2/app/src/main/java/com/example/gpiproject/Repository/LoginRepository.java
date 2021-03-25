package com.example.gpiproject.Repository;

import android.app.Activity;
import android.app.Application;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.util.Log;

import androidx.lifecycle.LiveData;

import com.example.gpiproject.Dao.DataBase;
import com.example.gpiproject.Dao.LoginDao;
import com.example.gpiproject.Dao.LotCreationDao;
import com.example.gpiproject.Dao.PurchaseDao;
import com.example.gpiproject.Dao.WeighmentDao;
import com.example.gpiproject.Service.FarmerPurchaseService;
import com.example.gpiproject.Service.LoginService;
import com.example.gpiproject.Service.RetrofitClientInstance;
import com.example.gpiproject.Service.WeighmentService;
import com.example.gpiproject.model.Crop;
import com.example.gpiproject.model.FarmerMasterGet;
import com.example.gpiproject.model.FarmerPurchaseModel;
import com.example.gpiproject.model.Header;
import com.example.gpiproject.model.HeaderPost;
import com.example.gpiproject.model.ItemMaster;
import com.example.gpiproject.model.Login;
import com.example.gpiproject.model.LotCreationModel;
import com.example.gpiproject.model.Organization;
import com.example.gpiproject.model.Variety;
import com.example.gpiproject.model.WeighmentGet;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginRepository {
      private DataBase database;
      private LoginDao loginDao;
      private PurchaseDao purchaseDao;
      private WeighmentDao weighmentDao;
      private LotCreationDao lotCreationDao;

      private   LoginService loginService;
      private FarmerPurchaseService farmerPurchaseService;
      private WeighmentService weighmentService;


    private LiveData<List<Organization>> getAllOrganizationDetails;
    private LiveData<List<Login>> getAllloginDetails;
    private LiveData<List<Header>> getAllHeaderID;
      private static final String TAG = "MainActivity";
    private ProgressDialog progressDialog = null;

    Application application;
    Activity activity;

    public LoginRepository(Application application,Activity activity) {
        this.application = application;
        this.activity = activity;
        database=  DataBase.getDataBase(application);

        loginDao=database.loginDao();
        purchaseDao=database.purchaseDao();
        lotCreationDao=database.lotCreationDao();
        weighmentDao=database.weighmentDao();

        loginService = RetrofitClientInstance.getClient().create(LoginService.class);
        farmerPurchaseService = RetrofitClientInstance.getClient().create(FarmerPurchaseService.class);
        weighmentService = RetrofitClientInstance.getClient().create(WeighmentService.class);
        getAllOrganizationDetails=loginDao.getAllOrganizationDetails();
        getAllloginDetails=loginDao.getAllloginDetails();
        getAllHeaderID=loginDao.getAllHeaderID();
    }

    ////////////////////// Room/////////////////////////
    public void InsertLogin(List<Login> login) {
        new InsertLogin(loginDao).execute(login);
    }
    public void insertcrop(List<Crop> crop){
        new insertcrop(loginDao).execute(crop);
    }

    public void insertvariety(Variety variety){
        new insertvariety(loginDao).execute(variety);
    }
    public void insertheader(List<Header> header){
        new insertheader(loginDao).execute(header);
    }
    public void insertfarmer(FarmerMasterGet farmerMasterGet){
        new insertfarmer(loginDao).execute(farmerMasterGet);
    }
    public void insertfarmerpurchase(FarmerPurchaseModel farmerPurchaseModel){
        new insertfarmerpurchase(purchaseDao).execute(farmerPurchaseModel);
    }

    public void Insertweighment(WeighmentGet weighmentGet){
        new Insertweighment(weighmentDao).execute(weighmentGet);
    }
    public  void insertitemmaster(ItemMaster itemMaster){
        new insertitemmaster(loginDao).execute(itemMaster);
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


    public List<Header> getheaderDetailList(){
        List<Header>  headerDetailList=null;
        try {
            headerDetailList = new getheaderDetailList(loginDao).execute().get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return headerDetailList;

    }

    private static class insertheader extends  AsyncTask<List<Header>,Void,Void>{
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
        protected Void doInBackground(List<Header>... headers) {
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

    private static class insertfarmerpurchase  extends AsyncTask<FarmerPurchaseModel,Void,Void>{
        private PurchaseDao purchaseDao;

        public insertfarmerpurchase(PurchaseDao purchaseDao) {
            this.purchaseDao = purchaseDao;
        }

        @Override
        protected Void doInBackground(FarmerPurchaseModel... farmerPurchaseModels) {
            purchaseDao.insertfarmerpurchase(farmerPurchaseModels[0]);
            return null;
        }
    }
    private static class Insertweighment extends AsyncTask<WeighmentGet, Void, Void> {

        private WeighmentDao weighmentDao;

        public Insertweighment(WeighmentDao weighmentDao) {
            this.weighmentDao = weighmentDao;
        }

        @Override
        protected Void doInBackground(WeighmentGet... weighmentGets) {
            weighmentDao.insertweighmentget(weighmentGets[0]);
            return null;
        }
    }

    private static class insertitemmaster extends AsyncTask<ItemMaster,Void,Void>{
        private LoginDao loginDao;

        public insertitemmaster(LoginDao loginDao) {
            this.loginDao = loginDao;
        }

        @Override
        protected Void doInBackground(ItemMaster... itemMasters) {
            loginDao.insertitemmaster(itemMasters[0]);
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

    private static class insertcrop extends AsyncTask<List<Crop>,Void,Void>{
        private LoginDao loginDao;

        public insertcrop(LoginDao loginDao) {
            this.loginDao = loginDao;
        }

        @Override
        protected Void doInBackground(List<Crop>... crops) {
            loginDao.insertcrop(crops[0]);
            return null;
        }
    }
//
    private static class InsertLogin extends AsyncTask<List<Login>, Void, Void> {
        private LoginDao loginDao;

        public InsertLogin(LoginDao loginDao) {
            this.loginDao = loginDao;
        }

        @Override
        protected Void doInBackground(List<Login>... logins) {
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

    private static  class getheaderDetailList extends AsyncTask<Void, Void, List<Header>> {
        private LoginDao loginDao;

        public getheaderDetailList(LoginDao loginDao) {
            this.loginDao = loginDao;
        }

        @Override
        protected List<Header> doInBackground(Void... voids) {
            return loginDao.getheaderDetailList();
        }
    }


////////////////////////////////////////////////////// Service/////////////////////////////////////////////////////////////////////////////////////////////

    public void getfarmerpurchaseservice(){
//        Call<List<FarmerPurchaseModel>> call=farmerPurchaseService.getfarmerpurchaseservice();
        Call<List<FarmerPurchaseModel>> call=farmerPurchaseService.getfarmerpurchaseservice();
        call.enqueue(new Callback<List<FarmerPurchaseModel>>() {
            @Override
            public void onResponse(Call<List<FarmerPurchaseModel>> call, Response<List<FarmerPurchaseModel>> response) {
                List<FarmerPurchaseModel> data=response.body();
                for(FarmerPurchaseModel i:data){
                    Log.d("purchase", i.getBaleNumber());

                  FarmerPurchaseModel farmerPurchaseModel=new FarmerPurchaseModel(
                          i.getTbLotNumber(),
                          i.getHeaderID(),
                          i.getAttribute3(),i.getOrgnCode(),
                          i.getBuyerGrade(),
                          i.getFarmerCode(),
                          i.getFarmerName(),
                          i.getBaleNumber(),
                          i.getSeries(),
                          i.getCrop(),
                          i.getVariety(),
                          i.getRejectStatus(),
                          i.getStatus(),
                          i.getHeaderStatus(),
                          i.getCreatedBy(),
                          i.getCreatedDate()

                  );
                    insertfarmerpurchase(farmerPurchaseModel);
                }
            }

            @Override
            public void onFailure(Call<List<FarmerPurchaseModel>> call, Throwable t) {

            }
        });
    }

    public  void getheaderservice(){
        Call<List<Header>> call=loginService.getheaderservice();
        call.enqueue(new Callback<List<Header>>() {
            @Override
            public void onResponse(Call<List<Header>> call, Response<List<Header>> response) {
                List<Header> data=response.body();
//                for(Header header: data){
//                    Header header1=new Header(
//                            header.getHeaderID(),
//                            header.getChequeNumber(),
//                            header.getFlag(),
//                            header.getOrganizationCode(),
//                            header.getPurchaseType(),
//                            header.getBuyerCode(),
//                            header.getPurchaseDocumentNumber(),
//                            header.getInvoiceDate(),
//                            header.getPurchaseDate(),
//                            header.getCrop(),
//                            header.getVariety(),
//                            header.getCreatedBy(),
//                            header.getAttribute(),
//                            header.getStatus(),
//                            header.getCreationDate()
//                                             );
//
//
//                    try{   insertheader(header1);
//                    }catch (Exception e){
//
//                    }


//                }
                System.out.println("************************** Header STARTS **************************");
                try {
                    if (!response.isSuccessful()) {
                        return;
                    }
                    List<Header> headers = response.body();
                    if(headers!=null){
//                    deleteUser();
                        for(Header header:headers){
                            System.out.print("<<<<<<<<<<<<<<>>>>>>>>>>>>>>>>>>>><<<<<<<<<<<>>>>>>>>>>>>"+header);
                        }
                        insertheader(headers);
                    }
                    System.out.println("************************** Header ENDS **************************");
                } catch (Exception e) {
                    System.out.println("Exception >>>>>>>>>> "+e);
                }



            }

            @Override
            public void onFailure(Call<List<Header>> call, Throwable t) {

            }
        });
    }

    public  void getitemmaster(){
     Call<List<ItemMaster>> call=loginService.getitemmaster();
     call.enqueue(new Callback<List<ItemMaster>>() {
         @Override
         public void onResponse(Call<List<ItemMaster>> call, Response<List<ItemMaster>> response) {
             List<ItemMaster> itemMasters=response.body();


             for(ItemMaster data: itemMasters){
                 ItemMaster itemMaster=new ItemMaster(
                         data.getItemCode(),
                         data.getItemCodeGrp(),
                         data.getItemGrp(),
                         data.getItemType()

                 );
                 insertitemmaster(itemMaster);
             }
         }

         @Override
         public void onFailure(Call<List<ItemMaster>> call, Throwable t) {

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
                    try{
                        insertfarmer(farmerMasterGet1);

                    }catch (Exception e){}


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
//            List<Login> data =response.body();

//            for(Login i: data){
//                 Login login= new Login(i.getUserID(),
//                         i.getUserName(),
//                         i.getPassword(),
//                         i.getUserERPName(),
//                         i.getEmployeeCode(),
//                         i.getDesignation(),
//                         i.getDepartment(),
//                         i.getUserRights(),
//                         i.getSyncID(),
//                         i.getSyncPassword(),
//                         i.getMobileNumber(),
//                         i.getEmailID(),
//                         i.getStatus());
//
//                       try{  InsertLogin(login);}catch (Exception e){}
//
//
//                Log.d("i",i.getUserID());
//
//
//            }

            System.out.println("************************** USER STARTS **************************");
            try {
                if (!response.isSuccessful()) {
                    return;
                }
                List<Login> userMasters = response.body();
                if(userMasters!=null){
//                    deleteUser();
                    for(Login userMaster:userMasters){
                        System.out.print("<<<<<<<<<<<<<<>>>>>>>>>>>>>>>>>>>><<<<<<<<<<<>>>>>>>>>>>>"+userMaster);
                    }
                    InsertLogin(userMasters);
                }
                System.out.println("************************** USER ENDS **************************");
            } catch (Exception e) {
                System.out.println("Exception >>>>>>>>>> "+e);
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


//                for(Crop a:data){
//                    Crop crop=new Crop(a.getCrop(),a.getCropYear(),a.getStatus());
//                    Log.d("a",a.getCrop());
//                   try{
//                       insertcrop(crop);
//
//                   }catch (Exception e){}

                    System.out.println("************************** Crop STARTS **************************");
                    try {
                        if (!response.isSuccessful()) {
                            return;
                        }
                        List<Crop> data= response.body();
                        if(data!=null){
//                    deleteUser();
                            for(Crop crop:data){
                                System.out.print("<<<<<<<<<<<<<<>>>>>>>>>>>>>>>>>>>><<<<<<<<<<<>>>>>>>>>>>>"+crop);
                            }
                            insertcrop(data);
                        }
                        System.out.println("************************** Crop ENDS **************************");
                    } catch (Exception e) {
                        System.out.println("Exception >>>>>>>>>> "+e);
                    }



            }

            @Override
            public void onFailure(Call<List<Crop>> call, Throwable t) {

            }
        });
}

public  void  getweighmentservice(){
        Call<List<WeighmentGet>> call=weighmentService.getweighmentservice();
        call.enqueue(new Callback<List<WeighmentGet>>() {
            @Override
            public void onResponse(Call<List<WeighmentGet>> call, Response<List<WeighmentGet>> response) {
                List<WeighmentGet> data=response.body();

                for(WeighmentGet i: data){
                    WeighmentGet weighmentGet=new WeighmentGet(
                            i.getHeaderID(),
                            i.getBaleNumber(),
                            i.getBuyerGrade(),
                            i.getClassGrade(),
                            i.getRate(),
                            i.getRejectStatus(),
                            i.getRejectedType()
                    );
                    Insertweighment(weighmentGet);


                    Log.d("aaa", i.getBaleNumber());

                }
            }

            @Override
            public void onFailure(Call<List<WeighmentGet>> call, Throwable t) {

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
                                         b.getOrganizationCode(),
                             b.getOrganizationName(),b.getOrganizationType()
                             ,b.getOrganizationAddress1(),
                             b.getStatus()
                                        );
                    try{
                        insertorganization(organization);
                    }catch (Exception e){}

                    Log.d("b",b.getOrganizationCode());
                }
            }

            @Override
            public void onFailure(Call<List<Organization>> call, Throwable t) {

            }
        });
}
}
