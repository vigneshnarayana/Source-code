package com.example.itc;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class
Home extends AppCompatActivity {
    FirebaseDatabase database;
    DatabaseReference myRef2,myRef3;

    RecyclerView recyclerView;
    adapter adapter;
    EditText et_loc,et_ass;
    Button btn_map;
    String value;
    String DateTime;
    String DateTime1;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {

        SimpleDateFormat datetime1= new SimpleDateFormat("dd-MM-YYY hh:mm: a");
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.recycleview_mapping);

        et_loc=findViewById(R.id.et_loc);
        et_ass=findViewById(R.id.et_ass);
        btn_map=findViewById(R.id.btn_map);
        Calendar calendar=Calendar.getInstance();


         DateTime1=datetime1.format(calendar.getTime());

        Intent intent = getIntent();
        value = intent.getStringExtra("location");


        recyclerView=findViewById(R.id.recycleView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        FirebaseRecyclerOptions<UserHelperClass> options =
                new FirebaseRecyclerOptions.Builder<UserHelperClass>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child(value), UserHelperClass.class)
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
        myRef2 = database.getReference(value);
        myRef2.child(DateTime).child("asset").setValue(value1);
      //  myRef3=database.getReference(value);
        myRef2.child(DateTime).child("time").setValue(DateTime1);
       // super.onRestart();

onStart();
    }

}