package com.example.tvs.activity.activitys;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
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
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.example.tvs.R;
import com.example.tvs.activity.Home;
import com.example.tvs.activity.JavaMailAPI;
import com.example.tvs.activity.Model.BreakDownmodel;
import com.example.tvs.activity.Model.Location;
import com.example.tvs.activity.Model.ProductViewFactory;
import com.example.tvs.activity.ViewModel.ProductViewModel;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

public class BreakDown extends AppCompatActivity {
    String CreatedBy;
    String LocationId;
    int A=0;

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
    LinearLayout linearLayoutbd;
    Button btn_problemSolved;
    TextView sendtxt;
    TextView send12txt;
    TextView send13txt;
    ImageView send;
    ImageView send12;
    ImageView send13;
    private ProductViewModel productViewModel;

    Calendar calendar=Calendar.getInstance();
    SimpleDateFormat datetime= new SimpleDateFormat("dd-MMMM-YY  hh:mm:ss ");



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_break_down);

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Breakdown");

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
//
        sp=getApplicationContext().getSharedPreferences("data", Context.MODE_PRIVATE);
        CreatedBy=sp.getString("Employeecode","");
        LocationId=sp.getString("LocationId","");


        DateTime=datetime.format(calendar.getTime());
        chronometer=findViewById(R.id.chronometeraction);
        linearLayout = (LinearLayout) findViewById(R.id.visible);
        linearLayoutbd = (LinearLayout) findViewById(R.id.Breakdownlayout);
        btn_problemSolved =  findViewById(R.id.btn_problemSolved);
        sendtxt =  findViewById(R.id.sendtxt);
        send12txt =  findViewById(R.id.send12txt);
        send13txt =  findViewById(R.id.send13txt);
        send12 =  findViewById(R.id.send12);
        send13 =  findViewById(R.id.send13);

        vibrator=(Vibrator) getSystemService(VIBRATOR_SERVICE);


        send=findViewById(R.id.send);


        productViewModel= ViewModelProviders.of(this,new ProductViewFactory(getApplication(),BreakDown.this)).get(ProductViewModel.class);

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //////////////////////////////////
                AlertDialog.Builder builder = new AlertDialog.Builder(BreakDown.this);
                builder.setTitle("       !!!Confirm !!!");
                builder.setIcon(R.drawable.po);
                builder.setMessage("Do you want to Electric BreakDown?")
                        .setCancelable(false)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {

                                /////////////////////////////////////
//                                YoYo.with(Techniques.FadeOut)
//                                        .duration(1000)
//                                        .repeat(0)
//                                        .playOn(send);
                                ////////////////////////////////

                                linearLayout.setVisibility(View.VISIBLE);
                                linearLayoutbd.setVisibility(View.INVISIBLE);

                                if(!runing){
                                    chronometer.setBase(SystemClock.elapsedRealtime()-pauseoffset);
                                    chronometer.start();
                                    runing=true;
                                }
                                btn_problemSolved.setVisibility(View.VISIBLE);

                                ///////////////////////////////////////////
                                String Time=chronometer.getText().toString().trim();
                                BreakDownmodel i=new BreakDownmodel(
                                        CreatedBy,
                                        LocationId,
                                        "","",
                                        DateTime," Bay no: xxxx \n Cell Name : yyyy\n ELECTRIC BREAKDOWN",
                                        Time

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
                                }, 3000);

                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                                Toast.makeText(BreakDown.this, "Skiped ", Toast.LENGTH_SHORT).show();
                                linearLayoutbd.setVisibility(View.VISIBLE);

                            }
                        });
                AlertDialog alert = builder.create();
                alert.show();


                /////////////////////////////////////////




            }

        });

        /////////////////////////////////////////other breakdown//////////////////////////
        send13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //////////////////////////////////
                AlertDialog.Builder builder = new AlertDialog.Builder(BreakDown.this);
                builder.setTitle("       !!!Confirm !!!");
                builder.setIcon(R.drawable.po);
                builder.setMessage("Do you want to other BreakDown?")
                        .setCancelable(false)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {

                                /////////////////////////////////////
//                                YoYo.with(Techniques.FadeOut)
//                                        .duration(1000)
//                                        .repeat(0)
//                                        .playOn(send);
                                ////////////////////////////////

                                linearLayout.setVisibility(View.VISIBLE);
                                linearLayoutbd.setVisibility(View.INVISIBLE);

                                if(!runing){
                                    chronometer.setBase(SystemClock.elapsedRealtime()-pauseoffset);
                                    chronometer.start();
                                    runing=true;
                                }
                                btn_problemSolved.setVisibility(View.VISIBLE);

                                ///////////////////////////////////////////
                                String Time=chronometer.getText().toString().trim();
                                BreakDownmodel i=new BreakDownmodel(
                                        CreatedBy,
                                        LocationId,
                                        "","",
                                        DateTime," Bay no: xxxx \n Cell Name : yyyy\n OTHER BREAKDOWN",
                                        Time

                                );

                                productViewModel.insertBreakDown(i);

                                senEmail13();

                                if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                                    if(checkSelfPermission(Manifest.permission.SEND_SMS)  == PackageManager.PERMISSION_GRANTED){
                                        SendSMS13();
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
                                }, 3000);

                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                                Toast.makeText(BreakDown.this, "Skiped ", Toast.LENGTH_SHORT).show();
                                linearLayoutbd.setVisibility(View.VISIBLE);

                            }
                        });
                AlertDialog alert = builder.create();
                alert.show();


                /////////////////////////////////////////




            }

        });

        //////////////////////////////Machine BREAKDOWN//////////////////////
        send12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //////////////////////////////////
                AlertDialog.Builder builder = new AlertDialog.Builder(BreakDown.this);
                builder.setTitle("       !!!Confirm !!!");
                builder.setIcon(R.drawable.po);
                builder.setMessage("Do you want to Machine BreakDown?")
                        .setCancelable(false)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {

                                /////////////////////////////////////
//                                YoYo.with(Techniques.FadeOut)
//                                        .duration(1000)
//                                        .repeat(0)
//                                        .playOn(send);
                                ////////////////////////////////

                                linearLayout.setVisibility(View.VISIBLE);
                                linearLayoutbd.setVisibility(View.INVISIBLE);

                                if(!runing){
                                    chronometer.setBase(SystemClock.elapsedRealtime()-pauseoffset);
                                    chronometer.start();
                                    runing=true;
                                }
                                btn_problemSolved.setVisibility(View.VISIBLE);

                                ///////////////////////////////////////////
                                String Time=chronometer.getText().toString().trim();
                                BreakDownmodel i=new BreakDownmodel(
                                        CreatedBy,
                                        LocationId,
                                        "","",
                                        DateTime," Bay no: xxxx \n Cell Name : yyyy\n MACHINE BREAKDOWN",
                                        Time

                                );

                                productViewModel.insertBreakDown(i);

                                senEmail12();

                                if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                                    if(checkSelfPermission(Manifest.permission.SEND_SMS)  == PackageManager.PERMISSION_GRANTED){
                                        SendSMS12();
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
                                }, 3000);

                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                                Toast.makeText(BreakDown.this, "Skiped ", Toast.LENGTH_SHORT).show();
                                linearLayoutbd.setVisibility(View.VISIBLE);

                            }
                        });
                AlertDialog alert = builder.create();
                alert.show();


                /////////////////////////////////////////




            }

        });


        ///////////////////////////////////////////////////////


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
    private void  PostDataDatails123(){
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

                     Intent intent=new Intent(BreakDown.this,Home.class);
                     startActivity(intent);



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

        String mSubject = "Electric Breakdown";
        String mMessage = " Bay no: xxxx \n Cell Name : yyyy\n  Electric Breakdown Message......";
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
    private void senEmail13() {

        String mSubject = " Other Breakdown ";
        String mMessage = " Bay no: xxxx \n Cell Name : yyyy\n Other Breakdown Message......";
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

    private void senEmail12() {

        String mSubject = "Machine Breakdown ";
        String mMessage = " Bay no: xxxx \n Cell Name : yyyy\n Machine Breakdown Message......";
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
    private void senEmailpr() {

        String mSubject = "Breakdown Solved";
        String mMessage = " Bay no: xxxx \n Cell Name : yyyy\n Problem Solved Message......";
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
        String messages=" Bay no: xxxx \n Cell Name : yyyy\n Electric Breakdown Message....:)";

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
    private void SendSMS13() {
        String messages=" Bay no: xxxx \n Cell Name : yyyy\n  Other Breakdown Message....:)";

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
    private void SendSMS12() {
        String messages=" Bay no: xxxx \n Cell Name : yyyy\n  Machine Breakdown Message....:)";

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
    private void SendSMSpr() {
        String messages=" Bay no: xxxx \n Cell Name : yyyy\n Problem Solved Message....:)";

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
                .playOn(btn_problemSolved);
//        Intent intent=new Intent(this, Home.class);
//        startActivity(intent);
 /////////////////////////////////////////////////////
        String Time=chronometer.getText().toString();
        BreakDownmodel i=new BreakDownmodel(
                CreatedBy,
                LocationId,
                "","",
                DateTime," Bay no: xxxx \n Cell Name : yyyy\n PROBLEM SOLVED",
                Time

        );

        productViewModel.insertBreakDown(i);

        senEmailpr();

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            if(checkSelfPermission(Manifest.permission.SEND_SMS)  == PackageManager.PERMISSION_GRANTED){
                SendSMSpr();
            }else {
                requestPermissions(new String[]{Manifest.permission.SEND_SMS},1);
            }
        }
        vibrator.vibrate(1000);


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                PostDataDatails123();


            }
        }, 5000);


        //////////////////////////////////////////////////


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

//    public void test(View view) {
//        String a=chronometer.getText().toString();
//        Toast.makeText(this, a, Toast.LENGTH_SHORT).show();
//            }
}