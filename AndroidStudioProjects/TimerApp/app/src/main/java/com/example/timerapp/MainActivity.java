package com.example.timerapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {
TextView txt_View;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txt_View=findViewById(R.id.txt_View);

        long duration= TimeUnit.MINUTES.toMillis(1);
        new CountDownTimer(duration, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                String Sduration=String.format(Locale.ENGLISH,"%02d: %02d"
                        ,TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished)
                        ,TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished)-TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished))  );
                txt_View.setText(Sduration);

            }

            @Override
            public void onFinish() {
                txt_View.setVisibility(View.GONE);
                Toast.makeText(MainActivity.this, "Ended", Toast.LENGTH_SHORT).show();

            }
        }.start();
    }

    public void Timer(View view) {
        Intent intent=new Intent(this,UpCounter.class);
        startActivity(intent);
    }
}