package com.example.gpiproject.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gpiproject.R;
import com.example.gpiproject.Viewmodel.LotCreationViewModel;
import com.example.gpiproject.model.FarmerMasterGet;
import com.example.gpiproject.model.LotCreationModel;
import com.example.gpiproject.use.LotCreationViewFactory;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class LotCreation extends AppCompatActivity {
String RefId,OrgCode;
String DateTime;
TextView et_total_bales,et_bale;
EditText et_farmer_code;
    TextView et_father_name;
    TextView et_farmer_name;
    TextView et_village_code;
    String headerId;
    String flag = "1";
    Button btn_save;


    List<FarmerMasterGet> farmerMasters;
    List<LotCreationModel> lotCreations;
    List<Integer> baleSeriesValue;

TextView et_header_id3,et_org_code3,et_date3,et_lot_number,et_bale_number;
LotCreationViewModel lotCreationViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lot_creation);
         lotCreationViewModel= ViewModelProviders.of(LotCreation.this, new LotCreationViewFactory(getApplication(),LotCreation.this)).get(LotCreationViewModel.class);

        et_header_id3=findViewById(R.id.et_header_id3);
        et_org_code3=findViewById(R.id.et_org_code3);
        et_date3=findViewById(R.id.et_date3);
        et_farmer_code=findViewById(R.id.et_farmer_code);
        et_father_name=findViewById(R.id.et_father_name);
        et_farmer_name=findViewById(R.id.et_farmer_name);
        et_village_code=findViewById(R.id.et_village_code);
        et_bale_number=findViewById(R.id.et_bale_number);
        et_lot_number=findViewById(R.id.et_lot_number);
        btn_save=findViewById(R.id.btn_save);
        et_total_bales=findViewById(R.id.et_total_bales);
        et_bale=findViewById(R.id.et_totel_values);

/////////////////////////////////////////////////////////////////



        ////////////////////////
        Calendar calendar=Calendar.getInstance();
        SimpleDateFormat datetime= new SimpleDateFormat("DD-MM-YYYY");
        DateTime=datetime.format(calendar.getTime());

        Intent intent = getIntent();
        RefId = intent.getStringExtra("RefId");
        OrgCode = intent.getStringExtra("Orgcode");
        et_farmer_code.requestFocus();

//        et_farmer_code.setOnFocusChangeListener(new View.OnFocusChangeListener() {
//            @Override
//            public void onFocusChange(View v, boolean hasFocus) {
//                if (!hasFocus) {
//                    doProcessCode();
//                    et_bale_number.requestFocus();
//                }
//            }
//        });




        et_farmer_code.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                try {
                    if ((event.getAction() == KeyEvent.ACTION_DOWN) &&
                            (keyCode == KeyEvent.KEYCODE_ENTER)) {
//
                        String farmerCode=et_farmer_code.getText().toString().trim();

                        lotCreationViewModel.getformerdetails(farmerCode).observe(LotCreation.this, new Observer<FarmerMasterGet>() {
                            @Override
                            public void onChanged(FarmerMasterGet farmerMasterGet) {

                                try {
                                    //activityLotBinding.sync.setVisibility(View.INVISIBLE);

                                    FarmerMasterGet farmerMaster ;

                                    Boolean result = false;
                                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N)
                                        result = farmerMasters
                                                .stream()
                                                .filter(fm -> fm.getFarmerCode().equals(farmerCode))
                                                .findFirst()
                                                .isPresent();

                                    if (result == false) {
                                        Toast.makeText(getApplicationContext(), "INVALID FARMER CODE", Toast.LENGTH_LONG).show();
                                        flag = "0";
                                        et_farmer_name.setText("");
                                        et_lot_number.setText("");
                                        et_bale_number.setText("");
                                    } else {
                                        flag = "1";
                                        if (null != farmerMasters && farmerMasters.size() != 0) {
                                            if (null != farmerCode) {
                                                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                                                    Optional<FarmerMasterGet> matchingObject = farmerMasters
                                                            .stream()
                                                            .filter(fm -> fm.getFarmerCode().equals(farmerCode))
                                                            .findFirst();
                                                    farmerMaster = matchingObject.get();


                                                        lotCreations = lotCreationViewModel.getLotCreations(headerId);
                                                        if (null == lotCreations || lotCreations.size() == 0) {
                                                            et_farmer_name.setText(farmerMasterGet.getFarmerName());
                                                            et_father_name.setText(farmerMasterGet.getFarM_FATHER_NAME());
                                                            et_village_code.setText(farmerMasterGet.getVillageCode());
                                                            et_lot_number.setText("1");
                                                            et_total_bales.setText("0");


                                                        if (lotCreations != null && lotCreations.size() != 0) {
                                                            List<LotCreationModel> ls = lotCreations
                                                                    .stream()
                                                                    .filter(fm -> fm.getFarmerCode().equals(farmerCode))
                                                                    .collect(Collectors.toList());

                                                            if (null != ls && ls.size() != 0) {
                                                                ls.forEach(System.out::println);
                                                                //System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>><<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
                                                                et_farmer_name.setText(ls.get(ls.size() - 1).getFarmerName());
                                                                et_lot_number.setText(ls.get(ls.size() - 1).getLotNumber().toString());
                                                                et_father_name.setText(ls.get(ls.size() - 1).getFarmerFatherName());
                                                                et_village_code.setText(ls.get(ls.size() - 1).getVillageCode());
                                                                Integer i = ls.size();
                                                                et_total_bales.setText(i.toString());
                                                            } else {

                                                                et_farmer_name.setText(farmerMaster.getFarmerName());
                                                                List<LotCreationModel> list = lotCreations.stream()
                                                                        .filter(distinctByKey(p -> p.getFarmerCode()))
                                                                        .collect(Collectors.toList());
                                                                list.forEach(System.out::println);
                                                                System.out.println(list.size());
                                                                Integer i = list.size() + 1;
                                                                //System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>><<<<<<<<<<<<<<<<<<<<<<<<<<<<< 555555555555");
                                                                System.out.println(i);
                                                                et_lot_number.setText(i.toString());
                                                                et_father_name.setText(farmerMaster.getFarM_FATHER_NAME());
                                                                et_village_code.setText(farmerMaster.getVillageCode());
                                                                et_total_bales.setText("0");
                                                            }
                                                        }
                                                    }
                                                } else {
                                                    et_farmer_name.setText("Not Available");
                                                    et_lot_number.setText("Not Available");
                                                }
                                            }
                                        }
                                    }
                                }catch (Exception e){
                                    e.printStackTrace();
                                }



                            }
                        });
//                        doProcessCode();
                        et_bale_number.requestFocus();



                    }

                }catch (Exception e){

                }
//
                return false;
            }
        });


/////////////////////////////////////////////////////////////////////////
        et_bale_number.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // String strname = edSearch.getText().toString().toLowerCase();

            }

            @Override
            public void afterTextChanged(Editable s) {
                doProcessSeries();
            }
        });


       /// //////////////////////////////////////////////////////////
 btn_save.setOnClickListener(new View.OnClickListener() {
     @Override
     public void onClick(View v) {
         try {
             if (flag.equals("0")) {
                 Toast.makeText(getApplicationContext(), "INVALID FARMER CODE", Toast.LENGTH_LONG).show();
                 et_farmer_name.setText("");
                 et_lot_number.setText("");
                 et_total_bales.setText("");
                 et_father_name.setText("");
                 et_village_code.setText("");
             } else {
                 List<LotCreationModel> lotCreationsValid = lotCreationViewModel.getAllLotCreation();
                 String headerId = et_header_id3.getText().toString().trim();
                 String farmerCode = et_farmer_code.getText().toString().trim();
                 String farmerName = et_farmer_name.getText().toString().trim();
                 String lotNumber = et_lot_number.getText().toString().trim();
                 String farmerFather = et_father_name.getText().toString().trim();
                 String village = et_village_code.getText().toString().trim();
                 String baleNumber = et_bale_number.getText().toString().trim();
                 String series = et_total_bales.getText().toString().trim();
                 Boolean result = true;
                 Boolean seriesvalid = true;
                 if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {

                     result = lotCreationsValid
                             .stream()
                             .filter(s -> s.getBaleNumber().equals(baleNumber))
                             .findFirst()
                             .isPresent();
                     seriesvalid = lotCreationsValid
                             .stream()
                             .filter(s -> s.getLotNumber() == Integer.parseInt(lotNumber) && s.getSeries() == Integer.parseInt(series) && s.getHeaderId().equals(headerId))
                             .findFirst()
                             .isPresent();

                 }
                 if (result == false && seriesvalid == false) {
                     LotCreationModel lotCreation = new LotCreationModel();
                     lotCreation.setHeaderId(headerId);
                     lotCreation.setFarmerCode(farmerCode);
                     lotCreation.setFarmerName(farmerName);
                     lotCreation.setLotNumber(Integer.parseInt(lotNumber));
                     lotCreation.setBaleNumber(baleNumber);
                     lotCreation.setSeries(Integer.parseInt(series));
                     java.text.SimpleDateFormat format = new java.text.SimpleDateFormat("yyyy-MM-dd");
                     lotCreation.setCreatedDate(format.format(new java.util.Date()));
                     lotCreation.setCreatedBy(farmerCode);
                     lotCreation.setFarmerFatherName(farmerFather);
                     lotCreation.setVillageCode(village);
                     lotCreation.setModifiedBy("");
                     lotCreation.setModifiedDate("");
                     lotCreationViewModel.insertLotCreation(lotCreation);
                    et_bale_number.setText("");

                     List<LotCreationModel> lotCreations = Arrays.asList(lotCreation);
//                  /////////////////////***************88888888888888********////////////
//                     lotCreationViewModel.insertLotCreationPost(lotCreations);
                     Toast.makeText(getApplicationContext(), "SAVED SUCCESSFULLY", Toast.LENGTH_LONG).show();
                     // activityLotBinding.sync.setVisibility(View.VISIBLE);
                     et_total_bales.setText("");

                     try{
                         if(lotCreationsValid!=null)
                             if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
                                 List<LotCreationModel> creationList1 =  lotCreationViewModel.getLotCreations(headerId);
                                 List<LotCreationModel> list = creationList1.stream()
                                         .filter(distinctByKey(p -> p.getLotNumber()))
                                         .collect(Collectors.toList());



                                 Integer totalLot = list.size();
                                 Integer totalBales = creationList1.size();

                                 //activityLotBinding.totalsValues.setText(totalLot+"  /  "+totalBales);

                                 List<LotCreationModel> c =creationList1.stream().filter(s->s.getFarmerCode().equals(farmerCode)).collect(Collectors.toList());
                                 if(c!=null)
                                     et_bale.setText(totalLot+" / "+totalBales+"   TOTAL BALES : "+c.size());
                             }
                     }
                     catch (Exception x){
                         System.out.println(">>>>>>>>>>>>>>> Some Error with code inside lots/bales");
                     }
                 } else {
                     if (result == true)
                         Toast.makeText(getApplicationContext(), "BALE NUMBER ALREAD PRESENT FAIL ", Toast.LENGTH_SHORT).show();
                     if (seriesvalid == true)
                         Toast.makeText(getApplicationContext(), "BALE NUMBER SERIES ALREAD PRESENT", Toast.LENGTH_LONG).show();
                 }
             }
         }catch (Exception e){
             e.printStackTrace();
         }
     }
 });
//////////////////////////////////////////////////////////
    }

    private void doProcessCode() {

        try {
            //activityLotBinding.sync.setVisibility(View.INVISIBLE);
            String farmerCode = et_farmer_code.getText().toString().trim();
            FarmerMasterGet farmerMaster = null;

            Boolean result = false;
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N)
                result = farmerMasters
                        .stream()
                        .filter(fm -> fm.getFarmerCode().equals(farmerCode))
                        .findFirst()
                        .isPresent();

            if (result == false) {
                Toast.makeText(getApplicationContext(), "INVALID FARMER CODE", Toast.LENGTH_LONG).show();
                flag = "0";
               et_farmer_name.setText("");
               et_lot_number.setText("");
                et_bale_number.setText("");
            } else {
                flag = "1";
                if (null != farmerMasters && farmerMasters.size() != 0) {
                    if (null != farmerCode) {
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                            Optional<FarmerMasterGet> matchingObject = farmerMasters
                                    .stream()
                                    .filter(fm -> fm.getFarmerCode().equals(farmerCode))
                                    .findFirst();
                            farmerMaster = matchingObject.get();

                            if (null != farmerMaster) {
                                lotCreations = lotCreationViewModel.getLotCreations(headerId);
                                if (null == lotCreations || lotCreations.size() == 0) {
                                    et_farmer_name.setText(farmerMaster.getFarmerName());
                                   et_father_name.setText(farmerMaster.getFarM_FATHER_NAME());
                                    et_village_code.setText(farmerMaster.getVillageCode());
                                    et_lot_number.setText("1");
                                    et_total_bales.setText("0");
                                }

                                if (lotCreations != null && lotCreations.size() != 0) {
                                    List<LotCreationModel> ls = lotCreations
                                            .stream()
                                            .filter(fm -> fm.getFarmerCode().equals(farmerCode))
                                            .collect(Collectors.toList());

                                    if (null != ls && ls.size() != 0) {
                                        ls.forEach(System.out::println);
                                        //System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>><<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
                                        et_farmer_name.setText(ls.get(ls.size() - 1).getFarmerName());
                                        et_lot_number.setText(ls.get(ls.size() - 1).getLotNumber().toString());
                                        et_father_name.setText(ls.get(ls.size() - 1).getFarmerFatherName());
                                        et_village_code.setText(ls.get(ls.size() - 1).getVillageCode());
                                        Integer i = ls.size();
                                        et_total_bales.setText(i.toString());
                                    } else {

                                        et_farmer_name.setText(farmerMaster.getFarmerName());
                                        List<LotCreationModel> list = lotCreations.stream()
                                                .filter(distinctByKey(p -> p.getFarmerCode()))
                                                .collect(Collectors.toList());
                                        list.forEach(System.out::println);
                                        System.out.println(list.size());
                                        Integer i = list.size() + 1;
                                        //System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>><<<<<<<<<<<<<<<<<<<<<<<<<<<<< 555555555555");
                                        System.out.println(i);
                                        et_lot_number.setText(i.toString());
                                        et_father_name.setText(farmerMaster.getFarM_FATHER_NAME());
                                        et_village_code.setText(farmerMaster.getVillageCode());
                                        et_total_bales.setText("0");
                                    }
                                }
                            }
                        } else {
                            et_farmer_name.setText("Not Available");
                            et_lot_number.setText("Not Available");
                        }
                    }
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    private void doProcessSeries() {
        try {
            //activityLotBinding.sync.setVisibility(View.INVISIBLE);
            if (flag.equals("0")) {
                Toast.makeText(getApplicationContext(), " INVALID FARMER CODE ", Toast.LENGTH_LONG).show();
            } else {
                try {
                    String lotNumber = et_lot_number.getText().toString();
                    List<String> valueSet = new ArrayList<>();
                    valueSet.add(lotNumber);
                    valueSet.add(headerId);

                    baleSeriesValue = lotCreationViewModel.getBaleSeries(valueSet);
                    Integer baleSeriesValueCount = 0;
                    String strBaleSeries = et_bale_number.getText().toString();
                    if (baleSeriesValue.size() != 0 && strBaleSeries != null) {

                        baleSeriesValueCount = baleSeriesValue.get(baleSeriesValue.size() - 1);
                        baleSeriesValueCount += 1;
                        et_total_bales.setText(baleSeriesValueCount.toString());


                    }
                    if (baleSeriesValue.size() == 0 && strBaleSeries != null) {

                        String strPresentSeries =et_total_bales.getText().toString();
                        Integer presentSeries = Integer.parseInt(strPresentSeries);
                        if (presentSeries == 0)
                            presentSeries += 1;
                        et_total_bales.setText(presentSeries.toString());

                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }



        }catch (Exception e){
            e.printStackTrace();
        }
    }


    @Override
    protected void onResume() {
        super.onResume();
        et_header_id3.setText(RefId);
        et_org_code3.setText(OrgCode);
        et_date3.setText(DateTime);
    }

    public void back(View view) {
        Intent intent = new Intent(this,FarmerPurchase.class);
        intent.putExtra("RefId",RefId);
        intent.putExtra("Orgcode",OrgCode);
        startActivity(intent);
    }

    public void btn(View view) {


    }
    public static <T> Predicate<T> distinctByKey(Function<? super T, Object> keyExtractor) {
        Map<Object, Boolean> map = new ConcurrentHashMap<>();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            return t -> map.putIfAbsent(keyExtractor.apply(t), Boolean.TRUE) == null;
        }
        return null;
    }
}