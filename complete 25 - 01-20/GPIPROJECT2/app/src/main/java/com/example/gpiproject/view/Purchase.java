package com.example.gpiproject.view;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gpiproject.R;
import com.example.gpiproject.Viewmodel.PurchaseViewModel;
import com.example.gpiproject.model.FarmerPurchaseModel;
import com.example.gpiproject.model.Header;
import com.example.gpiproject.model.ItemMaster;
import com.example.gpiproject.model.PurchaseModel;
import com.example.gpiproject.use.PurchaseViewFactory;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

public class Purchase extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    String RefId,OrgCode;
    TextView et_crop,et_variety;
    TextView et_org_code,et_date,et_header_id;
    String DateTime;
    SharedPreferences sp;
    List<String> labels;
    List<String> labels1;
    String rejected;
    List<PurchaseModel> purchaseList;
    Button btn_save;
    EditText remark;
    EditText et_bale_number,et_rate;
  private   PurchaseViewModel purchaseViewModel;
  Spinner spinner;
    String label;
    String label1;
    Spinner spinner_class_grade,spinner_rejection;
    String flag = "1";
    Integer totalLot = 0, totalPurchase = 0;
    TextView et_purchase,et_rejected,et_remaining,et_bale_total;


    List<FarmerPurchaseModel> lotCreations;

    String rejectTypeValues[] = {"--- PLS SELECT ---", "RR", "CR"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_purchase);

        sp=getApplicationContext().getSharedPreferences("data", Context.MODE_PRIVATE);
        OrgCode=sp.getString("Orgcode","");
        RefId=sp.getString("headerId","");





        Calendar calendar=Calendar.getInstance();
        SimpleDateFormat datetime= new SimpleDateFormat("dd-MM-YYYY");
        DateTime=datetime.format(calendar.getTime());


        et_header_id=findViewById(R.id.et_header_id);
        et_date=findViewById(R.id.et_date);
        et_org_code=findViewById(R.id.et_org_code);
        et_variety=findViewById(R.id.et_variety);
        et_crop=findViewById(R.id.et_crop);
        btn_save=findViewById(R.id.btn_save);
        et_bale_number=findViewById(R.id.et_bale_number);
        et_rate=findViewById(R.id.et_rate);
        et_bale_total=findViewById(R.id.et_bale_total);
        et_remaining=findViewById(R.id.et_remaining);
        et_rejected=findViewById(R.id.et_rejected);
        et_purchase=findViewById(R.id.et_purchase);
        remark=findViewById(R.id.remark);

        spinner=findViewById(R.id.spinner_buyer_grade);
        spinner_class_grade=findViewById(R.id.spinner_class_grade);
        spinner_rejection=findViewById(R.id.spinner_rejection);
        spinner_rejection.setOnItemSelectedListener(this);
        spinner.setOnItemSelectedListener(this);
        spinner_class_grade.setOnItemSelectedListener(this);



        et_header_id.setText(RefId);
        et_org_code.setText(OrgCode);
        et_date.setText(DateTime);

        purchaseViewModel= ViewModelProviders.of(Purchase.this,new PurchaseViewFactory(getApplication(),Purchase.this)).get(PurchaseViewModel.class);


        lotCreations=purchaseViewModel.getLotCreations();


        if (et_header_id != null  && lotCreations != null && lotCreations.size() != 0) {

            totalLot=lotCreations.size();

//            try{
//                if(lotCreations.size()<0){
//                    activityPurchaseBinding.totalValue.setText("NO BALE TO PYURCHASE");
//                }
//                else{
//                    activityPurchaseBinding.totalValue.setText(totalLot.toString());
//                }
//            }catch (Exception e){
//
//            }

            et_bale_number.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                @Override
                public void onFocusChange(View v, boolean hasFocus) {
                    if(et_bale_number.getText().toString().isEmpty()){
                        Toast.makeText(Purchase.this, "Please Fill Bale Number", Toast.LENGTH_SHORT).show();
                    }else{
                        doProcessCode();

                    }
                }
            });
        }



        purchaseViewModel.getheadercropverity(RefId).observe(this, new Observer<Header>() {
            @Override
            public void onChanged(Header header) {
                et_crop.setText(header.getCrop());
                et_variety.setText(header.getVariety());

            }
        });


//        btn_save.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                purchaseViewModel.getitemmasterbyergrade().observe(Purchase.this, new Observer<List<ItemMaster>>() {
//                    @Override
//                    public void onChanged(List<ItemMaster> itemMasters) {
//                        for(ItemMaster i: itemMasters){
//                            Log.d("itembyer", i.getItemCode());
//
//                        }
//
//                    }
//                });
//
//            }
//        });
        labels1 = new ArrayList<>();

        labels = new ArrayList<>();


        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, labels);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ArrayAdapter<String> adapter1 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, labels1);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ArrayAdapter aa2 = new ArrayAdapter(this, android.R.layout.simple_spinner_item, rejectTypeValues);
        aa2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Setting the ArrayAdapter data on the Spinner
        spinner_rejection.setAdapter(aa2);


        spinner.setAdapter(adapter);
        spinner_class_grade.setAdapter(adapter1);



        purchaseViewModel.getitemmasterclassificationgrade().observe(Purchase.this, new Observer<List<ItemMaster>>() {
            @Override
            public void onChanged(List<ItemMaster> itemMasters) {
                for(ItemMaster i: itemMasters){
                    Log.d("itembyer", i.getItemCode());
                    labels1.add(i.getItemCodeGrp());
                }
                adapter1.notifyDataSetChanged();

            }
        });

        purchaseViewModel.getitemmasterbyergrade().observe(this, new Observer<List<ItemMaster>>() {
            @Override
            public void onChanged(List<ItemMaster> itemMasters) {
                for(ItemMaster i: itemMasters){
                    Log.d("itembyer", i.getItemCode());
                    labels.add(i.getItemCode());
                }
                adapter.notifyDataSetChanged();

            }
        });




        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //////////////////////////////////
                try {
//                    spin2.setSelection(0);
//                    actv.setText("");
//                    class_grade_actv.setText("");
                    if (flag.equals("1")) {
                        List<PurchaseModel> purchases1 = purchaseViewModel.getPurchaseList();
                        List<FarmerPurchaseModel> lotCreationList = purchaseViewModel.getLotCreations();
                        String baleV = et_bale_number.getText().toString().trim();

                        Boolean result = true;
                        Boolean lotResult = false;
                        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
                            result = purchases1
                                    .stream()
                                    .filter(s -> s.getBaleNumber().equals(baleV))
                                    .findFirst()
                                    .isPresent();

                            /*
                             *  This validation already done ... This is duplicate .. Remove After Analyse
                             * */

                            lotResult = lotCreationList
                                    .stream()
                                    .filter(s -> s.getBaleNumber().equals(baleV))
                                    .findFirst()
                                    .isPresent();

                        }

                        if (result == false && lotResult == true) {
                            String headerV = et_header_id.getText().toString().trim();
                            String cropV = et_crop.getText().toString().trim();
                            String varietyV = et_variety.getText().toString().trim();
                            String bgV = label;
                            String cgV = label1;
                            ////////
                            String rejectedTypeV = "NO DATA";
                            String rejectedValue="";
                            ///////////

                            if (rejected.equals("--- PLS SELECT ---")) {
                                rejectedValue = "OK";
                                rejectedTypeV = "NRJ";
                            } else if (rejected.equals("RR")) {
                                rejectedValue = "RJ";
                                rejectedTypeV = "RR";
                            } else if (rejected.equals("CR")) {
                                rejectedValue = "RJ";
                                rejectedTypeV = "CR";
                            }

                            String rateV = et_rate.getText().toString().trim();
                            String remarkV=remark.getText().toString().trim();
//                            String remarkV="";
//                            String remarkV ="checking....";

                            if (headerV != null && baleV != null && cropV != null && varietyV != null && bgV != null && cgV != null && rejectedTypeV != null && rateV != null && remarkV != null) {
                                PurchaseModel purchase = new PurchaseModel();
                                purchase.setHeaderID(headerV);
                                purchase.setBaleNumber(baleV);
                                purchase.setBuyerGrade(bgV);
                                purchase.setClassGrade(cgV);
                                purchase.setRejectedStatus(rejectedValue);
                                purchase.setRejectedType(rejectedTypeV);
                                purchase.setRate(rateV);
                                purchase.setRemark(remarkV);

                                purchaseViewModel.insertPurchase(purchase);
//                                List<Purchase> purchases = Arrays.asList(purchase);
//
//                                purchaseViewModel.insertPurchasePost(purchases);


                                purchaseList = purchaseViewModel.getPurchaseList();
                                if (null != purchaseList)
                                    totalPurchase = purchaseList.size();

                                Integer rejectedCount = purchaseViewModel.getRejectedCount();
                                Integer puchasedCount = totalPurchase - rejectedCount;
                                Integer remainingCount = totalLot - totalPurchase;

                                et_purchase.setText(puchasedCount.toString());
                                et_remaining.setText(remainingCount.toString());
                                et_rejected.setText(rejectedCount.toString());
                                et_bale_total.setText(totalLot.toString());

                                et_bale_number.setText("");
                                remark.setText("");
//                                activityPurchaseBinding.remark.setText("");
                                et_rate.setText("");
                                et_bale_number.requestFocus();
                            } else {
                                Toast.makeText(getApplicationContext(), "PLS FILL ALL FORM", Toast.LENGTH_LONG).show();
                            }
                        } else {
                            if (result == false)
                                Toast.makeText(getApplicationContext(), "BALE ALREAD PRESENT IN PURCHASE", Toast.LENGTH_LONG).show();
                            if (lotResult == true)
                                Toast.makeText(getApplicationContext(), "BALE ALREAD PRESENT IN PURCHASE", Toast.LENGTH_LONG).show();
                        }
                    } else {
                        Toast.makeText(getApplicationContext(), "PLS ENTER VALID BALE NUMBER", Toast.LENGTH_LONG).show();
                       et_bale_number.setText("");
                        et_rate.setText("");
//                        activityPurchaseBinding.remark.setText("");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                ////////////////////////////

            }
        });


    }
    void doProcessCode() {
        try {
            String baleValueValid =et_bale_number .getText().toString().trim();
            if (lotCreations != null && lotCreations.size() != 0) {
                Boolean result = false;
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N)
                    result = lotCreations
                            .stream()
                            .filter(fm -> fm.getBaleNumber().equals(baleValueValid))
                            .findFirst()
                            .isPresent();
                flag = "1";

                if (result != true) {
                    Toast.makeText(getApplicationContext(), "Sorry", Toast.LENGTH_LONG).show();
                    flag = "0";

                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> arg0, View arg1, int position, long id) {
//        label = parent.getItemAtPosition(position).toString();
         spinner=(Spinner)arg0;
         spinner_class_grade=(Spinner)arg0;
         if(spinner.getId() ==R.id.spinner_buyer_grade){
             label = arg0.getItemAtPosition(position).toString();
         }else  if(spinner_class_grade.getId()== R.id.spinner_class_grade){
             label1 = arg0.getItemAtPosition(position).toString();
         }else  if(spinner_rejection.getId() ==R.id.spinner_rejection){
             rejected = rejectTypeValues[position];

         }


    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    public void clear(View view) {
        et_bale_number.setText("");
        et_rate.setText("");
        remark.setText("");
        et_bale_number.requestFocus();
    }

    public void backbtn(View view) {

        Intent intent=new Intent(this,FarmerPurchase.class);
        startActivity(intent);
    }

    public void Postpurchase(View view) {
        try {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Confirm !!!");
            builder.setMessage("ARE YOU SURE TO MAKE FINAL SYNC");
            builder.setCancelable(false);
            builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                    List<PurchaseModel> list = purchaseViewModel.getPurchaseList();

                    System.out.println("************************* START");
                    if (list != null)
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                            list.forEach(System.out::println);
                        }
                    if (list != null)
                        purchaseViewModel.insertPurchasePostSync(list);

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
}