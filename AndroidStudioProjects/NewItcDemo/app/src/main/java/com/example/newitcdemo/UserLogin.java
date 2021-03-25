package com.example.newitcdemo;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Pair;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.example.newitcdemo.Model.Location;
import com.example.newitcdemo.Model.Login;
import com.example.newitcdemo.Model.User;
import com.example.newitcdemo.ViewModel.UserViewModel;

import java.util.ArrayList;
import java.util.List;

public class UserLogin extends AppCompatActivity  {

    EditText et_username, et_password;
    String userName, password, label;
    List<String> labels;
    Spinner spinner;
    List<User> userMasters = new ArrayList<>();
    List<Login> loginMasters = new ArrayList<>();
    private UserViewModel userViewModel;
    SharedPreferences sp;
    Button btn_exit;
    Button btn_clear1;
    Button btn_submit;
    ImageView img_logo;
    TextView txt_logo;
    SwipeRefreshLayout mSwipeRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_login);
        mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipeToRefresh);
        mSwipeRefreshLayout.setColorSchemeResources(R.color.design_default_color_error);

        et_password = findViewById(R.id.et_password);
        et_username = findViewById(R.id.et_username);
        btn_exit = findViewById(R.id.btn_exit);
         btn_clear1=findViewById(R.id.btn_clear);
        btn_submit=findViewById(R.id.btn_submit);
        img_logo=findViewById(R.id.img_logo);
        txt_logo=findViewById(R.id.txt_logo);



//        spinner.setOnItemSelectedListener(this);

        userViewModel = ViewModelProviders.of(this).get(UserViewModel.class);
        userViewModel.getUserService();
        userViewModel.getLocation();
        sp=getSharedPreferences("data", Context.MODE_PRIVATE);


        ///////////////////////////////
//        userViewModel.getAllLocationDetails().observe(UserLogin.this, new Observer<List<Location>>() {
//            @Override
//            public void onChanged(List<Location> locations) {
//
//                if(TextUtils.isEmpty(label)){
//                    for (Location location : locations) {
////                        labels.add(location.getLocationID());
//                    }
//                }
//
//
//            }
//        });

        ///////////////////////////////

//        userMasters = userViewModel.getUserMasterList();
        loginMasters = userViewModel.getLoginDetailList();
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
               Intent intent=new Intent(UserLogin.this,UserLogin.class);
               startActivity(intent);


                mSwipeRefreshLayout.setRefreshing(false);
            }
        });

//       loadSpinnerData();


  btn_exit.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
          YoYo.with(Techniques.FadeIn)
                  .duration(1000)
                  .repeat(0)
                  .playOn(btn_exit);
          AlertDialog.Builder builder = new AlertDialog.Builder(UserLogin.this);
          builder.setTitle(R.string.app_name);
          builder.setIcon(R.drawable.po);
          builder.setMessage("Do you want to exit?")
                  .setCancelable(false)
                  .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                      public void onClick(DialogInterface dialog, int id) {
                          finish();
                          moveTaskToBack(true);
                      }
                  })
                  .setNegativeButton("No", new DialogInterface.OnClickListener() {
                      public void onClick(DialogInterface dialog, int id) {
                          dialog.cancel();
                          Toast.makeText(UserLogin.this, "Skiped ", Toast.LENGTH_SHORT).show();
                      }
                  });
          AlertDialog alert = builder.create();
          alert.show();


      }
  });
    }

//    private void loadSpinnerData() {
//        labels = new ArrayList<>();
//        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, labels);
//        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        spinner.setAdapter(adapter);
//
//
//
//    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void Sumbmit_btn(View view) {
        YoYo.with(Techniques.FadeIn)
                .duration(1000)
                .repeat(0)
                .playOn(btn_submit);
        try {

            userName = et_username.getText().toString().trim();
            password = et_password.getText().toString().trim();

            if (TextUtils.isEmpty(userName)) {
                Toast.makeText(getApplicationContext(), "FILL EmployeeCode ", Toast.LENGTH_LONG).show();
            } else if (TextUtils.isEmpty(password)) {
                Toast.makeText(getApplicationContext(), "FILL PASSWORD", Toast.LENGTH_LONG).show();
            }
//            else if (TextUtils.isEmpty(label)) {
//                Toast.makeText(getApplicationContext(), "Check Your Internet Connection", Toast.LENGTH_LONG).show();
////                startActivity();
//            }
            else {
                Boolean credential = false;

                if (loginMasters != null && loginMasters.size() != 0)
                    if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
                        credential = loginMasters
                                .stream()
                                .filter(login -> login.getLocationCode().equals(userName) && login.getPassword().equals(password))
                                .findFirst()
                                .isPresent();
                    }
                if (loginMasters != null && loginMasters.size() != 0)
                    if (credential == true) {
                        try {

                            Intent intent= new Intent(UserLogin.this,LandingPage.class);

//                            Pair[] pairs= new Pair[1];

                            SharedPreferences.Editor editor=sp.edit();
                            editor.putString("LocationId",userName);
                            editor.putString("Employeecode",password);
                            editor.commit();

//                            pairs[0] = new Pair<View,String>(img_logo,"logo_image");
//                            pairs[1] = new Pair<View,String>(txt_logo,"welcome_text");
//
//                            ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(UserLogin.this,pairs);
//                            startActivity(intent,options.toBundle());

//                            Intent intent = new Intent(UserLogin.this, LandingPage.class);

//
                            startActivity(intent);

                        } catch (Exception e) {
                            e.printStackTrace();
                        }


                    } else {
                        Toast.makeText(this, "Invalid Data", Toast.LENGTH_SHORT).show();
                    }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
    private void startActivity() {
        super.onRestart();
        Intent i = new Intent(UserLogin.this,UserLogin.class);  //your class
        startActivity(i);
        finish();


    }

//    @Override
//    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//        label = parent.getItemAtPosition(position).toString();
//
//    }
//
//    @Override
//    public void onNothingSelected(AdapterView<?> parent) {
//
//    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    public void Clearbtn(View view) {

        YoYo.with(Techniques.FadeIn)
                .duration(1000)
                .repeat(0)
                .playOn(btn_clear1);
        et_username.setText("");
        et_password.setText("");
    }
}