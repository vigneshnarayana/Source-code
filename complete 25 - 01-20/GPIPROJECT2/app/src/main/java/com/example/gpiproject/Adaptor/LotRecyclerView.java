package com.example.gpiproject.Adaptor;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;

import com.example.gpiproject.R;
import com.example.gpiproject.Viewmodel.LotCreationViewModel;
import com.example.gpiproject.model.LotCreationModel;
import com.example.gpiproject.use.LotCreationViewFactory;
import com.example.gpiproject.view.LotCreation;

import java.util.List;

public class LotRecyclerView extends AppCompatActivity {
    LotCreationViewModel lotCreationViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_lot_recycler_view);


        RecyclerView recyclerView=findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        final LotAdaptor lotAdaptor=new LotAdaptor();
        recyclerView.setAdapter(lotAdaptor);

        lotCreationViewModel= ViewModelProviders.of(this, new LotCreationViewFactory(getApplication(),LotRecyclerView.this)).get(LotCreationViewModel.class);

        lotCreationViewModel.getlotlivedata().observe(this, new Observer<List<LotCreationModel>>() {
            @Override
            public void onChanged(List<LotCreationModel> lotCreationModels) {

                lotAdaptor.setNotes(lotCreationModels);


            }
        });


    }
}