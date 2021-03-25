package com.example.materialdispatch;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.materialdispatch.Adaptor.MaterialRecyclerView;
import com.example.materialdispatch.Model.Material;
import com.example.materialdispatch.ViewModel.MaterialViewModel;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class MainActivity extends AppCompatActivity  implements AdapterView.OnItemSelectedListener{
    Spinner sp_customer;
    String label;
    EditText et_tray,et_material,et_quantity;
    Button btn_submit,btn_cancel;
    String DateTime;
    private MaterialViewModel materialViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Calendar calendar=Calendar.getInstance();
        SimpleDateFormat datetime= new SimpleDateFormat("dd-MM-YYYY hh:mm a");
        DateTime=datetime.format(calendar.getTime());

        et_tray=findViewById(R.id.et_tray);
        et_material=findViewById(R.id.et_material);
        et_quantity=findViewById(R.id.et_quantity);
        btn_submit=findViewById(R.id.btn_submit);
        btn_cancel=findViewById(R.id.btn_cancel);

        sp_customer=findViewById(R.id.sp_customer);
        sp_customer.setOnItemSelectedListener(this);

        loadSpinnerData();

        materialViewModel= ViewModelProviders.of(this).get(MaterialViewModel.class);

        et_tray.requestFocus();

        et_tray.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if ((event.getAction() == KeyEvent.ACTION_DOWN) &&
                        (keyCode == KeyEvent.KEYCODE_ENTER)) {
                    et_material.requestFocus();
                    return true;
                }

                return false;
            }
        });

        et_material.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if ((event.getAction() == KeyEvent.ACTION_DOWN) &&
                        (keyCode == KeyEvent.KEYCODE_ENTER)) {
                    et_quantity.requestFocus();
                    return true;
                }

                return false;
            }
        });

        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String TrayNumber=et_tray.getText().toString().trim();
                String Material=et_material.getText().toString().trim();
                String Quantity=et_quantity.getText().toString().trim();

                if (TextUtils.isEmpty(TrayNumber)) {
                    Toast.makeText(getApplicationContext(), "FILL TrayNumber ", Toast.LENGTH_LONG).show();
                } else if (TextUtils.isEmpty(Material)) {
                    Toast.makeText(getApplicationContext(), "FILL Material", Toast.LENGTH_LONG).show();
                } else if (TextUtils.isEmpty(Quantity)) {
                    Toast.makeText(getApplicationContext(), "FILL Quantity", Toast.LENGTH_LONG).show();
                }else {

                if( TrayNumber !=null && Material!=null && Quantity !=null){
                    Material material=new Material();
                    material.setCustomerName(label);
                    material.setTrayNumber(TrayNumber);
                    material.setMaterial(Material);
                    material.setQuantity(Quantity);
                    material.setDateAndTime(DateTime);
                    materialViewModel.insertMaterial(material);
                    Toast.makeText(MainActivity.this, "Saved", Toast.LENGTH_SHORT).show();

                    et_tray.setText("");
                    et_quantity.setText("");
                    et_material.setText("");
                    et_tray.requestFocus();
                }
                else {
                    Toast.makeText(MainActivity.this, "Data is Inavalid", Toast.LENGTH_SHORT).show();
                }
                }




            }
        });






    }

    private void loadSpinnerData() {
        List<String> labels = new ArrayList<String>();
        labels.add("CUST1");
        labels.add("CUST2");
        labels.add("CUST3");
        labels.add("CUST4");
        labels.add("CUST5");
        labels.add("CUST6");
        labels.add("CUST7");
        labels.add("CUST8");

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, labels);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp_customer.setAdapter(dataAdapter);
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
         label = parent.getItemAtPosition(position).toString();

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    public void ListView(View view) {
        Intent intent=new Intent(this, MaterialRecyclerView.class);
        startActivity(intent);
    }

    public void Cencel(View view) {
        et_tray.setText("");
        et_material.setText("");
        et_quantity.setText("");
        et_tray.requestFocus();
    }
}