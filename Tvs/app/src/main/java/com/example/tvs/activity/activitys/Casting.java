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
import com.example.tvs.activity.Model.Location;
import com.example.tvs.activity.Model.OnlinePE;
import com.example.tvs.activity.Model.ProductViewFactory;
import com.example.tvs.activity.ViewModel.ProductViewModel;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

public class Casting extends AppCompatActivity {
ImageView imgcasting;
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
        setContentView(R.layout.activity_casting);

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Casting Availability");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        sp=getApplicationContext().getSharedPreferences("data", Context.MODE_PRIVATE);
        CreatedBy=sp.getString("Employeecode","");
        LocationId=sp.getString("LocationId","");
        DateTime=datetime.format(calendar.getTime());

        productViewModel= ViewModelProviders.of(this,new ProductViewFactory(getApplication(),Casting.this)).get(ProductViewModel.class);
/////////////////////////////////////////////////////

        imgcasting=findViewById(R.id.imgcasting);

        imgcasting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                YoYo.with(Techniques.FadeOut)
                        .duration(1000)
                        .repeat(0)
                        .playOn(imgcasting);
                String messages= "Casting Availability";

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


    }
    private void senEmail() {

        String mSubject = "Casting Availability";
        String mMessage =  " Need for Casting Availability :\n Bay no: xxxx \n Cell Name : yyyy\n";
/////////////////////////////////////////////////////
        productViewModel.getLocationDatails(LocationId).observe(this, new Observer<List<Location>>() {
            @Override
            public void onChanged(List<Location> locations) {
                for(Location ii:locations){
                    String EmailIds=ii.getEmail().toString().trim();
                    JavaMailAPI javaMailAPI = new JavaMailAPI(Casting.this, EmailIds, mSubject, mMessage);

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
        String messages= " Need For Casting Availability:\n Bay no: xxxx \n Cell Name : yyyy\n";

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
                Toast.makeText(Casting.this, "Sended", Toast.LENGTH_SHORT).show();
            }
        });


    }
    private void  PostDataDatails(){
        try {
            List<OnlinePE> list = productViewModel.getOnlinePEList();

            System.out.println("************************* START");
            if (list != null)
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    list.forEach(System.out::println);
                }
            if (list != null)
                productViewModel.postonlinepeService(list);

            System.out.println("************************* END");

            System.out.println("************************* LOT Creation retrive STARTS");
        } catch (Exception e) {
            e.printStackTrace();
        }
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