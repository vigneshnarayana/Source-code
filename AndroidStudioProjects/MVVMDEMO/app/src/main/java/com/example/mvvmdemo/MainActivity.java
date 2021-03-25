package com.example.mvvmdemo;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.mvvmdemo.Adaptor.UsersAdapter;
import com.example.mvvmdemo.model.Users;
import com.example.mvvmdemo.viewmodel.UserViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class MainActivity extends AppCompatActivity implements UsersAdapter.ClickListener {

    RecyclerView recyclerView;
    UsersAdapter usersAdapter;
    UserViewModel userViewModel;

    FloatingActionButton floatingActionButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
         floatingActionButton=findViewById(R.id.btnNewUser);
        userViewModel= ViewModelProviders.of(this).get(UserViewModel.class);
        recyclerView=findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        usersAdapter = new UsersAdapter(this);
        userViewModel.getAllUsers().observe(this, new Observer<List<Users>>() {
            @Override
            public void onChanged(List<Users> users) {
                if(users.size() >0){
                   usersAdapter.setData(users);
                   recyclerView.setAdapter(usersAdapter);
                }

            }
        });

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addUser();
            }
        });
    }

    public void addUser(){
        final AlertDialog.Builder builder=new AlertDialog.Builder(this);
        final View view=getLayoutInflater().inflate(R.layout.row_add,null);
        builder.setView(view);
        AlertDialog alertDialog=builder.create();


        Button btnAddUser=view.findViewById(R.id.addUserbtn);
        EditText edUser=view.findViewById(R.id.edUser);
        TextView tvDetails=view.findViewById(R.id.edUser);

        btnAddUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             if(edUser.getText() !=null){
                 String username=edUser.getText().toString().trim();
                 Users users=new Users();
                 users.setUsername(username);

                 userViewModel.insertUser(users);
                 alertDialog.dismiss();
             }
            }
        });
        alertDialog.show();
    }

    @Override
    public void updateClicked(Users users) {
     updateUser(users);
    }

    @Override
    public void deleteClicked(Users users) {
   userViewModel.deleteUsers(users);
    }

    public void updateUser(Users users){
        final AlertDialog.Builder builder=new AlertDialog.Builder(this);
        final View view=getLayoutInflater().inflate(R.layout.row_add,null);
        builder.setView(view);
        AlertDialog alertDialog=builder.create();


        Button btnAddUser=view.findViewById(R.id.addUserbtn);
        EditText edUser=view.findViewById(R.id.edUser);
        TextView tvDetails=view.findViewById(R.id.edUser);
        btnAddUser.setText("Update");

        btnAddUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(edUser.getText() !=null){
                    String username=edUser.getText().toString().trim();

                    users.setUsername(username);

                    userViewModel.updateUsers(users);
                    alertDialog.dismiss();
                }
            }
        });
        alertDialog.show();
    }
}