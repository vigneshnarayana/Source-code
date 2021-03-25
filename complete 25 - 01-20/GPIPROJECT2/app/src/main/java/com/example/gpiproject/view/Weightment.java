package com.example.gpiproject.view;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gpiproject.R;
import com.example.gpiproject.Viewmodel.WeighmentViewModel;
import com.example.gpiproject.model.LotCreationModel;
import com.example.gpiproject.model.Weighment;
import com.example.gpiproject.model.WeighmentGet;
import com.example.gpiproject.use.WeighmentViewFactory;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class Weightment extends AppCompatActivity {
    SharedPreferences sp;
    String OrgCode;
    String HeaderId;
    String DateTime;
    TextView et_org_code,et_date,et_header_id;
    EditText et_balenumber,et_weight;
    List<LotCreationModel> lotCreations;
    String userCode;
    List<WeighmentGet> purchaseList;

    private WeighmentViewModel weighmentViewModel;
    Integer totalPurchase, rejectedBales, purchasedBales, weightedBales, remainingBales;
    TextView et_weighmentbales,et_purchasebales,et_remainingbales,et_rejectedbales,et_total_bales;
    String flag = "1";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weightment);

        sp=getApplicationContext().getSharedPreferences("data", Context.MODE_PRIVATE);
        OrgCode=sp.getString("Orgcode","");
        HeaderId=sp.getString("headerId","");
        userCode=sp.getString("Usercode","");
        Calendar calendar=Calendar.getInstance();
        SimpleDateFormat datetime= new SimpleDateFormat("dd-MM-YYYY");
        DateTime=datetime.format(calendar.getTime());

        et_header_id=findViewById(R.id.et_header_id);
        et_date=findViewById(R.id.et_date);
        et_org_code=findViewById(R.id.et_org_code);
        et_balenumber=findViewById(R.id.et_balenumber);
        et_weight=findViewById(R.id.et_weight);
        et_weighmentbales=findViewById(R.id.et_weighmentbales);
        et_purchasebales=findViewById(R.id.et_purchasebales);
        et_remainingbales=findViewById(R.id.et_remainingbales);
        et_rejectedbales=findViewById(R.id.et_rejectedbales);
        et_total_bales=findViewById(R.id.et_total_bales);

        et_org_code.setText(OrgCode);
        et_header_id.setText(HeaderId);
        et_date.setText(DateTime);
        weighmentViewModel= ViewModelProviders.of(this,new WeighmentViewFactory(getApplication(),Weightment.this)).get(WeighmentViewModel.class);

        if (lotCreations != null  && purchaseList != null) {

            totalPurchase = weighmentViewModel.getPurchaseList().size();

            et_balenumber.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                @Override
                public void onFocusChange(View v, boolean hasFocus) {
                    doProcessCode();
//                et_weight.requestFocus();
                }
            });

        }

    }

    public void doProcessCode() {
        try {
            String baleValueValid = et_balenumber.getText().toString().trim();
            List<WeighmentGet> purchases1 = weighmentViewModel.getPurchaseList();
            if (purchases1 != null) {
                Boolean result = false;
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N)
                    result = purchases1
                            .stream()
                            .filter(fm -> fm.getBaleNumber().equals(baleValueValid))
                            .findFirst()
                            .isPresent();
                flag = "1";
                System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>   1" + result);
                if (result != true) {
                    Toast.makeText(getApplicationContext(), "INVALID BALES", Toast.LENGTH_LONG).show();
                    flag = "0";
                    System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>  2" + result);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void weighbtn(View view) {

        try {

            if (flag.equals("1")) {
                List<WeighmentGet> purchases1 = weighmentViewModel.getPurchaseList();
                List<Weighment> list = weighmentViewModel.getWeighmentList();
                String baleNumber = et_balenumber.getText().toString();

                Boolean result = true;
                Boolean wresult = false;
                if (list != null && purchases1 != null) {
                    if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
                        result = list
                                .stream()
                                .filter(s -> s.getBaleNumber().equals(baleNumber))
                                .findFirst()
                                .isPresent();


                        /*
                         *  This validation already done ... This is duplicate .. Remove After Analyse
                         * */
                        wresult = purchases1
                                .stream()
                                .filter(fm -> fm.getBaleNumber().equals(baleNumber) && fm.getRejectStatus().equals("OK"))
                                .findFirst()
                                .isPresent();
                    }
                }

                String weight = et_weight.getText().toString().trim();
                String headerId = et_header_id.getText().toString().trim();
                if (result == false && wresult == true) {
                    if (headerId != null && baleNumber != null && weight != null) {
                        Weighment weighment = new Weighment();
                        weighment.setHeaderId(headerId);
                        weighment.setBaleNumber(baleNumber);
                        weighment.setNetWeight(weight);
                        weighment.setCreatedBy(userCode);
                        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                        String createdDate = format.format(new java.util.Date());
                        weighment.setCreatedDate(createdDate);

                        Optional<WeighmentGet> matchingObject = null;
                        String rateValues = "000";
                        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
                            //purchases1.forEach(System.out::println);
                            matchingObject = purchases1
                                    .stream()
                                    .filter(fm -> fm.getBaleNumber().equals(baleNumber))
                                    .findFirst();
                            WeighmentGet purchaseRate = matchingObject.get();
                            rateValues = purchaseRate.getRate();
                        }

//                        weighment.setRate(rateValues);
                        weighmentViewModel.insertWeighment(weighment);

//                        List<Weighment> weighmentList = Arrays.asList(weighment);
//                        weighmentViewModel.sendWeightPost(weighmentList);

                        Toast.makeText(getApplicationContext(), "WEIGHMENT INSERTED", Toast.LENGTH_LONG).show();
                     et_balenumber.setText("");
                       et_weight.setText("");
                        et_balenumber.requestFocus();
                        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
                            totalPurchase = purchases1.size();
                            rejectedBales = purchases1
                                    .stream()
                                    .filter(fm -> fm.getRejectStatus().equals("RJ")).collect(Collectors.toList()).size();
                            purchasedBales = purchases1
                                    .stream()
                                    .filter(fm -> fm.getRejectStatus().equals("OK")).collect(Collectors.toList()).size();
                            weightedBales = weighmentViewModel.getWeighmentList().size();

                            remainingBales = purchasedBales - weightedBales;

                            et_weighmentbales.setText(weightedBales.toString());
                            et_purchasebales.setText(purchasedBales.toString());
                            et_remainingbales.setText(remainingBales.toString());
                            et_rejectedbales.setText(rejectedBales.toString());
                           et_total_bales.setText(totalPurchase.toString());

                            /*if (purchasedBales == weightedBales) {
                                activityWeightmentBinding.sync.setVisibility(View.VISIBLE);
                            }*/
                        }

                    } else {
                        Toast.makeText(getApplicationContext(), "SORRY!!! NOT INSERTED", Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "WEIGHMENT ALREADY INSERTED", Toast.LENGTH_LONG).show();
                }
            } else {
                Toast.makeText(getApplicationContext(), "INVALID BALES", Toast.LENGTH_LONG).show();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void Completeweighment(View view) {

        try {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Confirm !!!");
            builder.setMessage("ARE YOU SURE TO MAKE FINAL SYNC");
            builder.setCancelable(false);
            builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                    List<Weighment> list = weighmentViewModel.getWeighmentList();
                    if (list != null) {

                        System.out.println("************************* Weighment START");
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                            list.forEach(System.out::println);
                            weighmentViewModel.sendWeightPostList(list);
                            System.out.println("*************************Weighment END");
                        }

                    }

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