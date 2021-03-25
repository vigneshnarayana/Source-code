package com.example.tvs.activity.activitys;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityOptionsCompat;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.tvs.R;
import com.example.tvs.activity.Home;

public class SplashActivity extends AppCompatActivity {

    /** Duration of wait **/
    private final int SPLASH_DISPLAY_LENGTH = 2000;

    ImageView logo;
           LinearLayout logoName;
    SharedPreferences pref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        initialize();

        //Animation
        Animation animation = AnimationUtils.loadAnimation(getApplication(), R.anim.anim);
        Animation animation2 = AnimationUtils.loadAnimation(getApplication(), R.anim.frombottom);
        animation.setInterpolator(new LinearInterpolator());
        animation.setRepeatCount(Animation.INFINITE);
        animation.setDuration(800);

        logo.startAnimation(animation);
        logoName.startAnimation(animation2);

        /* New Handler to start the Menu-Activity
         * and close this Splash-Screen after some seconds.*/
        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {



//                pref = getApplication().getSharedPreferences("MyAuthSharedPreference", Context.MODE_PRIVATE);
//                String uName = pref.getString("userName","");
//                String uPass = pref.getString("password","");
//
//                if (!uName.isEmpty() && !uPass.isEmpty()){
//                    /* Create an Intent that will start the Menu-Activity. */
//                    Intent mainIntent = new Intent(SplashActivity.this, MainActivity.class);
//                    Bundle bundle = ActivityOptionsCompat.makeCustomAnimation(getApplicationContext(),
//                            android.R.anim.fade_in, android.R.anim.fade_out).toBundle();
//                    SplashActivity.this.startActivity(mainIntent,bundle);
//                    SplashActivity.this.finish();
//
//                }else if(uName.isEmpty()&&uPass.isEmpty()){
//                    Intent mainIntent = new Intent(SplashActivity.this,Login.class);
//                    Bundle bundle = ActivityOptionsCompat.makeCustomAnimation(getApplicationContext(),
//                            android.R.anim.fade_in, android.R.anim.fade_out).toBundle();
//                    SplashActivity.this.startActivity(mainIntent,bundle);
//
//                    SplashActivity.this.finish();
//                }


                //tempCheck
                Intent mainIntent = new Intent(SplashActivity.this, LoginActivity.class);
                    Bundle bundle = ActivityOptionsCompat.makeCustomAnimation(getApplicationContext(),
                            android.R.anim.fade_in, android.R.anim.fade_out).toBundle();
                    SplashActivity.this.startActivity(mainIntent,bundle);
                    SplashActivity.this.finish();

            }
        }, SPLASH_DISPLAY_LENGTH);
    }

    public void initialize(){
        logo = findViewById(R.id.img);
        logoName = findViewById(R.id.logoName);
    }
}