package com.example.tvs.activity.activitys;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.telephony.SmsManager;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.example.tvs.R;
import com.example.tvs.activity.JavaMailAPI;
import com.example.tvs.activity.Model.IntimationModel;
import com.example.tvs.activity.Model.Location;
import com.example.tvs.activity.Model.ProductViewFactory;
import com.example.tvs.activity.ViewModel.ProductViewModel;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

public class OnlinePE extends AppCompatActivity {
 ImageView img_tools,img_fixture;
    String CreatedBy;
    String LocationId;
    SharedPreferences sp;
    private ProductViewModel productViewModel;
    Calendar calendar=Calendar.getInstance();
    SimpleDateFormat datetime= new SimpleDateFormat("dd-MMMM-YY  hh:mm:ss ");
    String DateTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_online_p_e);

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Online PE");

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        sp=getApplicationContext().getSharedPreferences("data", Context.MODE_PRIVATE);
        CreatedBy=sp.getString("Employeecode","");
        LocationId=sp.getString("LocationId","");
        DateTime=datetime.format(calendar.getTime());

        productViewModel= ViewModelProviders.of(this,new ProductViewFactory(getApplication(),OnlinePE.this)).get(ProductViewModel.class);
/////////////////////////////////////////////////////

        img_fixture=findViewById(R.id.img_fixture);
        img_tools=findViewById(R.id.img_tools);

        img_fixture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                YoYo.with(Techniques.FadeOut)
                        .duration(1000)
                        .repeat(0)
                        .playOn(img_fixture);
                String messages= "Online PE- Fixture ";

                if (TextUtils.isEmpty(messages)) {
                    Toast.makeText(getApplicationContext(), "Please Fill the Intimation Message ", Toast.LENGTH_LONG).show();
                }else{
                    com.example.tvs.activity.Model.OnlinePE onlinePE=new com.example.tvs.activity.Model.OnlinePE(
                            CreatedBy,LocationId,"","",DateTime,messages
                    );
                    productViewModel.insertOnlinePE(onlinePE);

                    senEmail();


                    if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                        if(checkSelfPermission(Manifest.permission.SEND_SMS)  == PackageManager.PERMISSION_GRANTED){
                            SendSMS();
                        }else {
                            requestPermissions(new String[]{Manifest.permission.SEND_SMS},1);
                        }
                    }


                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            PostDataDatails();

                        }
                    }, 3000);
                }



            }

        });
        img_tools.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                YoYo.with(Techniques.FadeOut)
                        .duration(1000)
                        .repeat(0)
                        .playOn(img_tools);
                String messages= "Online PE- Tools ";

                if (TextUtils.isEmpty(messages)) {
                    Toast.makeText(getApplicationContext(), "Please Fill the Intimation Message ", Toast.LENGTH_LONG).show();
                }else{
                    com.example.tvs.activity.Model.OnlinePE onlinePE=new com.example.tvs.activity.Model.OnlinePE(
                            CreatedBy,LocationId,"","",DateTime,messages
                    );
                    productViewModel.insertOnlinePE(onlinePE);

                    senEmailt();


                    if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                        if(checkSelfPermission(Manifest.permission.SEND_SMS)  == PackageManager.PERMISSION_GRANTED){
                            SendSMSt();
                        }else {
                            requestPermissions(new String[]{Manifest.permission.SEND_SMS},1);
                        }
                    }


                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            PostDataDatails();

                        }
                    }, 3000);
                }



            }

        });
    }

    private void  PostDataDatails(){
        try {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Confirm !!!");
            builder.setMessage("ARE YOU SURE TO MAKE FINAL SYNC");
            builder.setCancelable(false);
            builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                    List<com.example.tvs.activity.Model.OnlinePE> list = productViewModel.getOnlinePEList();

                    System.out.println("************************* START");
                    if (list != null)
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                            list.forEach(System.out::println);
                        }
                    if (list != null)
                        productViewModel.postonlinepeService(list);

                    System.out.println("************************* END");

                    System.out.println("************************* LOT Creation retrive STARTS");


                }
            });

            builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Toast.makeText(getApplicationContext(), "SKIPPED SYNC", Toast.LENGTH_SHORT).show();
                }
            });

            builder.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void senEmail() {

        String mSubject = "Online PE Fixture";
        String mMessage =  " Online PE Fixture Message:\n Bay no: xxxx \n Cell Name : yyyy\n";
/////////////////////////////////////////////////////
        productViewModel.getLocationDatails(LocationId).observe(this, new Observer<List<Location>>() {
            @Override
            public void onChanged(List<Location> locations) {
                for(Location ii:locations){
                    String EmailIds=ii.getEmail().toString().trim();
                    JavaMailAPI javaMailAPI = new JavaMailAPI(OnlinePE.this, EmailIds, mSubject, mMessage);

                    javaMailAPI.execute();
                }
            }
        });
        ///////////////////////////////////////////////

//        JavaMailAPI javaMailAPI = new JavaMailAPI(this, EmailId, mSubject, mMessage);

//        javaMailAPI.execute();
        Toast.makeText(this, "Sended", Toast.LENGTH_SHORT).show();
    }
    private void senEmailt() {

        String mSubject = "Online PE Tools";
        String mMessage =  " Online PE Tools Message:\n Bay no: xxxx \n Cell Name : yyyy\n";
/////////////////////////////////////////////////////
        productViewModel.getLocationDatails(LocationId).observe(this, new Observer<List<Location>>() {
            @Override
            public void onChanged(List<Location> locations) {
                for(Location ii:locations){
                    String EmailIds=ii.getEmail().toString().trim();
                    JavaMailAPI javaMailAPI = new JavaMailAPI(OnlinePE.this, EmailIds, mSubject, mMessage);

                    javaMailAPI.execute();
                }
            }
        });
        ///////////////////////////////////////////////

//        JavaMailAPI javaMailAPI = new JavaMailAPI(this, EmailId, mSubject, mMessage);

//        javaMailAPI.execute();
        Toast.makeText(this, "Sended", Toast.LENGTH_SHORT).show();
    }



    private void SendSMS() {
        String messages= "Online PE Fixture Message:\n Bay no: xxxx \n Cell Name : yyyy\n";

        ///////////////////////////////////////
        productViewModel.getLocationDatails(LocationId).observe(this, new Observer<List<Location>>() {
            @Override
            public void onChanged(List<Location> locations) {
                for(Location i:locations){
                    String PhoneNo=i.getMobileNumber().toString().trim();
                    try {
                        SmsManager smsManager=SmsManager.getDefault();
                        smsManager.sendTextMessage(PhoneNo,null,messages,null,null);


                    }catch (Exception e){}

                }
                Toast.makeText(OnlinePE.this, "Sended", Toast.LENGTH_SHORT).show();
            }
        });


    }
    private void SendSMSt() {
        String messages= "Online PE Tools Message:\n Bay no: xxxx \n Cell Name : yyyy\n";

        ///////////////////////////////////////
        productViewModel.getLocationDatails(LocationId).observe(this, new Observer<List<Location>>() {
            @Override
            public void onChanged(List<Location> locations) {
                for(Location i:locations){
                    String PhoneNo=i.getMobileNumber().toString().trim();
                    try {
                        SmsManager smsManager=SmsManager.getDefault();
                        smsManager.sendTextMessage(PhoneNo,null,messages,null,null);


                    }catch (Exception e){}

                }
                Toast.makeText(OnlinePE.this, "Sended", Toast.LENGTH_SHORT).show();
            }
        });


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                super.onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}