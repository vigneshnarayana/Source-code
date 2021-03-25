package com.example.newitcdemo;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.example.newitcdemo.Model.Product;
import com.example.newitcdemo.Model.ProductViewFactory;
import com.example.newitcdemo.ViewModel.ProductViewModel;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

public class Productivity extends AppCompatActivity {

    EditText et_partNumber;
    TextView tv_acceptCount,tv_rejectCount,tv_quantity;
    Button btn_acpt,btn_rej,btn_complete;
    int a = 0,b = 0;
    private ProductViewModel productViewModel;
    String DateTime;
    SharedPreferences sp;
    String CreatedBy;
    Button btn_post,btn_back_productivity,btn_clear_productivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_productivity);

        et_partNumber = findViewById(R.id.et_partNumber);
        tv_quantity = findViewById(R.id.tv_quantity);
        tv_acceptCount = findViewById(R.id.tv_acceptCount);
        tv_rejectCount = findViewById(R.id.tv_rejectCount);
        btn_acpt = findViewById(R.id.btn_acpt);
        btn_rej = findViewById(R.id.btn_rej);
        btn_complete = findViewById(R.id.btn_complete);
        btn_post = findViewById(R.id.btn_post);
        btn_back_productivity = findViewById(R.id.btn_back_productivity);
        btn_clear_productivity = findViewById(R.id.btn_clear_productivity);

        Calendar calendar=Calendar.getInstance();
        SimpleDateFormat datetime= new SimpleDateFormat("dd-MMMM-YY  HH:MM:SS a");
        DateTime=datetime.format(calendar.getTime());
        tv_acceptCount.setText("0");
        tv_rejectCount.setText("0");
        tv_quantity.setText("0");

        sp=getApplicationContext().getSharedPreferences("data", Context.MODE_PRIVATE);
        CreatedBy=sp.getString("CreatedBy","");

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
                }else{  a = a+1;
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
                            RejectQuantity,
                            AcceptQuantity,
                            CreatedBy,
                            DateTime

                    );
                    productViewModel.insertProduct(product);



                    et_partNumber.setText("");
                    a=0;
                    b=0;
                    tv_acceptCount.setText("0");
                    tv_rejectCount.setText("0");
                    tv_quantity.setText("0");

//                Toast.makeText(Productivity.this, "Success", Toast.LENGTH_SHORT).show();

                    Intent intent=new Intent(Productivity.this,Submited.class);
                    startActivity(intent);


                }



            }
        });





    }


    public void Post(View view) {
        YoYo.with(Techniques.FadeIn)
                .duration(1000)
                .repeat(0)
                .playOn(btn_post);
        try {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("        Confirm !!!");
            builder.setIcon(R.drawable.po);
            builder.setMessage("ARE YOU SURE TO MAKE FINAL SYNC");
            builder.setCancelable(false);
            builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

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


                }
            });

            builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Toast.makeText(getApplicationContext(), "SKIPPED SYNC", Toast.LENGTH_SHORT).show();
                }
            });

            builder.show();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void BackProductivity(View view) {
        YoYo.with(Techniques.FadeIn)
                .duration(1000)
                .repeat(0)
                .playOn(btn_back_productivity);
        Intent intent=new Intent(this,LandingPage.class);
        startActivity(intent);
    }

    public void btnClear(View view) {
        YoYo.with(Techniques.FadeIn)
                .duration(1000)
                .repeat(0)
                .playOn(btn_clear_productivity);
        et_partNumber.setText("");
        a=0;
        b=0;
        tv_acceptCount.setText("0");
        tv_rejectCount.setText("0");
        tv_quantity.setText("0");
    }
}