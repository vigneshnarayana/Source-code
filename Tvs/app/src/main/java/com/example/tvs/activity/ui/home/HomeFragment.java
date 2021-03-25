package com.example.tvs.activity.ui.home;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.tvs.R;
import com.example.tvs.activity.activitys.BreakDown;
import com.example.tvs.activity.activitys.Casting;
import com.example.tvs.activity.activitys.OnlinePE;
import com.example.tvs.activity.activitys.Productivity;
import com.example.tvs.activity.activitys.Test;
import com.example.tvs.activity.adapter.CustomGridViewAdapter;


public class HomeFragment extends Fragment {
    GridView gridview;
    View root;
    TextView EmpcodeText;
    SharedPreferences sp;
    String CreatedBy;


    Intent intent;
    String[] gridViewString = {
            "Productivity", "Breakdown", "Quality Assurance","Casting Availability","Near Miss / Safety","Consumable Packaging Box","Online PE"};

    int[] gridViewImageId = {
            R.drawable.productivity,R.drawable.breakdown,R.drawable.quality_assurance,R.drawable.casting,
            R.drawable.safety,R.drawable.packages,R.drawable.onlinepe};


    private HomeViewModel homeViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
            ViewGroup container, Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_home, container, false);
        initialize();

        gridview = root.findViewById(R.id.simpleGridView);
        EmpcodeText=root.findViewById(R.id.EmpcodeText);
        sp=getActivity().getSharedPreferences("data", Context.MODE_PRIVATE);
        CreatedBy=sp.getString("Employeecode","");



        CustomGridViewAdapter customAdapter = new CustomGridViewAdapter(getContext(), gridViewString, gridViewImageId);
        gridview.setAdapter(customAdapter);


        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long id) {
                String prompt = (String) parent.getItemAtPosition(i);
//                Toast.makeText(getApplicationContext(),
//                        prompt,
//                        Toast.LENGTH_LONG).show();

                if (gridViewString[+i].equals("Productivity")) {

                    intent = new Intent(getActivity(), Productivity.class);
                    startActivity(intent);

                }
                if (gridViewString[+i].equals("Breakdown")) {

                    intent = new Intent(getActivity(), BreakDown.class);
                    startActivity(intent);
                }
                if (gridViewString[+i].equals("Quality Assurance")) {

//                    intent = new Intent(MainGridView.this, PaySlip.class);
//                    startActivity(intent);
                    Toast.makeText(getActivity(), "Update Will Coming Soon...", Toast.LENGTH_SHORT).show();
                }
                if (gridViewString[+i].equals("Casting Availability")) {

                    intent = new Intent(getActivity(), Casting.class);
                    startActivity(intent);

                }



                if (gridViewString[+i].equals("Near Miss / Safety")) {
                    intent = new Intent(getActivity(), Test.class);
                    startActivity(intent);

//                    intent = new Intent(MainGridView.this, PaySlip.class);
//                    startActivity(intent);
                    Toast.makeText(getActivity(), "Update Will Coming Soon...", Toast.LENGTH_SHORT).show();
                }
                if (gridViewString[+i].equals("Consumable Packaging Box")) {

//                    intent = new Intent(MainGridView.this, PaySlip.class);
//                    startActivity(intent);
                    Toast.makeText(getActivity(), "Update Will Coming Soon...", Toast.LENGTH_SHORT).show();
                }
                if (gridViewString[+i].equals("Online PE")) {

                    intent = new Intent(getActivity(), OnlinePE.class);
                    startActivity(intent);
//                    Toast.makeText(getActivity(), "Update Will Coming Soon...", Toast.LENGTH_SHORT).show();
                }

            }
        });

        homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);
        homeViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {

            }
        });
        return root;


    }
    public void initialize(){

    }
}