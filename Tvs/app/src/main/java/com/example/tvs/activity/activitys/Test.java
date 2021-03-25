package com.example.tvs.activity.activitys;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import com.example.tvs.R;

public class Test extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
    }

    public void OpenApp(View view) {
        Intent launchintent=getPackageManager().getLaunchIntentForPackage("com.zebra.rfid.demo.sdksample");
        if(launchintent !=null){
            startActivity(launchintent);
        }else {
            Toast.makeText(this, "This is no Package", Toast.LENGTH_SHORT).show();
        }
    }

}