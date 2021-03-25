package com.example.newitcdemo;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.telephony.SmsManager;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.example.newitcdemo.Model.BreakDownmodel;
import com.example.newitcdemo.Model.IntimationModel;
import com.example.newitcdemo.Model.Location;
import com.example.newitcdemo.Model.ProductViewFactory;
import com.example.newitcdemo.ViewModel.ProductViewModel;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

public class Intimation extends AppCompatActivity {
    String CreatedBy;
    String LocationId;

    SharedPreferences sp;
    String InchargeName;
    String PhoneNumber;
    String EmailId;
    String LocationName;
    EditText et_intimation;
    String DateTime;

    Button send;
    Button btn_back_intimation;
    private ProductViewModel productViewModel;
    Calendar calendar=Calendar.getInstance();
    SimpleDateFormat datetime= new SimpleDateFormat("dd-MMMM-YY  hh:mm:ss ");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intimation);

        sp=getApplicationContext().getSharedPreferences("data", Context.MODE_PRIVATE);
        CreatedBy=sp.getString("CreatedBy","");
        LocationId=sp.getString("LocationId","");

        send=findViewById(R.id.send1);
        btn_back_intimation=findViewById(R.id.btn_back_intimation);
        et_intimation=findViewById(R.id.et_intimation);
        DateTime=datetime.format(calendar.getTime());

        productViewModel= ViewModelProviders.of(this,new ProductViewFactory(getApplication(),Intimation.this)).get(ProductViewModel.class);

//        productViewModel.getLocationDatails(LocationId).observe(this, new Observer<Location>() {
//            @Override
//            public void onChanged(Location location) {
////
////                InchargeName=  location.getInchargeName();
////                LocationName= location.getLocationName();
////                EmailId=  location.getMailID();
////                PhoneNumber=  location.getPhoneNumber();
//
//
//            }
//        });

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                YoYo.with(Techniques.FadeOut)
                        .duration(1000)
                        .repeat(0)
                        .playOn(send);
                String messages=et_intimation.getText().toString().trim();

                if (TextUtils.isEmpty(messages)) {
                    Toast.makeText(getApplicationContext(), "Please Fill the Intimation Message ", Toast.LENGTH_LONG).show();
                }else{
                    IntimationModel intimationModel=new IntimationModel(
                            CreatedBy,
                            DateTime,
                            CreatedBy,
                            InchargeName,
                            LocationId,
                            LocationName
                    );
                    productViewModel.insertIntimation(intimationModel);

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
                            et_intimation.setText("");
                        }
                    }, 5000);
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

                    List<IntimationModel> list = productViewModel.getIntimationList();

                    System.out.println("************************* START");
                    if (list != null)
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                            list.forEach(System.out::println);
                        }
                    if (list != null)
                        productViewModel.postIntimationService(list);

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

        String mSubject = "Checking Intimation Subject";
        String mMessage = et_intimation.getText().toString().trim();


        JavaMailAPI javaMailAPI = new JavaMailAPI(this, EmailId, mSubject, mMessage);

        javaMailAPI.execute();
        Toast.makeText(this, "Sended", Toast.LENGTH_SHORT).show();
    }


    private void SendSMS() {
        String messages=et_intimation.getText().toString().trim();

        try {
            SmsManager smsManager=SmsManager.getDefault();
            smsManager.sendTextMessage(PhoneNumber,null,messages,null,null);

            Toast.makeText(this, "Sended", Toast.LENGTH_SHORT).show();

        }catch (Exception e){}


    }


    public void IntimationBack(View view) {
        YoYo.with(Techniques.FadeIn)
                .duration(1000)
                .repeat(0)
                .playOn(btn_back_intimation);
        Intent intent=new Intent(this,LandingPage.class);
        startActivity(intent);
    }
}