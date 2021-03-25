package com.example.gpiproject.Adaptor;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gpiproject.R;
import com.example.gpiproject.model.LotCreationModel;

import java.util.ArrayList;
import java.util.List;

public class LotAdaptor extends RecyclerView.Adapter<LotAdaptor.LotHolder>{

    private List<LotCreationModel> notes =new ArrayList<>();
    @NonNull
    @Override
    public LotHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemview= LayoutInflater.from(parent.getContext())
                .inflate(R.layout.lotitem,parent,false);
        return new LotHolder(itemview);
    }

    @Override
    public void onBindViewHolder(@NonNull LotHolder holder, int position) {
        LotCreationModel  creationModel=notes.get(position);
        holder.txt1.setText(creationModel.getHeaderId());
        holder.txt2.setText(creationModel.getBaleNumber());
        holder.txt3.setText(creationModel.getFarmerCode());

    }

    @Override
    public int getItemCount() {
        return notes.size();
    }
    public void setNotes(List<LotCreationModel> creationModels){
        this.notes=creationModels;
        notifyDataSetChanged();
    }

    class LotHolder extends RecyclerView.ViewHolder{
        private TextView txt1;
        private TextView txt2;
        private TextView txt3;

        public LotHolder(@NonNull View itemView) {
            super(itemView);
            txt1=itemView.findViewById(R.id.txt1);
            txt2=itemView.findViewById(R.id.txt2);
            txt3=itemView.findViewById(R.id.txt3);
        }
    }
}
