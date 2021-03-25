package com.example.gpiproject.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.gpiproject.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class FarmerPurchase extends AppCompatActivity {
String RefId,OrgCode;
TextView tv_org_code,et_ref_id,tv_date;
String DateTime;
String code;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_farmer_purchase);


        tv_org_code=findViewById(R.id.tv_org_code1);
        et_ref_id=findViewById(R.id.et_ref_id1);
        tv_date=findViewById(R.id.tv_date1);


        Calendar calendar=Calendar.getInstance();
        SimpleDateFormat datetime= new SimpleDateFormat("DD-MM-YYYY");
        DateTime=datetime.format(calendar.getTime());

        Intent intent = getIntent();
        RefId = intent.getStringExtra("RefId");
        OrgCode = intent.getStringExtra("Orgcode");
        code = intent.getStringExtra("code");

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
        intent.putExtra("RefId",RefId);
        intent.putExtra("code",code);
        intent.putExtra("Orgcode",OrgCode);
        startActivity(intent);
    }
}