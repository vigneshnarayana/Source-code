package com.example.recyclerviewbasics;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
      String s1[],s2[];
      RecyclerView recyclerView;
      int image[]={R.drawable.c,R.drawable.java,R.drawable.js,R.drawable.cotlin,R.drawable.css,R.drawable.matlap,R.drawable.scala};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        recyclerView= findViewById(R.id.recycleRView);
        s1=getResources().getStringArray(R.array.programming_languages);
        s2=getResources().getStringArray(R.array.description);

              MyAdaptor myAdaptor=new MyAdaptor(this,s1,s2,image);
              recyclerView.setAdapter(myAdaptor);
              recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}