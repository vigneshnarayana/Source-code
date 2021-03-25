package com.example.itc;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Iterator;

public class adapter extends FirebaseRecyclerAdapter<UserHelperClass,adapter.myViewHolder>
{DatabaseReference myRef5;
    public adapter(@NonNull FirebaseRecyclerOptions<UserHelperClass> options)
    {
        super(options);

    }

    @Override
    protected void onBindViewHolder(@NonNull myViewHolder holder, int position, @NonNull UserHelperClass model)
    {
        Intent intent = getIntent();
         String value = intent.getStringExtra("locations");

        holder.asset.setText(model.getAsset());
        holder.time.setText(model.getTime());


        //String value=model.getAsset();
//
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        myRef5=database.getReference("ITC");
        myRef5.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Iterator<DataSnapshot> iteams= snapshot.child(value).getChildren().iterator();
                while (iteams.hasNext()) {
                    DataSnapshot item = iteams.next();

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


       }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_item_mapping,parent,false);
        return new myViewHolder(view);
    }

    public Intent getIntent() {
        Intent intent = new Intent();
        // String value = intent.getStringExtra("location");
        return intent;
    }

    class myViewHolder extends RecyclerView.ViewHolder
    {

        TextView asset,time;
        public myViewHolder(@NonNull View itemView) {
            super(itemView);

            asset = itemView.findViewById(R.id.et_assetitem);
            time = itemView.findViewById(R.id.et_assettime);


        }
    }

}
