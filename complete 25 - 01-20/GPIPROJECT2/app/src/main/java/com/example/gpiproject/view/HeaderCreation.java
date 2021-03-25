package com.example.gpiproject.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.gpiproject.R;
import com.example.gpiproject.Viewmodel.HeaderViewModel;
import com.example.gpiproject.model.Crop;
import com.example.gpiproject.model.HeaderPost;
import com.example.gpiproject.model.HeaderPostCreation;
import com.example.gpiproject.model.Login;
import com.example.gpiproject.use.HeaderViewModelFactory;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class HeaderCreation extends AppCompatActivity implements AdapterView.OnItemSelectedListener{
TextView et_header_id,et_org_code,et_date,et_buyer_name;
     TextView et_variety,et_gon;
     Button btn_save;
    String DateTime;
    String DateTime1;
    String DateTime2;
    Spinner spinner;
    List<String> labels;
    String headerID;
    String Crop;
    String ByerCode;
    String label;
    String varity;
    String byername;
    SharedPreferences sp;


    HeaderViewModel headerViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_header_creation);

        spinner = findViewById(R.id.spinner);
        spinner.setOnItemSelectedListener(this);

        // Loading spinner data from database


        et_header_id=findViewById(R.id.et_header_id);
        et_org_code=findViewById(R.id.et_org_code);
        et_date=findViewById(R.id.et_date);
        et_buyer_name=findViewById(R.id.et_buyer_name);
        btn_save=findViewById(R.id.btn_save);
        et_variety=findViewById(R.id.et_variety);
        et_gon=findViewById(R.id.et_gon);

        headerViewModel= ViewModelProviders.of(HeaderCreation.this,new HeaderViewModelFactory(getApplication(),HeaderCreation.this)).get(HeaderViewModel.class);

        Calendar calendar=Calendar.getInstance();
        SimpleDateFormat datetime= new SimpleDateFormat("dd-mm-yyyy");
        SimpleDateFormat datetime1= new SimpleDateFormat("yyyyMM dd");
        SimpleDateFormat datetime2= new SimpleDateFormat("dd-MMMM-YY  HH:MM:SS a");


        DateTime=datetime.format(calendar.getTime());
        DateTime1=datetime1.format(calendar.getTime());
        DateTime2=datetime2.format(calendar.getTime());
        varity=et_variety.getText().toString().trim();

        sp=getApplicationContext().getSharedPreferences("data", Context.MODE_PRIVATE);
        Crop=sp.getString("Orgcode","");
        headerID=sp.getString("headerId","");
        ByerCode=sp.getString("Usercode","");




//        Intent intent = getIntent();
//         headerID = intent.getStringExtra("person");
//         Crop = intent.getStringExtra("crop");
//         ByerCode = intent.getStringExtra("username");



        et_header_id.setText(headerID);
        et_org_code.setText(Crop);
        et_date.setText(DateTime);

//        et_buyer_name.setText(val3);
            headerViewModel.getusername(ByerCode).observe(this, new Observer<Login>() {
                @Override
                public void onChanged(Login login) {
                 et_buyer_name.setText(login.getUserName());

                }
            });

            byername=et_buyer_name.getText().toString().trim();
            et_gon.setText(ByerCode);



        loadSpinnerData();
   //////////////////////////////////////
        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                HeaderPost headerPost= new HeaderPost();
                HeaderPostCreation creation=new HeaderPostCreation();
                List<HeaderPost> tableList=new ArrayList<>();


                headerPost.setHeaderID(headerID);
                headerPost.setOrganizationCode(Crop);
                headerPost.setBuyerCode(ByerCode);
                headerPost.setPurchaseDocumentNumber(DateTime1);
                headerPost.setPurchaseDate(DateTime2);
                headerPost.setAttribute("0");
                headerPost.setCrop(label);
                headerPost.setVariety(varity);
                headerPost.setCreatedBy(ByerCode);

                headerViewModel.insertheader(headerPost);
                tableList.add(headerPost);


                creation.setHeaderDetails(tableList);
                headerViewModel.headerpost(creation);
            }
        });


        /////////////////////////////////

    }
    private void loadSpinnerData() {

        //enable for data from sql lite
//        DatabaseHandler db = new DatabaseHandler(getApplicationContext());
//        List<String> labels = db.getAllLabels();

        //for testing dissabe below code when enable the top lines
//        labels.add("Crop1");
//        labels.add("Crop1");
//        labels.add("Crop1");
//        labels.add("Crop1");
//        labels.add("Crop1");


        labels = new ArrayList<>();
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, labels);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);


        headerViewModel.getcrop().observe(this, new Observer<List<Crop>>() {
            @Override
            public void onChanged(List<Crop> crops) {
                for(Crop i: crops){
                    Log.d("CROP", i.getCrop());
                    labels.add(i.getCrop());
                }
                adapter.notifyDataSetChanged();

            }
        });


    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        label = parent.getItemAtPosition(position).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public void back(View view) {
        Intent intent = new Intent(this,LandingPage.class);

        startActivity(intent);
    }
}