package com.example.timerapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.Chronometer;

public class UpCounter extends AppCompatActivity {
     Chronometer chronometer;
     private long pauseoffset;
    private boolean runing;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_up_counter);

        chronometer=findViewById(R.id.chronometeraction);
    }

    public void startChronomer(View view) {
        if(!runing){
            chronometer.setBase(SystemClock.elapsedRealtime()-pauseoffset);
            chronometer.start();
            runing=true;
        }
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