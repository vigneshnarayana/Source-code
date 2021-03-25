package com.example.itc;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Movement extends AppCompatActivity implements AdapterView.OnItemSelectedListener{
    private String[] Names = {"Production", "MSI", "MARKETING"};
    private String[] Names2 = {"Production", "MSI", "MARKETING"};


    FirebaseDatabase database;
    String v1,v2,v3;
    Spinner et_from,et_to;
    EditText et_assert;
    String val1;
    String val2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movement);

        adapter();

        et_assert=findViewById(R.id.et_assert);
        et_from=findViewById(R.id.et_from);
        et_to=findViewById(R.id.et_to);



    }

    public void move(View view) {
        v1=et_assert.getText().toString().trim();
        v2=et_from.toString().trim();
        v3=et_to.toString().trim();

        v2 = val1;
        v3 = val2;


//        try {
            if(v1 != null|| v2 !=null || v3 !=null){

                database = FirebaseDatabase.getInstance();
                DatabaseReference myRef = database.getReference("Mapping");
                myRef.child(v2).child(v1).removeValue();
                myRef.child(v3).child(v1).setValue(v1);
                Toast.makeText(this, "Asset Moved to :"+v3, Toast.LENGTH_LONG).show();
                et_assert.setText("");



            }else if(v1 == null|| v2 ==null || v3 ==null){
                Toast.makeText(this, " Invalid Data", Toast.LENGTH_LONG).show();
                et_assert.setText("");
            }

//        }catch (Exception e){
//            Toast.makeText(this, "Empty Value", Toast.LENGTH_SHORT).show();
//        }


    }

    private void adapter() {


        //Getting the instance of Spinner and applying OnItemSelectedListener on it
        Spinner spin = (Spinner) findViewById(R.id.et_from);
        Spinner spin2 = (Spinner) findViewById(R.id.et_to);
        spin.setOnItemSelectedListener((AdapterView.OnItemSelectedListener) this);
        spin2.setOnItemSelectedListener((AdapterView.OnItemSelectedListener) this);

//Creating the ArrayAdapter instance having the bank name list
        ArrayAdapter aa = new ArrayAdapter(this, android.R.layout.simple_spinner_item, Names);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ArrayAdapter bb = new ArrayAdapter(this, android.R.layout.simple_spinner_item, Names2);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//Setting the ArrayAdapter data on the Spinner
        spin.setAdapter(aa);
        spin2.setAdapter(bb);
    }


    //PERFORMING ACTION ONITEMSELECTED AND ONNOTHING SELECTED
    @Override
    public void onItemSelected(AdapterView<?> arg0, View arg1, int position, long id) {
        Spinner spin = (Spinner)arg0;
        Spinner spin2 = (Spinner)arg0;

        if(spin.getId() == R.id.et_from)
        {
            val1=Names[position];

        }
        else if(spin2.getId() == R.id.et_to)
        {
            val2=Names2[position];

        }


//        Toast.makeText(getApplicationContext(), Names[position], Toast.LENGTH_SHORT).show();
//        scanner_ET.setOnFocusChangeListener((View.OnFocusChangeListener) this);
    }

    @Override
    public void onNothingSelected(AdapterView<?> arg0) {
// TODO Auto-generated method stub

    }
}