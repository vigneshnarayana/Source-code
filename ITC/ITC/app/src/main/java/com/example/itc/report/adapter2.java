package com.example.itc.report;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.itc.R;
import com.example.itc.mapping.adapter;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

public class adapter2 extends FirebaseRecyclerAdapter<reportmodel, adapter2.myViewHolder>
{
    public adapter2(@NonNull FirebaseRecyclerOptions<reportmodel> options)
    {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull adapter2.myViewHolder holder, int position, @NonNull reportmodel model) {
        holder.location.setText(model.getLocation());
        holder.asset.setText(model.getAsset());
    }


    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_item_mapping,parent,false);
        return new myViewHolder(view);
    }

    class myViewHolder extends RecyclerView.ViewHolder
    {

        TextView location,asset;
        public myViewHolder(@NonNull View itemView) {
            super(itemView);

            location = itemView.findViewById(R.id.et_assetitem);
             asset = itemView.findViewById(R.id.et_assettime);


        }
    }

}
