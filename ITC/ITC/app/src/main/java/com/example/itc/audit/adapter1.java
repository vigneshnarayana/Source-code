package com.example.itc.audit;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.itc.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

public class adapter1 extends FirebaseRecyclerAdapter<UserHelperClass1, adapter1.myViewHolder>
{
    public adapter1(@NonNull FirebaseRecyclerOptions<UserHelperClass1> options)
    {
        super(options);

    }

    @Override
    protected void onBindViewHolder(@NonNull myViewHolder holder, int position, @NonNull UserHelperClass1 model) {
        holder.asset.setText(model.getAsset());
        holder.time.setText(model.getTime());
       // String value=model.getAsset();
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

        TextView asset,time;
        public myViewHolder(@NonNull View itemView) {
            super(itemView);

            asset = itemView.findViewById(R.id.et_assetitem);
            time = itemView.findViewById(R.id.et_assettime);


        }
    }

}
