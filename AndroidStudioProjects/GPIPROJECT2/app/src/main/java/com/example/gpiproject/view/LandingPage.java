package com.example.gpiproject.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.gpiproject.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class LandingPage extends AppCompatActivity {
  String RefId;
  String OrgCode;
  String DateTime;
  TextView tv_org_code,et_ref_id,et_date;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing_page);

        tv_org_code=findViewById(R.id.tv_org_code2);
        et_ref_id=findViewById(R.id.et_ref_id2);
        et_date=findViewById(R.id.tv_date2);
        Intent intent = getIntent();
        RefId = intent.getStringExtra("RefId");
        OrgCode = intent.getStringExtra("Orgcode");
//

        Calendar calendar=Calendar.getInstance();
        SimpleDateFormat datetime= new SimpleDateFormat("DD-MM-YYYY");
        DateTime=datetime.format(calendar.getTime());
//

        tv_org_code.setText(OrgCode);
        et_ref_id.setText(RefId);
        et_date.setText(DateTime);


    }

    @Override
    protected void onPause() {
        super.onPause();
//
    }

    public void ppd(View view) {
        Intent intent = new Intent(this,FarmerPurchase.class);
        intent.putExtra("RefId",RefId);
        intent.putExtra("Orgcode",OrgCode);
        startActivity(intent);
    }
}