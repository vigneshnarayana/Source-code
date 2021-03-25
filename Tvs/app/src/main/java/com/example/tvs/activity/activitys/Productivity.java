package com.example.tvs.activity.activitys;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.ui.AppBarConfiguration;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.example.tvs.R;
import com.example.tvs.activity.Model.Product;
import com.example.tvs.activity.Model.ProductViewFactory;
import com.example.tvs.activity.ViewModel.ProductViewModel;
import com.example.tvs.activity.ui.home.HomeFragment;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

public class Productivity extends AppCompatActivity {
    EditText et_partNumber;
    EditText tv_acceptCount,tv_rejectCount,tv_quantity;
    Button btn_acpt,btn_rej,btn_complete;
    int a = 0,b = 0;
    private ProductViewModel productViewModel;
    String DateTime;
    SharedPreferences sp;
    String CreatedBy;
    String LocationId;
    Button btn_post,btn_back_productivity,btn_clear_productivity;

    private AppBarConfiguration mAppBarConfiguration;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_productivity);

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Productivity");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        et_partNumber = findViewById(R.id.et_partNumber);
        tv_quantity = findViewById(R.id.tv_quantity);
        tv_acceptCount = findViewById(R.id.tv_acceptCount);
        tv_rejectCount = findViewById(R.id.tv_rejectCount);
        btn_acpt = findViewById(R.id.btn_acpt);
        btn_rej = findViewById(R.id.btn_rej);
        btn_complete = findViewById(R.id.btn_complete);


        Calendar calendar=Calendar.getInstance();
        SimpleDateFormat datetime= new SimpleDateFormat("dd-MMMM-YY  HH:MM:SS a");
        DateTime=datetime.format(calendar.getTime());
        tv_acceptCount.setText("0");
        tv_rejectCount.setText("0");
        tv_quantity.setText("0");

        sp=getApplicationContext().getSharedPreferences("data", Context.MODE_PRIVATE);
        CreatedBy=sp.getString("Employeecode","");
        LocationId=sp.getString("LocationId","");

        productViewModel= ViewModelProviders.of(Productivity.this,new ProductViewFactory(getApplication(),Productivity.this)).get(ProductViewModel.class);
        btn_acpt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                YoYo.with(Techniques.FadeIn)
                        .duration(1000)
                        .repeat(0)
                        .playOn(btn_acpt);
                String PartNumber=et_partNumber.getText().toString().trim();

                if (TextUtils.isEmpty(PartNumber)) {
                    Toast.makeText(getApplicationContext(), " Please Fill PartNumber ", Toast.LENGTH_LONG).show();
                }else{
                    a= Integer.parseInt(tv_acceptCount.getText().toString());
                    a = a+1;
                    tv_acceptCount.setText(String.valueOf(a));
                    tv_quantity.setText(String.valueOf(a+b));}

            }
        });

        btn_rej.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                YoYo.with(Techniques.FadeIn)
                        .duration(1000)
                        .repeat(0)
                        .playOn(btn_rej);
                String PartNumber=et_partNumber.getText().toString().trim();

                if (TextUtils.isEmpty(PartNumber)) {
                    Toast.makeText(getApplicationContext(), " Please Fill PartNumber ", Toast.LENGTH_LONG).show();
                }else{
                    b= Integer.parseInt(tv_rejectCount.getText().toString());
                    b = b+1;
                    tv_rejectCount.setText(String.valueOf(b));
                    tv_quantity.setText(String.valueOf(a+b));
                }

            }
        });

        btn_complete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                YoYo.with(Techniques.FadeIn)
                        .duration(1000)
                        .repeat(0)
                        .playOn(btn_complete);


                String PartNumber=et_partNumber.getText().toString().trim();
                String Quantity=tv_quantity.getText().toString().trim();
                String RejectQuantity=tv_rejectCount.getText().toString().trim();
                String AcceptQuantity=tv_acceptCount.getText().toString().trim();

                if (TextUtils.isEmpty(PartNumber)) {
                    Toast.makeText(getApplicationContext(), " Please Fill PartNumber ", Toast.LENGTH_LONG).show();
                }else{

                    Product product= new Product(
                            PartNumber,
                            Quantity,
                            AcceptQuantity,
                            RejectQuantity,
                            CreatedBy,
                            DateTime,
                            LocationId

                    );
                    productViewModel.insertProduct(product);



                    et_partNumber.setText("");
                    a=0;
                    b=0;
                    tv_acceptCount.setText("0");
                    tv_rejectCount.setText("0");
                    tv_quantity.setText("0");

                Toast.makeText(Productivity.this, "Success", Toast.LENGTH_SHORT).show();

//                    Intent intent=new Intent(Productivity.this,Submited.class);
//                    startActivity(intent);


                }

                ////////////////////////////////////////

                List<Product> list = productViewModel.getproductList();

                System.out.println("************************* START");
                if (list != null)
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                        list.forEach(System.out::println);
                    }
                if (list != null)
                    productViewModel.postProductDetails(list);

                System.out.println("************************* END");

                System.out.println("************************* LOT Creation retrive STARTS");

                //////////////////////////////////



            }
        });


    }
    public void Post(View view) {
        YoYo.with(Techniques.FadeIn)
                .duration(1000)
                .repeat(0)
                .playOn(btn_post);


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