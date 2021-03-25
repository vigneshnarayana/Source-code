package com.example.tvs.activity.activitys;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.telephony.SmsManager;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.example.tvs.R;
import com.example.tvs.activity.JavaMailAPI;
import com.example.tvs.activity.Model.IntimationModel;
import com.example.tvs.activity.Model.Location;
import com.example.tvs.activity.Model.ProductViewFactory;
import com.example.tvs.activity.ViewModel.ProductViewModel;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class AlertActivity extends  AppCompatActivity{
   AutoCompleteTextView customerAutoTV;
   Button send1;
    Button send;
    String DateTime;
    String CreatedBy;
    String LocationId;
    SharedPreferences sp;

    private ProductViewModel productViewModel;
    Calendar calendar=Calendar.getInstance();
    SimpleDateFormat datetime= new SimpleDateFormat("dd-MMMM-YY  hh:mm:ss ");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alert);
        sp=getApplicationContext().getSharedPreferences("data", Context.MODE_PRIVATE);
        CreatedBy=sp.getString("Employeecode","");
        LocationId=sp.getString("LocationId","");

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Alert");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        initUI();

        send=findViewById(R.id.send1);
//        send1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(AlertActivity.this, customerAutoTV.getText(), Toast.LENGTH_SHORT).show();
//            }
//        });

        DateTime=datetime.format(calendar.getTime());

        productViewModel= ViewModelProviders.of(this,new ProductViewFactory(getApplication(),AlertActivity.this)).get(ProductViewModel.class);
/////////////////////////////////////////////////////

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                YoYo.with(Techniques.FadeOut)
                        .duration(1000)
                        .repeat(0)
                        .playOn(send);
                String messages= customerAutoTV.getText().toString().trim();

                if (TextUtils.isEmpty(messages)) {
                    Toast.makeText(getApplicationContext(), "Please Fill the Intimation Message ", Toast.LENGTH_LONG).show();
                }else{
                    IntimationModel intimationModel=new IntimationModel(
                            CreatedBy,
                            LocationId,
                             "",
                             "", DateTime,
                              messages
                    );
                    productViewModel.insertIntimation(intimationModel);

                    senEmail();


                    if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                        if(checkSelfPermission(Manifest.permission.SEND_SMS)  == PackageManager.PERMISSION_GRANTED){
                            SendSMS();
                        }else {
                            requestPermissions(new String[]{Manifest.permission.SEND_SMS},1);
                        }
                    }


                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            PostDataDatails();

                        }
                    }, 3000);
                }



            }

        });

        //////////////////////////////////////


    }


    ///////////////////////////////////////////

    private void  PostDataDatails(){
        try {

            List<IntimationModel> list = productViewModel.getIntimationList();

            System.out.println("************************* START");
            if (list != null)
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    list.forEach(System.out::println);
                }
            if (list != null)
                productViewModel.postIntimationService(list);

            System.out.println("************************* END");

            System.out.println("************************* LOT Creation retrive STARTS");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void senEmail() {

        String mSubject = "Intimation Subject";
        String mMessage =  customerAutoTV.getText().toString().trim()+" :\n Bay no: xxxx \n Cell Name : yyyy\n";
/////////////////////////////////////////////////////
        productViewModel.getLocationDatails(LocationId).observe(this, new Observer<List<Location>>() {
            @Override
            public void onChanged(List<Location> locations) {
                for(Location ii:locations){
                    String EmailIds=ii.getEmail().toString().trim();
                    JavaMailAPI javaMailAPI = new JavaMailAPI(AlertActivity.this, EmailIds, mSubject, mMessage);

                    javaMailAPI.execute();
                }
            }
        });
        ///////////////////////////////////////////////

//        JavaMailAPI javaMailAPI = new JavaMailAPI(this, EmailId, mSubject, mMessage);

//        javaMailAPI.execute();
        Toast.makeText(this, "Sended", Toast.LENGTH_SHORT).show();
    }


    private void SendSMS() {
        String messages= customerAutoTV.getText().toString().trim()+"\n Bay no: xxxx \n Cell Name : yyyy";

        ///////////////////////////////////////
        productViewModel.getLocationDatails(LocationId).observe(this, new Observer<List<Location>>() {
            @Override
            public void onChanged(List<Location> locations) {
                for(Location i:locations){
                    String PhoneNo=i.getMobileNumber().toString().trim();
                    try {
                        SmsManager smsManager=SmsManager.getDefault();
                        smsManager.sendTextMessage(PhoneNo,null,messages,null,null);


                    }catch (Exception e){}

                }
                Toast.makeText(AlertActivity.this, "Sended", Toast.LENGTH_SHORT).show();
            }
        });

        /////////////////////////////////////////////////////




    }

    ///////////////////////////////////
    private void initUI()
    {
        //UI reference of textView
      customerAutoTV = findViewById(R.id.customerTextView);

        // create list of customer
        ArrayList<String> customerList = getCustomerList();

        //Create adapter
        ArrayAdapter<String> adapter = new ArrayAdapter<>(AlertActivity.this, android.R.layout.simple_spinner_item, customerList);

        //Set adapter
        customerAutoTV.setAdapter(adapter);

        //submit button click event registration
//        findViewById(R.id.send1).setOnClickListener(new View.OnClickListener()
//        {
//            @Override
//            public void onClick(View view)
//            {
//                Toast.makeText(AlertActivity.this, customerAutoTV.getText(), Toast.LENGTH_SHORT).show();
//            }
//        });
    }

    private ArrayList<String> getCustomerList()
    {
        ArrayList<String> customers = new ArrayList<>();
        customers.add("Electrical Issue");
        customers.add("Repair");
        customers.add("Mechanical Issue");
        customers.add("Service Needed");
        customers.add("Unknown");
        return customers;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                super.onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}