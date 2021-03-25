package com.example.messageapp;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import static android.Manifest.permission_group.SMS;

public class MainActivity extends AppCompatActivity {
    EditText number,message;
    Button send;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        number=findViewById(R.id.phonenumber);
        message=findViewById(R.id.message);
        send=findViewById(R.id.send);

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

             if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                 if(checkSelfPermission(Manifest.permission.SEND_SMS)  == PackageManager.PERMISSION_GRANTED){
                     SendSMS();
                 }else {
                     requestPermissions(new String[]{Manifest.permission.SEND_SMS},1);
                 }
             }

            }

        });
    }

    private void SendSMS() {
        String numbers=number.getText().toString().trim();
        String messages=message.getText().toString().trim();

        try {
            SmsManager smsManager=SmsManager.getDefault();
            smsManager.sendTextMessage(numbers,null,messages,null,null);
            
            Toast.makeText(this, "Sended", Toast.LENGTH_SHORT).show();

        }catch (Exception e){}


    }


}