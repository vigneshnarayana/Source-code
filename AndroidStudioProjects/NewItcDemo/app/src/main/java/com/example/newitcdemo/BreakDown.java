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
import android.os.SystemClock;
import android.os.Vibrator;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.example.newitcdemo.Model.BreakDownmodel;
import com.example.newitcdemo.Model.Location;
import com.example.newitcdemo.Model.Product;
import com.example.newitcdemo.Model.ProductViewFactory;
import com.example.newitcdemo.ViewModel.ProductViewModel;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

public class BreakDown extends AppCompatActivity {

    String CreatedBy;
    String LocationId;

    SharedPreferences sp;
    String InchargeName;
    String PhoneNumber;
    String EmailId;
    String LocationName;
    String DateTime;
    Vibrator vibrator;
    Button bt_back_breakdown;
    Chronometer chronometer;
    private long pauseoffset;
    private boolean runing;
    LinearLayout linearLayout;
    Button btn_problemSolved;




    ImageView send;
    private ProductViewModel productViewModel;

    Calendar calendar=Calendar.getInstance();
    SimpleDateFormat datetime= new SimpleDateFormat("dd-MMMM-YY  hh:mm:ss ");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_break_down);
        sp=getApplicationContext().getSharedPreferences("data", Context.MODE_PRIVATE);
        CreatedBy=sp.getString("Employeecode","");
        LocationId=sp.getString("LocationId","");


        DateTime=datetime.format(calendar.getTime());
        chronometer=findViewById(R.id.chronometeraction);
         linearLayout = (LinearLayout) findViewById(R.id.visible);
        btn_problemSolved =  findViewById(R.id.btn_problemSolved);

        vibrator=(Vibrator) getSystemService(VIBRATOR_SERVICE);


        send=findViewById(R.id.send);
        bt_back_breakdown=findViewById(R.id.bt_back_breakdown);

        productViewModel= ViewModelProviders.of(this,new ProductViewFactory(getApplication(),BreakDown.this)).get(ProductViewModel.class);





        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                YoYo.with(Techniques.FadeOut)
                        .duration(1000)
                        .repeat(0)
                        .playOn(send);
                ////////////////////////////////
                linearLayout.setVisibility(View.VISIBLE);
                if(!runing){
                    chronometer.setBase(SystemClock.elapsedRealtime()-pauseoffset);
                    chronometer.start();
                    runing=true;
                }
                btn_problemSolved.setVisibility(View.VISIBLE);
                //////////////////////////////////
                AlertDialog.Builder builder = new AlertDialog.Builder(BreakDown.this);
                builder.setTitle("       !!!Confirm !!!");
                builder.setIcon(R.drawable.po);
                builder.setMessage("Do you want to BreakDown?")
                        .setCancelable(false)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                BreakDownmodel i=new BreakDownmodel(
                                        CreatedBy,
                                        LocationId,
                                        LocationName,
                                        InchargeName,
                                        CreatedBy,
                                        DateTime
                                );

                                productViewModel.insertBreakDown(i);

                                senEmail();

                                if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                                    if(checkSelfPermission(Manifest.permission.SEND_SMS)  == PackageManager.PERMISSION_GRANTED){
                                        SendSMS();
                                    }else {
                                        requestPermissions(new String[]{Manifest.permission.SEND_SMS},1);
                                    }
                                }
                                vibrator.vibrate(1000);


                                new Handler().postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        PostDataDatails();
                                    }
                                }, 5000);

                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                                Toast.makeText(BreakDown.this, "Skiped ", Toast.LENGTH_SHORT).show();
                            }
                        });
                AlertDialog alert = builder.create();
                alert.show();


                /////////////////////////////////////////




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

                    List<BreakDownmodel> list = productViewModel.getBreakDownList();

                    System.out.println("************************* START");
                    if (list != null)
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                            list.forEach(System.out::println);
                        }
                    if (list != null)
                        productViewModel.postBreakDownServioce(list);

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

        String mSubject = "Checking Breakdown Subject";
        String mMessage = "Checking Breakdown Message......";
      productViewModel.getLocationDatails(LocationId).observe(this, new Observer<List<Location>>() {
          @Override
          public void onChanged(List<Location> locations) {
              for(Location ii:locations){
                  String EmailIds=ii.getEmail().toString().trim();
                  JavaMailAPI javaMailAPI = new JavaMailAPI(BreakDown.this, EmailIds, mSubject, mMessage);

                  javaMailAPI.execute();
              }
          }
      });


        Toast.makeText(this, "Sended", Toast.LENGTH_SHORT).show();
    }


    private void SendSMS() {
        String messages="Checking Breakdown Message....:)";

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
                Toast.makeText(BreakDown.this, "Sended", Toast.LENGTH_SHORT).show();
            }
        });



    }


    public void btnBreakdown(View view) {
        YoYo.with(Techniques.FadeIn)
                .duration(1000)
                .repeat(0)
                .playOn(bt_back_breakdown);
        Intent intent=new Intent(this,LandingPage.class);
        startActivity(intent);
    }



    public void pauseChronomer(View view) {
        if (runing){
            chronometer.stop();
            pauseoffset=SystemClock.elapsedRealtime() - chronometer.getBase();
            runing=false;
        }
    }

    public void resetChronomer(View view) {
        chronometer.setBase(SystemClock.elapsedRealtime());
        pauseoffset=0;
    }
}