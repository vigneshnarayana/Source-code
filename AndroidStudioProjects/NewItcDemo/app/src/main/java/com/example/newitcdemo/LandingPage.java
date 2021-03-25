package com.example.newitcdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

public class LandingPage extends AppCompatActivity {
ImageView product,breakdown,intimation;
Button btn_back_land;
ImageView img_logo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing_page);
        product=findViewById(R.id.img_pro);
        breakdown=findViewById(R.id.img_bre);
        intimation=findViewById(R.id.img_int);
        btn_back_land=findViewById(R.id.btn_back_land);
        img_logo=findViewById(R.id.img_logo);
    }

    public void Productivity(View view) {
        YoYo.with(Techniques.FadeIn)
                .duration(1000)
                .repeat(0)
                .playOn(product);

        ////////////////////
        Intent intent= new Intent(LandingPage.this,Productivity.class);

        Pair[] pairs= new Pair[1];
        pairs[0] = new Pair<View,String>(img_logo,"logo_image");

        ActivityOptions options = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            options = ActivityOptions.makeSceneTransitionAnimation(LandingPage.this,pairs);
        }
        startActivity(intent,options.toBundle());

        /////////////////
//        Intent intent= new Intent(this,Productivity.class);
//        startActivity(intent);
    }

    public void BreakDown(View view) {
        YoYo.with(Techniques.FadeIn)
                .duration(1000)
                .repeat(0)
                .playOn(breakdown);
        Intent intent= new Intent(LandingPage.this,BreakDown.class);

        Pair[] pairs= new Pair[1];
        pairs[0] = new Pair<View,String>(img_logo,"logo_image");

        ActivityOptions options = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            options = ActivityOptions.makeSceneTransitionAnimation(LandingPage.this,pairs);
        }
        startActivity(intent,options.toBundle());
    }

    public void Intimationbtn(View view) {
        YoYo.with(Techniques.FadeIn)
                .duration(1000)
                .repeat(0)
                .playOn(intimation);
        Intent intent= new Intent(LandingPage.this,Intimation.class);
        Pair[] pairs= new Pair[1];
        pairs[0] = new Pair<View,String>(img_logo,"logo_image");

        ActivityOptions options = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            options = ActivityOptions.makeSceneTransitionAnimation(LandingPage.this,pairs);
        }
        startActivity(intent,options.toBundle());
    }

    public void Back_btn(View view) {
        YoYo.with(Techniques.FadeIn)
                .duration(1000)
                .repeat(0)
                .playOn(btn_back_land);
        Intent intent= new Intent(this,UserLogin.class);
        startActivity(intent);
    }
}