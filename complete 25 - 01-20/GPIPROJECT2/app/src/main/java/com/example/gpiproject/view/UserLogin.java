package com.example.gpiproject.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.UserManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gpiproject.R;
import com.example.gpiproject.Viewmodel.LoginViewModel;
import com.example.gpiproject.model.Header;
import com.example.gpiproject.model.Login;
import com.example.gpiproject.model.Organization;
import com.example.gpiproject.use.LandingPageViewModelFactory;
import com.google.gson.Gson;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;

public class UserLogin extends AppCompatActivity implements AdapterView.OnItemSelectedListener{
    private LoginViewModel loginViewModel;
    Spinner spinner;
    List<String> labels;
    String label;
    List<Login> userMasters = new ArrayList<>();
    List<Organization> orgnMasters = new ArrayList<>();
    EditText et_password,et_username;
    String userName;
    String val;
    String usercode;

    SharedPreferences sp;


    Calendar calendar=Calendar.getInstance();
    SimpleDateFormat datetime= new SimpleDateFormat("YYYMMdd");
    String DateTime=datetime.format(calendar.getTime());


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_user_login);

//        loginViewModel= ViewModelProviders.of(this).get(LoginViewModel.class);

        loginViewModel= ViewModelProviders.of(this,new LandingPageViewModelFactory(getApplication(),UserLogin.this)).get(LoginViewModel.class);

        et_username=findViewById(R.id.et_username);
        et_password=findViewById(R.id.et_password);
        val=label+DateTime;
/////////////////////
         usercode= et_username.getText().toString().trim();

        sp=getSharedPreferences("data", Context.MODE_PRIVATE);

        loginViewModel.getorganizationservice();

        spinner = findViewById(R.id.spinner);
        spinner.setOnItemSelectedListener(this);


        // Loading spinner data from database
        loadSpinnerData();

        userMasters = loginViewModel.getUserMasterList();
        orgnMasters = loginViewModel.getOrgnMasterList();

    }

    public void submit(View view){
        //Validation  Goes Here
        loginViewModel.getData();

        try {

            userName = et_username.getText().toString().trim();
            String password = et_password.getText().toString().trim();

            if (TextUtils.isEmpty(userName)) {
                // activityMainBinding.txtEmailAddress.setError("Please Enter Your E-mail Address");
                Toast.makeText(getApplicationContext(), "FILL USERNAME ", Toast.LENGTH_LONG).show();
            } else if (TextUtils.isEmpty(password)) {
                // activityMainBinding.txtPassword.setError("Please Enter Your Password");
                Toast.makeText(getApplicationContext(), "FILL PASSWORD", Toast.LENGTH_LONG).show();
            } else {
                Boolean credential = false;

                if (userMasters != null && userMasters.size() != 0)
                    if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
                        /*userIn =  userMasters.stream().filter(userMaster -> userMaster.getEmpName().equals(userName)).findFirst().isPresent();
                         */
                        credential = userMasters
                                .stream()
                                .filter(userMaster -> userMaster.getUserID().equals(userName) && userMaster.getPassword().equals(password))
                                .findFirst()
                                .isPresent();
                    }
                if (userMasters != null && userMasters.size() != 0)
                    if (credential == true) {
                        String name= et_username.getText().toString().trim();
                        try {
                            val=label+DateTime;

                            SharedPreferences.Editor editor=sp.edit();
                            editor.putString("headerId",val);
                            editor.putString("Usercode",name);
                            editor.putString("Orgcode",label);
                            editor.commit();

                            Intent intent = new Intent(UserLogin.this, LandingPage.class);
//                            intent.putExtra("RefId",val);
//                            intent.putExtra("usercode",name);
//                            intent.putExtra("password",password);
//                            intent.putExtra("Orgcode",label);

                            startActivity(intent);


//                            loginViewModel.getAllHeaderID().observe(UserLogin.this, new Observer<List<Header>>() {
//                               @Override
//                               public void onChanged(List<Header> headers) {
//                                   for(Header header:headers){
//
//
//                                        val=label+DateTime;
//
//
//
////                                       Toast.makeText(UserLogin.this, val, Toast.LENGTH_LONG).show();
//                                       Log.d("data", val);
//
//
//                                       if(val.equals(header.getHeaderID())){
//
////                                           finish();
////                                           Intent intent1 = new Intent(getApplicationContext(), UserLogin.class);
//
//
////                                           startActivity(intent1);
//
//                                           }else {
//                                           headerNotificationTv.setVisibility(View.VISIBLE);
//
////                                           Toast.makeText(UserLogin.this, "Please Create Header", Toast.LENGTH_SHORT).show();
//
//
//
//
//                                           }
//
//                                   }
//                               }
//                           });




//

                                  } catch (Exception e)

                                {
                                   e.printStackTrace();
                               }






                    }else {
                        Toast.makeText(this, "Invalid Data", Toast.LENGTH_SHORT).show();
                    }
                  }
                   } catch (Exception e) {
            e.printStackTrace();
        }


///////////////////////////////////////////////////////////////////
//        Intent myIntent = new Intent(UserLogin.this, LandingPage.class);
//        UserLogin.this.startActivity(myIntent);
//        finish();
    }

    public void clear(View view){

        et_username.setText("");
        et_password.setText("");
//        try {
////            java.text.SimpleDateFormat format = new java.text.SimpleDateFormat("YYYY-MM-DD");
//
//            userName = et_username.getText().toString().trim();
//            String password = et_password.getText().toString().trim();
//
//            Login login = new Login();
//
//            if (TextUtils.isEmpty(userName)) {
//                // activityMainBinding.txtEmailAddress.setError("Please Enter Your E-mail Address");
//                Toast.makeText(getApplicationContext(), "FILL USERNAME ", Toast.LENGTH_LONG).show();
//            } else if (TextUtils.isEmpty(password)) {
//                // activityMainBinding.txtPassword.setError("Please Enter Your Password");
//                Toast.makeText(getApplicationContext(), "FILL PASSWORD", Toast.LENGTH_LONG).show();
//            } else {
//                Boolean userIn = false, passIn = false, credential = false;
//
//                if (userMasters != null && userMasters.size() != 0)
//                    if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
//                        /*userIn =  userMasters.stream().filter(userMaster -> userMaster.getEmpName().equals(userName)).findFirst().isPresent();
//                         */
//                        credential = userMasters
//                                .stream()
//                                .filter(userMaster -> userMaster.getUserID().equals(userName) && userMaster.getPassword().equals(password))
//                                .findFirst()
//                                .isPresent();
//                    }
//                if (userMasters != null && userMasters.size() != 0)
//                    if (credential == true) {
//                        String empCode = "Empty";
//                        String empType = "Empty";
//                        String fullName = "EmptyUser";
//
//                        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
//                            Optional<Login> matchingObject = userMasters
//                                    .stream()
//                                    .filter(um -> userName.equals(um.getUserID()))
//                                    .findFirst();
////
//
//                        }
//
////
//
//                        try {
//
//                            val=label+DateTime;
//                            Intent intent = new Intent(UserLogin.this, HeaderCreation.class);
//                            intent.putExtra("person",val);
//                            intent.putExtra("crop",label);
//                            intent.putExtra("username",userName);
//                           startActivity(intent);
//
//
////
//
//                        } catch (Exception e) {
//                            e.printStackTrace();
//                        }
//
//
//                    }
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//

        ////////////////////////////////////////


    }
    private void loadSpinnerData() {
        labels = new ArrayList<>();
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, labels);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        loginViewModel.getAllOrganizationDetails().observe(UserLogin.this, new Observer<List<Organization>>() {
            @Override
            public void onChanged(List<Organization> organizations) {
                for (Organization i: organizations){
                    labels.add(i.getOrganizationCode()+" - "+i.getOrganizationName());
                }
                adapter.notifyDataSetChanged();
            }
        });

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        // On selecting a spinner item
         label = parent.getItemAtPosition(position).toString().substring(0,3);

        // Showing selected spinner item
//        Toast.makeText(parent.getContext(), "You selected: " + label,
//                Toast.LENGTH_LONG).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    protected void onResume() {
        super.onResume();
        try{
            loginViewModel.getData();
            loginViewModel.getorganizationservice();
//            loginViewModel.getheaderservice();



        }catch (Exception e){
            Toast.makeText(this, "Network Problem", Toast.LENGTH_SHORT).show();
        }

    }
}
