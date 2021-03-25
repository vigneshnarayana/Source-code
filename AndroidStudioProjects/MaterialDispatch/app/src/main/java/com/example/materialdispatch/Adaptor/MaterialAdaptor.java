package com.example.materialdispatch.Adaptor;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.materialdispatch.Model.Material;
import com.example.materialdispatch.R;
import java.util.ArrayList;
import java.util.List;


public class MaterialAdaptor extends RecyclerView.Adapter<MaterialAdaptor.MaterialHolder>{
private List<Material> notes=new ArrayList<>();
    @NonNull
    @Override
    public MaterialHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemview= LayoutInflater.from(parent.getContext())
                .inflate(R.layout.materialitem,parent,false);
        return new MaterialHolder(itemview);
    }

    @Override
    public void onBindViewHolder(@NonNull MaterialHolder holder, int position) {
     Material material=notes.get(position);
     holder.txt1.setText(material.getCustomerName());
     holder.txt2.setText(material.getTrayNumber());
     holder.txt3.setText(material.getMaterial());
     holder.txt4.setText(material.getQuantity());
    }

    @Override
    public int getItemCount() {
        return notes.size();
    }

    public  void SetNotes(List<Material> materials)
    {
        this.notes=materials;
        notifyDataSetChanged();
    }
    class MaterialHolder extends RecyclerView.ViewHolder{
        private TextView txt1;
        private TextView txt2;
        private TextView txt3;
        private TextView txt4;

        public MaterialHolder(@NonNull View itemView) {
            super(itemView);
            txt1=itemView.findViewById(R.id.txt1);
            txt2=itemView.findViewById(R.id.txt2);
            txt3=itemView.findViewById(R.id.txt3);
            txt4=itemView.findViewById(R.id.txt4);
        }
    }
}
