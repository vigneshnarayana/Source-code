package com.example.itc.mapping;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.itc.R;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class
Home extends AppCompatActivity {
    FirebaseDatabase database,db,db1;
    DatabaseReference myRef2,myRef3,myRef4,myRef5;

    RecyclerView recyclerView;
    com.example.itc.mapping.adapter adapter;
    EditText et_ass;
    Button btn_map;
    String value;
    String DateTime;
    String DateTime1;
    Spinner et_loc;


    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        Calendar calendar=Calendar.getInstance();
        SimpleDateFormat datetime1= new SimpleDateFormat("dd-mm-YYYY hh:mm: a");
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.recycleview_mapping);

        et_loc=findViewById(R.id.et_loc);
        et_ass=findViewById(R.id.et_ass);
        btn_map=findViewById(R.id.btn_map);



         DateTime1=datetime1.format(calendar.getTime());

        Intent intent = getIntent();
        value = intent.getStringExtra("location");


        recyclerView=findViewById(R.id.recycleView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        FirebaseRecyclerOptions<UserHelperClass> options =
                new FirebaseRecyclerOptions.Builder<UserHelperClass>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("maprecycler").child(value), UserHelperClass.class)
                        .build();


        adapter = new adapter(options);
        recyclerView.setAdapter(adapter);




    }

    @Override
    protected void onStart()
    {
        super.onStart();
        adapter.startListening();
        Calendar calendar=Calendar.getInstance();
        SimpleDateFormat datetime= new SimpleDateFormat("dd-MM-YYY hh:mm:ss");
        DateTime=datetime.format(calendar.getTime());



    }

    @Override
    protected void onStop()
    {
        super.onStop();
        adapter.stopListening();
    }

    public void btnMap(View view)
    {
        String value1=et_ass.getText().toString().trim();
        database = FirebaseDatabase.getInstance();
        myRef2 = database.getReference("maprecycler");
        myRef2.child(value).child(DateTime).child("asset").setValue(value1);
      //  myRef3=database.getReference(value);
        myRef2.child(value).child(DateTime).child("time").setValue(DateTime1);
       // super.onRestart();
        ////////////////////////////////////////////////////////////////////////
          db = FirebaseDatabase.getInstance();
          myRef3 = db.getReference("Mapping");
          myRef3.child(value).child(value1).setValue(value1);

          ////////////////////////////////////////////////////////////////
        db1 = FirebaseDatabase.getInstance();
        myRef4 = db1.getReference("Report");
        myRef5 = db1.getReference("Report");
        myRef4.child(DateTime).child("location").setValue(value);
        myRef5.child(DateTime).child("asset").setValue(value1);

        et_ass.setText("");


onStart();
    }

}