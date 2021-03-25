package com.zebra.rfid.demo.sdksample;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

public class LandingPage extends AppCompatActivity {
ImageView btn_read;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing_page);
        btn_read=findViewById(R.id.btn_read);
    }

    public void btn_Read(View view) {

        YoYo.with(Techniques.FadeIn)
                .duration(1000)
                .repeat(0)
                .playOn(btn_read);

        Intent intent=new Intent(this,MainActivity.class);
        startActivity(intent);
    }

    public void btnWrite(View view) {
        Intent intent=new Intent(this,WriteTag.class);
        startActivity(intent);
    }
}