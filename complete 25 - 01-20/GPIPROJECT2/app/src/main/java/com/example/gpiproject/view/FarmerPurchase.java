package com.example.gpiproject.view;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.gpiproject.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class FarmerPurchase extends AppCompatActivity {
    String RefId,OrgCode;
    TextView tv_org_code,et_ref_id,tv_date;
    String DateTime;
    String code;
    SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_farmer_purchase);


        tv_org_code=findViewById(R.id.tv_org_code1);
        et_ref_id=findViewById(R.id.et_ref_id1);
        tv_date=findViewById(R.id.tv_date1);


        Calendar calendar=Calendar.getInstance();
        SimpleDateFormat datetime= new SimpleDateFormat("dd-MM-YYYY");
        DateTime=datetime.format(calendar.getTime());

        sp=getApplicationContext().getSharedPreferences("data", Context.MODE_PRIVATE);
        OrgCode=sp.getString("Orgcode","");
        RefId=sp.getString("headerId","");
        code=sp.getString("Usercode","");


        tv_org_code.setText(OrgCode);
        et_ref_id.setText(RefId);
        tv_date.setText(DateTime);

    }

    public void purchase(View view) {

        Intent intent = new Intent(this,Purchase.class);
        startActivity(intent);
    }

    public void weighment(View view) {

        Intent intent = new Intent(this,Weightment.class);
        startActivity(intent);
    }

    public void lotCreation(View view) {

        Intent intent = new Intent(this,LotCreation.class);

        startActivity(intent);
    }

    public void Back_btn(View view) {
        Intent intent = new Intent(this,LandingPage.class);

        startActivity(intent);
    }

}
