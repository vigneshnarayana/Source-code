package com.example.gpiproject.view;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gpiproject.Adaptor.LotAdaptor;
import com.example.gpiproject.Adaptor.LotRecyclerView;
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
import java.util.Observable;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class LotCreation extends AppCompatActivity {
String RefId,OrgCode;
String DateTime;
TextView TOTBALES;

    String flag = "1";
    Button btn_save;
    List<FarmerMasterGet> farmerMasters;
    List<LotCreationModel> lotCreations;
    List<Integer> baleSeriesValue;
    List<LotCreationModel> c;
    SharedPreferences pref;
    String userCode, orgnTypeValue, empType, crop, variety, baleStart;



    String headerId;
    String code;
    int a;

TextView et_org_code3,et_date3;
LotCreationViewModel lotCreationViewModel;
    Integer totalLot,totalBales;
   TextView seriesvalue;
   SharedPreferences sp;

EditText code_value,bale_number;
TextView  header_value,fathers_name,farmer_name,village_name,lot_value,totals_values;
Button btn_view;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lot_creation);

//

        lotCreationViewModel= ViewModelProviders.of(LotCreation.this, new LotCreationViewFactory(getApplication(),LotCreation.this)).get(LotCreationViewModel.class);

        header_value=findViewById(R.id.et_header_id3);
        et_org_code3=findViewById(R.id.et_org_code3);
        et_date3=findViewById(R.id.et_date3);
        code_value=findViewById(R.id.et_farmer_code);
        fathers_name=findViewById(R.id.et_father_name);
        farmer_name=findViewById(R.id.et_farmer_name);
        village_name=findViewById(R.id.et_village_code);
        bale_number=findViewById(R.id.et_bale_number);
        lot_value=findViewById(R.id.et_lot_number);
        btn_save=findViewById(R.id.btn_save);
        totals_values=findViewById(R.id.balesinlot);
        seriesvalue=findViewById(R.id.Totelbales);
        TOTBALES=findViewById(R.id.TOTBALES);
        btn_view=findViewById(R.id.btn_view);

        farmerMasters = lotCreationViewModel.getFarmerMasters();


/////////////////////////////////////////////////////////////////



        ////////////////////////
        Calendar calendar=Calendar.getInstance();
        SimpleDateFormat datetime= new SimpleDateFormat("dd-MM-YYYY");
        DateTime=datetime.format(calendar.getTime());

        sp=getApplicationContext().getSharedPreferences("data", Context.MODE_PRIVATE);
        OrgCode=sp.getString("Orgcode","");
        RefId=sp.getString("headerId","");
        code=sp.getString("Usercode","");

        code_value.requestFocus();
        code_value.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if ((event.getAction() == KeyEvent.ACTION_DOWN) &&
                        (keyCode == KeyEvent.KEYCODE_ENTER)) {
                    doProcessCode();

//                    bale_number.requestFocus();
                   bale_number.requestFocus();
                    return true;
                }

                return false;
            }
        });

        ///////////////////////////////

        lotCreationViewModel.getlotlivedata().observe(LotCreation.this, new Observer<List<LotCreationModel>>() {
            @Override
            public void onChanged(List<LotCreationModel> lotCreationModels) {



                btn_view.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        StringBuffer buffer = new StringBuffer();

                        for(LotCreationModel values:lotCreationModels){
                            buffer.append("BaleNumber :"+ values.getBaleNumber()+"\n");
                            buffer.append("farmercode :"+ values.getFarmerCode()+"\n");
                            buffer.append("lotnumber :"+ values.getLotNumber()+"\n");
                            buffer.append("headerid :"+ values.getHeaderId()+"\n");
                            buffer.append("series :"+ values.getSeries()+"\n\n");
                        }
                        showMessage("LotCreation Data", buffer.toString());
                    }
                });

            }
        });


        //////////////////////////////////////
        bale_number.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                doProcessCode();

//                doProcessSeries();
//                bale_number.requestFocus();
            }
        });


//             seriesvalue.setText();
//          lotCreationViewModel.getlotcount(RefId)
//
//
//     doProcessSeries();
//             lotCreationViewModel.getlotcount();
//             seriesvalue.setText(lotCreationViewModel.getlotcount()+"");


//     lotCreationViewModel.getlotcount().observe(this, new Observer<LotCreationModel>() {
//         @Override
//         public void onChanged(LotCreationModel lotCreationModel) {
////             seriesvalue.setText("");
//
//
////             Integer i=1;
////             Log.d("hi",lotCreationModel.getHeaderId());
////             Log.d("hi1",lotCreationModel.getSeries()+"");
////             i=i+lotCreationModel.getSeries();
////             seriesvalue.setText(i+"");
//
//         }
//     });


        /////////////////////////////////////////////////

//        String count= lotCreationViewModel.getformercount()+"";
//        Log.d("countformer", count);

//        lotCreationViewModel.getformercount().observe(this, new Observer<LotCreationModel>() {
//            @Override
//            public void onChanged(LotCreationModel lotCreationModel) {
//
//            }
//        });




        bale_number.addTextChangedListener(new TextWatcher() {

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


        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveprocess();
            }
        });

    }


    private void saveprocess() {
        try {
            if (flag.equals("0")) {
                Toast.makeText(getApplicationContext(), "INVALID FARMER CODE", Toast.LENGTH_LONG).show();
                farmer_name.setText("");
                lot_value.setText("");
                seriesvalue.setText("");
                fathers_name.setText("");
                village_name.setText("");
            } else {
                List<LotCreationModel> lotCreationsValid = lotCreationViewModel.getAllLotCreation();

                String headerId = header_value.getText().toString().trim();
                String farmerCode = code_value.getText().toString().trim();
                String farmerName = farmer_name.getText().toString().trim();
                String lotNumber = lot_value.getText().toString().trim();
                String farmerFather = fathers_name.getText().toString().trim();
                String village = village_name.getText().toString().trim();
                String baleNumber = bale_number.getText().toString().trim();
                String series = seriesvalue.getText().toString().trim();


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
                    LotCreationModel lotCreations = new LotCreationModel();


                  Integer lotnumber=Integer.parseInt(lotNumber);
                  Integer serie=Integer.parseInt(series);

                    lotCreations.setHeaderId(headerId);
                    lotCreations.setFarmerCode(farmerCode);
                    lotCreations.setLotNumber(lotnumber);
                    lotCreations.setBaleNumber(baleNumber);
                    lotCreations.setSeries(serie);
                    java.text.SimpleDateFormat format = new java.text.SimpleDateFormat("yyyy-MM-dd");
                    lotCreations.setCreatedDate(format.format(new java.util.Date()));
                    lotCreations.setCreatedBy(code);
                    lotCreations.setAttribute3("1");
//

//                         runOnUiThread(new Runnable() {
//                             @Override
//                             public void run() {
//                                 lotCreationViewModel.insertLotCreation(lotCreations);
//
//                             }
//                         });
                    lotCreationViewModel.insertLotCreation(lotCreations);

                    bale_number.setText("");
//                    List<LotCreationModel> lotCreation = Arrays.asList(lotCreations);


//                    lotCreationViewModel.lotcreationpost(lotCreation);

                    ///////////////////////////////////////////////////
                    ///////////////////////////////////////////////////
                    ///////////////////////////////////////////////////
//                    lotCreationViewModel.insertLotCreationPost(lotCreations);
                    /////////////////////////////////////////////////////////
                    Toast.makeText(getApplicationContext(), "SAVED SUCCESSFULLY", Toast.LENGTH_LONG).show();
                    // activityLotBinding.sync.setVisibility(View.VISIBLE);
//                    seriesvalue.setText("");

                    try{
                        if(lotCreationsValid!=null)
                            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
                                List<LotCreationModel> creationList1 =  lotCreationViewModel.getLotCreations(headerId);
                                List<LotCreationModel> list = creationList1.stream()
                                        .filter(distinctByKey(p -> p.getLotNumber()))
                                        .collect(Collectors.toList());



                                 totalLot = list.size();
                                totalBales = creationList1.size();


                                //activityLotBinding.totalsValues.setText(totalLot+"  /  "+totalBales);

                               c =creationList1.stream().filter(s->s.getFarmerCode().equals(farmerCode)).collect(Collectors.toList());

                                if(c!=null) {
                                    TOTBALES.setText(totalBales+"");
                                    totals_values.setText(totalLot + " / " +c.size());

//                                    + "   TOTAL BALES : " + totalBales

                                }

//

                            }
                    }
                    catch (Exception x){
                        System.out.println(">>>>>>>>>>>>>>> Some Error with code inside lots/bales");
                    }
                } else {
                    if (result == true){
                        Toast.makeText(getApplicationContext(), "BALE NUMBER ALREAD PRESENT FAIL ", Toast.LENGTH_SHORT).show();
                        bale_number.setText("");
                    }

                    if (seriesvalid == true){
                        Toast.makeText(getApplicationContext(), "BALE NUMBER SERIES ALREAD PRESENT", Toast.LENGTH_LONG).show();
                        bale_number.setText("");
                    }
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }


    }

    private void doProcessCode() {

        try {
            //activityLotBinding.sync.setVisibility(View.INVISIBLE);
            String farmerCode = code_value.getText().toString().trim();
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
                farmer_name.setText("");
                lot_value.setText("");
                seriesvalue.setText("");
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
                                lotCreations = lotCreationViewModel.getLotCreations(RefId);
                                if (null == lotCreations || lotCreations.size() == 0) {
                                    farmer_name.setText(farmerMaster.getFarmerName());
                                    fathers_name.setText(farmerMaster.getFarM_FATHER_NAME());
                                    village_name.setText(farmerMaster.getVillageCode());
                                    lot_value.setText("1");
                                    seriesvalue.setText("0");
                                }

                                if (lotCreations != null && lotCreations.size() != 0) {
                                    List<LotCreationModel> ls = lotCreations
                                            .stream()
                                            .filter(fm -> fm.getFarmerCode().equals(farmerCode))
                                            .collect(Collectors.toList());

                                    if (null != ls && ls.size() != 0) {
                                        ls.forEach(System.out::println);
                                        //System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>><<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
//                                        farmer_name.setText(ls.get(ls.size() - 1).get());
                                         lot_value.setText(ls.get(ls.size() - 1).getLotNumber().toString());
//                                        fathers_name.setText(ls.get(ls.size() - 1).getFarmerFatherName());
//                                        village_name.setText(ls.get(ls.size() - 1).getVillageCode());
                                        Integer i = ls.size();
                                        seriesvalue.setText(i.toString());
                                    } else {

                                        farmer_name.setText(farmerMaster.getFarmerName());
                                        List<LotCreationModel> list = lotCreations.stream()
                                                .filter(distinctByKey(p -> p.getFarmerCode()))
                                                .collect(Collectors.toList());
                                        list.forEach(System.out::println);
                                        System.out.println(list.size());
                                        Integer i = list.size() + 1;
                                        //System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>><<<<<<<<<<<<<<<<<<<<<<<<<<<<< 555555555555");
                                        System.out.println(i);
                                        lot_value.setText(i.toString());
                                        farmer_name.setText(farmerMaster.getFarmerName());
                                        fathers_name.setText(farmerMaster.getFarM_FATHER_NAME());
                                        village_name.setText(farmerMaster.getVillageCode());
                                        seriesvalue.setText("0");
                                    }
                                }
                            }
                        } else {
                            farmer_name.setText("Not Available");
                            lot_value.setText("Not Available");
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
                    String lotNumber = lot_value.getText().toString();
                    List<String> valueSet = new ArrayList<>();
                    valueSet.add(lotNumber);
                    valueSet.add(RefId);

                    baleSeriesValue = lotCreationViewModel.getBaleSeries(valueSet);
                    Integer baleSeriesValueCount = 0;
                    String strBaleSeries = bale_number.getText().toString();
                    if (baleSeriesValue.size() != 0 && strBaleSeries != null) {

                        baleSeriesValueCount = baleSeriesValue.get(baleSeriesValue.size() - 1);
                        baleSeriesValueCount += 1;
                       seriesvalue.setText(baleSeriesValueCount.toString());


                    }
                    if (baleSeriesValue.size() == 0 && strBaleSeries != null) {

                        String strPresentSeries = seriesvalue.getText().toString();
                        Integer presentSeries = Integer.parseInt(strPresentSeries);
                        if (presentSeries == 0)
                            presentSeries += 1;
                       seriesvalue.setText(presentSeries.toString());

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
        header_value.setText(RefId);
        et_org_code3.setText(OrgCode);
        et_date3.setText(DateTime);
//        doProcessSeries();
    }

    public void back(View view) {
        Intent intent = new Intent(this,FarmerPurchase.class);
        startActivity(intent);
    }

    public void btn(View view) {
        bale_number.setText("");
        code_value.setText("");
        farmer_name.setText("");
        fathers_name.setText("");
        village_name.setText("");
        seriesvalue.setText("");
        code_value.requestFocus();
//        bale_number.invalidate();





    }
    public static <T> Predicate<T> distinctByKey(Function<? super T, Object> keyExtractor) {
        Map<Object, Boolean> map = new ConcurrentHashMap<>();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            return t -> map.putIfAbsent(keyExtractor.apply(t), Boolean.TRUE) == null;
        }
        return null;
    }

    public void complete(View view) {
        List<LotCreationModel> list= lotCreationViewModel.getLotcreationdata();
        System.out.println("************************* START");
        if (list != null)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                list.forEach(System.out::println);
            }
        if (list != null)
            lotCreationViewModel.lotcreationpost(list);

        System.out.println("************************* END");

        System.out.println("************************* LOT Creation retrive STARTS");



    }
    public void showMessage(String title, String message) {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }


    public void RecyclerView(View view) {
        Intent intent= new Intent(this, LotRecyclerView.class);
        startActivity(intent);
    }
}