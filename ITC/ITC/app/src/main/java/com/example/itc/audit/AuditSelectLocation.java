package com.example.itc.audit;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.itc.R;
import com.example.itc.mapping.Home;

public class AuditSelectLocation extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private String[] Names = {"Production", "MSI", "MARKETING"};
    Spinner et_loc;
    String val1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_audit_select_location);

        et_loc = findViewById(R.id.au_location);
        adapter();



    }

    public void add(View view)
    {
        String loc=val1;


        Intent intent = new Intent(getApplicationContext(), Audit.class);
        intent.putExtra("locatio",loc);
        startActivity(intent);
    }

    private void adapter() {


        //Getting the instance of Spinner and applying OnItemSelectedListener on it
        Spinner spin = (Spinner) findViewById(R.id.au_location);
        spin.setOnItemSelectedListener((AdapterView.OnItemSelectedListener) this);

//Creating the ArrayAdapter instance having the bank name list
        ArrayAdapter aa = new ArrayAdapter(this, android.R.layout.simple_spinner_item, Names);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//Setting the ArrayAdapter data on the Spinner
        spin.setAdapter(aa);
    }


    //PERFORMING ACTION ONITEMSELECTED AND ONNOTHING SELECTED
    @Override
    public void onItemSelected(AdapterView<?> arg0, View arg1, int position, long id) {
        val1 = Names[position];
//        Toast.makeText(getApplicationContext(), Names[position], Toast.LENGTH_LONG).show();
//        scanner_ET.setOnFocusChangeListener((View.OnFocusChangeListener) this);
    }

    @Override
    public void onNothingSelected(AdapterView<?> arg0) {
// TODO Auto-generated method stub

    }
}