package com.example.gpiproject.view;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gpiproject.R;
import com.example.gpiproject.Viewmodel.LoginViewModel;
import com.example.gpiproject.use.LandingPageViewModelFactory;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class SyncActivity extends AppCompatActivity {

    private LoginViewModel loginViewModel;
    SharedPreferences sp;
    String OrgCode,RefId,DateTime;
    TextView orgcode,datetime1,headerid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sync);

        loginViewModel= ViewModelProviders.of(this,new LandingPageViewModelFactory(getApplication(),SyncActivity.this)).get(LoginViewModel.class);


        sp=getApplicationContext().getSharedPreferences("data", Context.MODE_PRIVATE);
        OrgCode=sp.getString("Orgcode","");
        RefId=sp.getString("headerId","");

        Calendar calendar=Calendar.getInstance();
        SimpleDateFormat datetime= new SimpleDateFormat("dd-MM-YYYY");
        DateTime=datetime.format(calendar.getTime());


        headerid=findViewById(R.id.headerid);
        datetime1=findViewById(R.id.datetime);
        orgcode=findViewById(R.id.orgcode);
        headerid.setText(RefId);
        datetime1.setText(DateTime);
        orgcode.setText(OrgCode);









    }

    public void SyncHeader(View view) {

        AlertDialog.Builder builder = new AlertDialog.Builder(SyncActivity.this);
        builder.setTitle("Confirm !!!!");
        builder.setMessage("ARE YOU SURE TO MAKE  SYNC");
        builder.setCancelable(false);
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                loginViewModel.getheaderservice();


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



    public void SyncLot(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(SyncActivity.this);
        builder.setTitle("Confirm !!!!");
        builder.setMessage("ARE YOU SURE TO MAKE  SYNC");
        builder.setCancelable(false);
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                loginViewModel.getfarmerservice();

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

    public void Syncpurchase(View view) {
        loginViewModel.getitemmaster();

        AlertDialog.Builder builder = new AlertDialog.Builder(SyncActivity.this);
        builder.setTitle("Confirm !!!!");
        builder.setMessage("ARE YOU SURE TO MAKE  SYNC");
        builder.setCancelable(false);
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {



                loginViewModel.getfarmerpurchaseservice();

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

    public void Syncweightment(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(SyncActivity.this);
        builder.setTitle("Confirm !!!!");
        builder.setMessage("ARE YOU SURE TO MAKE  SYNC");
        builder.setCancelable(false);
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {



                loginViewModel.getweighmentservice();

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

    public void Backsync(View view) {
        Intent intent=new Intent(this,LandingPage.class);
        startActivity(intent);
    }
}