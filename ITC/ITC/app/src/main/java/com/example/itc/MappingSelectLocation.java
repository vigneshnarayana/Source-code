package com.example.itc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class MappingSelectLocation extends AppCompatActivity {

    EditText et_loc;
    String loc;
   // private Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_mapping_select_location);



        et_loc = findViewById(R.id.et_loc);
        Intent intent = new Intent(getApplicationContext(), adapter.class);
        intent.putExtra("locations",loc);
        startActivity(intent);


    }

    public void addNew(View view)
    {
        loc=et_loc.getText().toString().trim();


        Intent intent = new Intent(getApplicationContext(), Home.class);
        intent.putExtra("location",loc);
        startActivity(intent);
    }


}