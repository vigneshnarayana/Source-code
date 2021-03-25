package com.example.gpiproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;

import com.example.gpiproject.Viewmodel.LoginViewModel;
import com.example.gpiproject.view.LandingPage;
import com.example.gpiproject.view.UserLogin;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_main);



        Intent myIntent = new Intent(MainActivity.this, UserLogin.class);
        MainActivity.this.startActivity(myIntent);
        finish();

    }
}