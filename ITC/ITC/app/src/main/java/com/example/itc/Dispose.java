package com.example.itc;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Dispose extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private String[] Names = {"Production", "MSI", "MARKETING"};
    String val1;
    EditText dis_ass, dis_ass1;
    Spinner dis_loc;
    TextView txt;
    String v1, v2, v3;
    FirebaseDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dispose);

        adapter();

        dis_ass = findViewById(R.id.dis_ass);
        dis_ass1 = findViewById(R.id.dis_ass1);
        dis_loc = findViewById(R.id.dis_loc);
    }

    public void Dispose(View view) {
        v1 = dis_ass.getText().toString().trim();
        v2 = dis_ass1.getText().toString().trim();
        v3 = dis_loc.toString().trim();
        v3 = val1;

        try {
            if (v1.equals(v2)) {


                database = FirebaseDatabase.getInstance();
                DatabaseReference myRef = database.getReference("Mapping");
                myRef.child(v3).child(v1).removeValue();

                //   txt.setText("Disposed");
                Toast.makeText(this, "Disposed", Toast.LENGTH_LONG).show();
                DatabaseReference myRef1 = database.getReference("Dispose");
                myRef1.child(v3).child(v1).setValue(v1);
                dis_ass.setText("");
                dis_ass1.setText("");

            } else {
                Toast.makeText(this, "Not Disposed", Toast.LENGTH_SHORT).show();
                dis_ass.setText("");
                dis_ass1.setText("");

            }

        }catch (Exception e){

        }



    }

    private void adapter() {


        //Getting the instance of Spinner and applying OnItemSelectedListener on it
        Spinner spin = (Spinner) findViewById(R.id.dis_loc);
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