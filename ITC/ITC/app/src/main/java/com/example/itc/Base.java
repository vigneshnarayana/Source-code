package com.example.itc;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.example.itc.audit.AuditSelectLocation;
import com.example.itc.mapping.MappingSelectLocation;
import com.example.itc.report.report;

public class Base extends AppCompatActivity {
    TextView assetMapping,audit,assetMovement,assetDispose,Report;
    Button Exit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_base);


        assetMapping = findViewById(R.id.a1);
        audit = findViewById(R.id.a2);
        assetMovement = findViewById(R.id.a3);
        assetDispose = findViewById(R.id.a4);
        Report = findViewById(R.id.a5);
        Exit = findViewById(R.id.a6);


        assetMapping.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Base.this, MappingSelectLocation.class);
                startActivity(intent);
            }
        });


        /////////////////
        audit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Base.this, AuditSelectLocation.class);
                startActivity(intent);
            }
        });
        ////////////////////
        assetMovement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Base.this, Movement.class);
                startActivity(intent);
            }
        });

        /////////////////////

        assetDispose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Base.this, Dispose.class);
                startActivity(intent);
            }
        });

           Report.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            Intent intent = new Intent(Base.this, report.class);
            startActivity(intent);
        }
    });

           /////
        Exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                Intent intent = new Intent(Base.this, Login.class);
//                startActivity(intent);
                finishAffinity();
            }
        });

    }
}