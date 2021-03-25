package com.example.gpiproject.view;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gpiproject.R;
import com.example.gpiproject.Viewmodel.LoginViewModel;
import com.example.gpiproject.model.Header;
import com.example.gpiproject.model.Login;
import com.example.gpiproject.use.LandingPageViewModelFactory;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.RunnableFuture;

public class LandingPage extends AppCompatActivity {
  String RefId;
  String OrgCode;
  String DateTime;
  String DateTime1;
    private LoginViewModel loginViewModel;
    List<Header> headersGet = new ArrayList<>();
    String val;


    String code,code1;
  String password;
  TextView headerNotificationTv;
  TextView tv_org_code,et_ref_id,et_date;
  SharedPreferences sp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing_page);
        List<Login> userMasters = new ArrayList<>();
        tv_org_code=findViewById(R.id.tv_org_code2);
        et_ref_id=findViewById(R.id.et_ref_id2);
        et_date=findViewById(R.id.tv_date2);
        headerNotificationTv=findViewById(R.id.headerNotificationTv);
//        Intent intent = getIntent();
//        RefId = intent.getStringExtra("RefId");
//        RefId = intent.getStringExtra("RefId");
//        OrgCode = intent.getStringExtra("Orgcode");
//        code = intent.getStringExtra("usercode");
//        code1 = intent.getStringExtra("username1");
//
//        password = intent.getStringExtra("password");
//
        loginViewModel= ViewModelProviders.of(this,new LandingPageViewModelFactory(getApplication(),LandingPage.this)).get(LoginViewModel.class);

        sp=getApplicationContext().getSharedPreferences("data", Context.MODE_PRIVATE);
        OrgCode=sp.getString("Orgcode","");
        RefId=sp.getString("headerId","");

        Calendar calendar=Calendar.getInstance();
        SimpleDateFormat datetime= new SimpleDateFormat("dd-MM-YYYY");
        SimpleDateFormat datetime1= new SimpleDateFormat("YYYYMMdd");
        DateTime=datetime.format(calendar.getTime());
        DateTime1=datetime1.format(calendar.getTime());
////////////////////////////////////////////////////////////////////

        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
        String strDate= formatter.format(new Date());

        ////////////////////////////////////
        Toast.makeText(this, ""+strDate, Toast.LENGTH_LONG).show();


//

        tv_org_code.setText(OrgCode);
        et_ref_id.setText(RefId);
        et_date.setText(DateTime);






        loginViewModel.getheaderservice();




        headersGet=loginViewModel.getheaderDetailList();






    }

    @Override
    protected void onPause() {
        super.onPause();
//
    }
    @Override
    protected void onResume() {
        super.onResume();
        try{
            loginViewModel.getheaderservice();

            loginViewModel.getcropservice();

        }catch (Exception e){
            Toast.makeText(this, "Network Problem", Toast.LENGTH_SHORT).show();
        }

    }


    public void ppd(View view) {



        Boolean credential = false;

        if (headersGet != null && headersGet.size() != 0)
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
                /*userIn =  userMasters.stream().filter(userMaster -> userMaster.getEmpName().equals(userName)).findFirst().isPresent();
                 */
                credential = headersGet
                        .stream()
                        .filter(header -> header.getHeaderID().equals(RefId))
                        .findFirst()
                        .isPresent();
            }
          if (headersGet != null && headersGet.size() != 0)
              if (credential == true) {
                  Intent intent = new Intent(LandingPage.this,FarmerPurchase.class);

                  startActivity(intent);

              }else {
//                  Intent intent = new Intent(LandingPage.this, FarmerPurchase.class);
                  Intent intent = new Intent(LandingPage.this, HeaderCreation.class);

                            startActivity(intent);
              }

//        loginViewModel.getAllHeaderID().observe(LandingPage.this, new Observer<List<Header>>() {
//                               @Override
//                               public void onChanged(List<Header> headers) {
//                                   for(Header header:headers){
//
//
//                                        try{
//                                            if(RefId.equals(header.getHeaderID())){
//                                                Intent intent = new Intent(LandingPage.this,FarmerPurchase.class);
//                                                intent.putExtra("RefId",RefId);
//                                                intent.putExtra("code",code);
//                                                intent.putExtra("Orgcode",OrgCode);
//                                                startActivity(intent);
//                                            }else if(!RefId.equals(header.getHeaderID())){
//
//                                              HeaderVisible();
//                                                headerNotificationTv.postDelayed(new Runnable() {
//                                                    @Override
//                                                    public void run() {
//                                                        headerNotificationTv.setText("**Please Create Header**");
//                                                    }
//                                                }, 600);
//                                            }
//                                        }catch (Exception e){
//                                            Toast.makeText(LandingPage.this, "Please Create Header", Toast.LENGTH_SHORT).show();
//                                        }
//
//
//
//                                   }
//                               }
//                           });


    }

    public void HeaderCreation(View view) {
       new Thread(new Runnable() {
           @Override
           public void run() {
               loginViewModel.getheaderservice();

           }
       }).start();
//        loginViewModel.getAllHeaderID().observe(LandingPage.this, new Observer<List<Header>>() {
//            @Override
//            public void onChanged(List<Header> headers) {
//                for(Header header:headers){
//
//
//                    try{
//                        if(!RefId.equals(header.getHeaderID())){
//
//                            Intent intent = new Intent(LandingPage.this, HeaderCreation.class);
//                            intent.putExtra("person",RefId);
//                            intent.putExtra("crop",OrgCode);
//                            intent.putExtra("username",code);
//                            startActivity(intent);
//
//
//                        }
//                    }catch (Exception e){
//                        Toast.makeText(LandingPage.this, "Please Create Header", Toast.LENGTH_SHORT).show();
//                    }
//
//
//
//                }
//            }
//        });



    }


    public void sync(View view) {

        loginViewModel.getcropservice();

      Intent intent=new Intent(this,SyncActivity.class);
      startActivity(intent);





    }

}