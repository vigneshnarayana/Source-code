package com.example.itc.report;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.itc.R;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.firebase.ui.database.SnapshotParser;
import com.google.firebase.database.FirebaseDatabase;

public class report extends AppCompatActivity {
    RecyclerView recyclerView;
    adapter2 adapter2;
   @Override
   protected void onCreate(Bundle savedInstanceState) {
       super.onCreate(savedInstanceState);
       setContentView(R.layout.recycleview_report);

       recyclerView=findViewById(R.id.recycleView);
       recyclerView.setLayoutManager(new LinearLayoutManager(this));

       FirebaseRecyclerOptions<reportmodel> options =
               new FirebaseRecyclerOptions.Builder<reportmodel>()
                       .setQuery(FirebaseDatabase.getInstance().getReference().child("Report"), reportmodel.class)
                       .build();


       adapter2 = new adapter2(options);
       recyclerView.setAdapter(adapter2);


       Button Get = findViewById(R.id.getReport);

       Get.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {

               adapter2.startListening();

           }
       });
   }

    @Override
    protected void onStart()
    {
        super.onStart();

    }

    @Override
    protected void onStop()
    {
        super.onStop();
        adapter2.stopListening();
    }

}
