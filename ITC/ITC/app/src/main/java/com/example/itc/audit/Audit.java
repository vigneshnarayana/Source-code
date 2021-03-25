package com.example.itc.audit;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.itc.R;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Audit extends AppCompatActivity {
    FirebaseDatabase database,db;
    DatabaseReference myRef2,myRef3;

    RecyclerView recyclerView1;
    com.example.itc.audit.adapter1 adapter1;
    EditText et_loc,et_ass;
    Button btn_au;
    String value;
    String DateTime;
    String DateTime1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        SimpleDateFormat datetime1= new SimpleDateFormat("dd-MM-YYYY hh:mm: a");
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.recyclerview_audit);

        et_loc=findViewById(R.id.au_loc);
        et_ass=findViewById(R.id.au_ass);
        btn_au=findViewById(R.id.btn_au);
        Calendar calendar=Calendar.getInstance();


        DateTime1=datetime1.format(calendar.getTime());

        Intent intent = getIntent();
        value = intent.getStringExtra("locatio");
        et_loc.setText(value);


        recyclerView1=findViewById(R.id.recycleView);
        recyclerView1.setLayoutManager(new LinearLayoutManager(this));

        FirebaseRecyclerOptions<com.example.itc.audit.UserHelperClass1> options =
                new FirebaseRecyclerOptions.Builder<com.example.itc.audit.UserHelperClass1>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("auditrecycleview")
                                .child(value),UserHelperClass1.class)
                        .build();


        adapter1 = new adapter1(options);
        recyclerView1.setAdapter(adapter1);



    }
    @Override
    protected void onStart()
    {
        super.onStart();
        adapter1.startListening();
        Calendar calendar=Calendar.getInstance();
        SimpleDateFormat datetime= new SimpleDateFormat("dd-MM-YYYY hh:mm:ss");
        DateTime=datetime.format(calendar.getTime());



    }

    @Override
    protected void onStop()
    {
        super.onStop();
        adapter1.stopListening();
    }

    public void btnMap(View view)
    {


        String value1=et_ass.getText().toString().trim();

            if(value1.equals(null)){
                Toast.makeText(this, "Empty Value", Toast.LENGTH_SHORT).show();


        }else {
                try {
                    database = FirebaseDatabase.getInstance();
                    myRef2 = database.getReference("auditrecycleview");
                    myRef2.child(value).child(DateTime).child("asset").setValue(value1);
                    //  myRef3=database.getReference(value);
                    myRef2.child(value).child(DateTime).child("time").setValue(DateTime1);
                    // super.onRestart();
                    db = FirebaseDatabase.getInstance();
                    myRef3 = db.getReference("Audit");

                    myRef3.child(value).child(value1).setValue(value1);
                    et_ass.setText("");

                    onStart();

                }catch (Exception e){}

            }


    }
}