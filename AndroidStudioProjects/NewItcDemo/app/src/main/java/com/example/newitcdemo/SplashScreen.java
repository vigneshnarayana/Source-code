package com.example.newitcdemo;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Pair;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class SplashScreen extends AppCompatActivity {

    private static int SPLASH_SCREEN = 5000;

    //Variables
    Animation topanim,bottomanim;
    ImageView splash_img,splash_text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.screen_splash);

        //Animation
        topanim= AnimationUtils.loadAnimation(this,R.anim.top_animation);
        bottomanim= AnimationUtils.loadAnimation(this,R.anim.bottom_animation);

        //Hooks
        splash_img=findViewById(R.id.splash_img);
        splash_text=findViewById(R.id.splash_text);

        splash_img.setAnimation(topanim);
        splash_text.setAnimation(bottomanim);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent=new Intent(SplashScreen.this,UserLogin.class);

                Pair[] pairs = new Pair[2];
                pairs[0] = new Pair<View,String>(splash_img, "logo_image");
                pairs[1] = new Pair<View,String>(splash_text, "logo_text");

                ActivityOptions options= null;
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
                    options = ActivityOptions.makeSceneTransitionAnimation(SplashScreen.this,pairs);
                }
                startActivity(intent,options.toBundle());


            }
        },SPLASH_SCREEN);

    }
}