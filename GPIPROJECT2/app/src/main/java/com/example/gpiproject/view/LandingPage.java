package com.example.gpiproject.view;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gpiproject.R;
import com.example.gpiproject.Viewmodel.LoginViewModel;
import com.example.gpiproject.model.Header;
import com.example.gpiproject.model.Login;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.RunnableFuture;

public class LandingPage extends AppCompatActivity {
  String RefId;
  String OrgCode;
  String DateTime;
  String DateTime1;
    private LoginViewModel loginViewModel;
    List<Login> userMasters = new ArrayList<>();
    String val;


    String code;
  String password;
  TextView headerNotificationTv;
  TextView tv_org_code,et_ref_id,et_date;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing_page);
        List<Login> userMasters = new ArrayList<>();
        tv_org_code=findViewById(R.id.tv_org_code2);
        et_ref_id=findViewById(R.id.et_ref_id2);
        et_date=findViewById(R.id.tv_date2);
        headerNotificationTv=findViewById(R.id.headerNotificationTv);
        Intent intent = getIntent();
        RefId = intent.getStringExtra("RefId");
        RefId = intent.getStringExtra("RefId");
        OrgCode = intent.getStringExtra("Orgcode");
        code = intent.getStringExtra("usercode");
        password = intent.getStringExtra("password");
//
        loginViewModel= ViewModelProviders.of(this).get(LoginViewModel.class);


        Calendar calendar=Calendar.getInstance();
        SimpleDateFormat datetime= new SimpleDateFormat("DD-MM-YYYY");
        SimpleDateFormat datetime1= new SimpleDateFormat("YYYYMMDD");
        DateTime=datetime.format(calendar.getTime());
        DateTime1=datetime1.format(calendar.getTime());
//

        tv_org_code.setText(OrgCode);
        et_ref_id.setText(RefId);
        et_date.setText(DateTime);
        loginViewModel.getcropservice();



        try{
            loginViewModel.getheaderservice();

        }catch (Exception e){
            Toast.makeText(this, "NetWork Problem", Toast.LENGTH_SHORT).show();
        }




    }

    @Override
    protected void onPause() {
        super.onPause();
//
    }

    public void ppd(View view) {

        loginViewModel.getAllHeaderID().observe(LandingPage.this, new Observer<List<Header>>() {
                               @Override
                               public void onChanged(List<Header> headers) {
                                   for(Header header:headers){


                                        try{
                                            if(RefId.equals(header.getHeaderID())){

                                                Intent intent = new Intent(LandingPage.this,FarmerPurchase.class);
                                                intent.putExtra("RefId",RefId);
                                                intent.putExtra("code",code);
                                                intent.putExtra("Orgcode",OrgCode);
                                                startActivity(intent);

//                                           finish();
//                                           Intent intent1 = new Intent(getApplicationContext(), UserLogin.class);


//                                           startActivity(intent1);

                                            }else if(!RefId.equals(header.getHeaderID())){

                                              HeaderVisible();
                                                headerNotificationTv.postDelayed(new Runnable() {
                                                    @Override
                                                    public void run() {
                                                        headerNotificationTv.setText("**Please Create Header**");
                                                    }
                                                }, 300);
                                            }
                                        }catch (Exception e){
                                            Toast.makeText(LandingPage.this, "Please Create Header", Toast.LENGTH_SHORT).show();
                                        }



                                   }
                               }
                           });


    }

    public void HeaderCreation(View view) {
        loginViewModel.getAllHeaderID().observe(LandingPage.this, new Observer<List<Header>>() {
            @Override
            public void onChanged(List<Header> headers) {
                for(Header header:headers){


                    try{
                        if(!RefId.equals(header.getHeaderID())){

                            Intent intent = new Intent(LandingPage.this, HeaderCreation.class);
                            intent.putExtra("person",RefId);
                            intent.putExtra("crop",OrgCode);
                            intent.putExtra("username",code);
                            startActivity(intent);

//                                           startActivity(intent1);

                        }
                    }catch (Exception e){
                        Toast.makeText(LandingPage.this, "Please Create Header", Toast.LENGTH_SHORT).show();
                    }



                }
            }
        });



    }

    private void HeaderVisible() {
//        headerNotificationTv.setVisibility(View.VISIBLE);

    }
    public void sync(View view) {

        AlertDialog.Builder builder = new AlertDialog.Builder(LandingPage.this);
        builder.setTitle("Confirm !!!!");
        builder.setMessage("ARE YOU SURE TO MAKE  SYNC");
        builder.setCancelable(false);
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                loginViewModel.getfarmerservice();
                Toast.makeText(LandingPage.this, "Sync", Toast.LENGTH_SHORT).show();

                loginViewModel.getAllHeaderID();
                Toast.makeText(LandingPage.this, "Please Wait", Toast.LENGTH_SHORT).show();

                loginViewModel.getcropservice();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getApplicationContext(), "SKIPPED SYNC", Toast.LENGTH_SHORT).show();
            }
        });

        builder.show();





    }
}