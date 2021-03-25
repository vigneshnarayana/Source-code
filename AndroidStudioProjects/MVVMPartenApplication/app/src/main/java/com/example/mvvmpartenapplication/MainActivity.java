package com.example.mvvmpartenapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.os.Vibrator;
import android.view.View;
import android.widget.Button;

import com.example.mvvmpartenapplication.ViewModel.EmloyeeViewModel;

public class MainActivity extends AppCompatActivity {
    private EmloyeeViewModel emloyeeViewModel;
    Button btn_vibration;
    Vibrator vibrator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        emloyeeViewModel= ViewModelProviders.of(this).get(EmloyeeViewModel.class);
//        emloyeeViewModel.getemployeeservicedetails();

        btn_vibration=findViewById(R.id.btn_vibration);
        vibrator=(Vibrator) getSystemService(VIBRATOR_SERVICE);

        btn_vibration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vibrator.vibrate(1000);
            }
        });
    }
}