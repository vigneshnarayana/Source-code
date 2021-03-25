package com.zebra.rfid.demo.sdksample;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;

import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.zebra.rfid.api3.TagData;
import com.zebra.rfid.demo.sdksample.Repository.DataRepository;
import com.zebra.rfid.demo.sdksample.Viewmodel.DataViewModel;
import com.zebra.rfid.demo.sdksample.model.Data;

import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

public class MainActivity extends AppCompatActivity implements RFIDHandler.ResponseHandlerInterface,AdapterView.OnItemSelectedListener {

    public TextView statusTextViewRFID = null;
    private EditText textrfid;
    private TextView testStatus;
    private DataRepository dataRepository;
    private DataViewModel dataViewModel ;
    Button btn_submit;
    Button export;
    Button btn_back;

    RFIDHandler rfidHandler;
    final static String TAG = "RFID_SAMPLE";
    private String[] Names = {"LOC1", "LOC2", "LOC3","LOC4", "LOC5", "LOC6"};
    Spinner et_loc;
    String val1;
    Spinner spin;
    RecyclerView recyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dataViewModel = ViewModelProviders.of(this,new DataViewFactory(getApplication(),MainActivity.this)).get(DataViewModel.class);

/////////////////////
        RecyclerView recyclerView=findViewById(R.id.recycleView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        MaterialAdaptor materialAdaptor=new MaterialAdaptor();
        recyclerView.setAdapter(materialAdaptor);

        //////////////////


//        Data data=new Data("hi","hello");
//        dataViewModel.insertData(data);




        statusTextViewRFID = findViewById(R.id.textStatus);
        textrfid = findViewById(R.id.textViewdata);
        testStatus = findViewById(R.id.testStatus);
        btn_submit = findViewById(R.id.btn_submit);
        btn_back = findViewById(R.id.btn_back);
        export = findViewById(R.id.export);

        // RFID Handler
        rfidHandler = new RFIDHandler();
        rfidHandler.onCreate(this);
        et_loc = findViewById(R.id.et_loc);
        spin = (Spinner) findViewById(R.id.et_loc);
        spin.setOnItemSelectedListener(this);

        // set up button click listener

        adapter();

        final Handler handler = new Handler(Looper.getMainLooper());
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                //Do something after 100ms
                String result = rfidHandler.Test1();
                testStatus.setText(result);

            }
        }, 600);


        recyclerView=findViewById(R.id.recycleView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                YoYo.with(Techniques.FadeIn)
                        .duration(1000)
                        .repeat(0)
                        .playOn(btn_submit);
                String TagId=textrfid.getText().toString().trim();
                Data data=new Data(TagId,val1);
                try{
                    if (TextUtils.isEmpty(TagId)) {
                        Toast.makeText(getApplicationContext(), "FILL TagId ", Toast.LENGTH_LONG).show();
                    }else {
                    dataViewModel.insertData(data);
                    Toast.makeText(MainActivity.this, "Inserted Successfully", Toast.LENGTH_SHORT).show();
                        textrfid.setText("");
                    }

                }catch (Exception e){
                    Toast.makeText(MainActivity.this, "Inserted Fail", Toast.LENGTH_SHORT).show();
                }

            }
        });

        dataViewModel.getDatadetails().observe(this, new Observer<List<Data>>() {
            @Override
            public void onChanged(List<Data> data) {
                materialAdaptor.SetNotes(data);

            }
        });


        ///////////////////////////
        export.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                YoYo.with(Techniques.FadeIn)
                        .duration(1000)
                        .repeat(0)
                        .playOn(export);
                dataViewModel.getDatadetails().observe(MainActivity.this, new Observer<List<Data>>() {
                    @Override
                    public void onChanged(List<Data> data) {
                        StringBuilder data1 = new StringBuilder();
                        data1.append("Tag ID,Location");
                        for(Data a:data){


                            data1.append("\n"+String.valueOf(a.getTagId())+","+String.valueOf(a.getLocation()));

                        }
                        try{
                            FileOutputStream out = openFileOutput("data.csv", Context.MODE_PRIVATE);
                            out.write((data1.toString()).getBytes());
                            out.close();

                            Context context = getApplicationContext();
                            File fileLocation = new File(getFilesDir(), "data.csv");
                            Uri path = FileProvider.getUriForFile(context,"com.zebra.rfid.demo.sdksample.fileprovider",fileLocation);
                            Intent fileIntent = new Intent(Intent.ACTION_SEND);
                            fileIntent.setType("text/csv");
                            fileIntent.putExtra(Intent.EXTRA_SUBJECT, "Data");
                            fileIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                            fileIntent.putExtra(Intent.EXTRA_STREAM, path);
                            startActivity(Intent.createChooser(fileIntent, "send mail"));

                        }catch(Exception e){
                            e.printStackTrace();
                        }

                    }
                });

                Intent intent=new Intent(MainActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });
        ////////////////////
    }

    /////////////////////////////////////


    //////////////////////////////////////







    private void adapter() {


        //Getting the instance of Spinner and applying OnItemSelectedListener on it


//Creating the ArrayAdapter instance having the bank name list
        ArrayAdapter aa = new ArrayAdapter(this, android.R.layout.simple_spinner_item, Names);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//Setting the ArrayAdapter data on the Spinner
        spin.setAdapter(aa);
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_main, menu);
//        return true;
//    }

//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//
//        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }
//
//        return super.onOptionsItemSelected(item);
//    }

    @Override
    protected void onPause() {
        super.onPause();
        rfidHandler.onPause();
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        String status = rfidHandler.onResume();
        statusTextViewRFID.setText(status);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        rfidHandler.onDestroy();
    }


    @Override
    public void handleTagdata(TagData[] tagData) {
        final StringBuilder sb = new StringBuilder();
        for (int index = 0; index < tagData.length; index++) {
            sb.append(tagData[index].getTagID() + "\n");
        }
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                textrfid.append(sb.toString());
            }
        });
    }

    @Override
    public void handleTriggerPress(boolean pressed) {
        if (pressed) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    textrfid.setText("");
                }
            });
            rfidHandler.performInventory();
        } else
            rfidHandler.stopInventory();
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        val1 = Names[position];
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    public void btn_Back(View view) {
        YoYo.with(Techniques.FadeIn)
                .duration(1000)
                .repeat(0)
                .playOn(btn_back);
        Intent intent=new Intent(this,LandingPage.class);
        startActivity(intent);
    }
}
