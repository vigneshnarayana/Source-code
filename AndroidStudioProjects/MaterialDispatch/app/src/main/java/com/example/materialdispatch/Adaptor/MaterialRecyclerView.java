package com.example.materialdispatch.Adaptor;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.example.materialdispatch.MainActivity;
import com.example.materialdispatch.Model.Material;
import com.example.materialdispatch.R;
import com.example.materialdispatch.ViewModel.MaterialViewModel;

import java.util.ArrayList;
import java.util.List;

public class MaterialRecyclerView extends AppCompatActivity implements AdapterView.OnItemSelectedListener{
    private MaterialViewModel materialViewModel;
    Spinner SpinnerSearchable;
    String label;
    Button Search_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_material_recycler_view);
        Search_btn=findViewById(R.id.Search_btn);
        SpinnerSearchable=findViewById(R.id.SpinnerSearchable);
        SpinnerSearchable.setOnItemSelectedListener(this);




        materialViewModel= ViewModelProviders.of(this).get(MaterialViewModel.class);

        RecyclerView recyclerView=findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        loadSpinnerData();

        MaterialAdaptor materialAdaptor=new MaterialAdaptor();
        recyclerView.setAdapter(materialAdaptor);



        materialViewModel.getMaterialData().observe(this, new Observer<List<Material>>() {
            @Override
            public void onChanged(List<Material> materials) {
               materialAdaptor.SetNotes(materials);
            }
        });
        Search_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              materialViewModel.getMaterialDataSa(label).observe(MaterialRecyclerView.this, new Observer<List<Material>>() {
                  @Override
                  public void onChanged(List<Material> materials) {
                      materialAdaptor.SetNotes(materials);
                  }
              });
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
        SpinnerSearchable.setAdapter(dataAdapter);
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


    public void back(View view) {
        Intent intent=new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}