package com.example.gpiproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gpiproject.Dao.DataBase;
import com.example.gpiproject.Dao.TestDao;
import com.example.gpiproject.ViewModel.LoginViewModel;
import com.example.gpiproject.ViewModel.TestViewModel;
import com.example.gpiproject.model.Login;
import com.example.gpiproject.model.LoginServiceModel;
import com.example.gpiproject.model.Organization;
import com.example.gpiproject.model.TestTable;
import com.example.gpiproject.service.LoginService;
import com.example.gpiproject.service.RetrofitClientInstance;
import com.google.gson.Gson;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
   LoginViewModel loginViewModel;
    List<Login> userMasters = new ArrayList<>();

   EditText username,password;
   Button loginbtn,btn_sync;
    String  Username;
    String  Password;
    LoginService loginService;
    SharedPreferences pref;

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        username=findViewById(R.id.et_username);
        password=findViewById(R.id.et_password);
        loginbtn=findViewById(R.id.btn_submit);
        btn_sync=findViewById(R.id.btn_Sync);

        loginService =RetrofitClientInstance.getClient().create(LoginService.class);

//



        loginViewModel= ViewModelProviders.of(this).get(LoginViewModel.class);
        loginViewModel.getoranization();

/////////////////////////////////////////////////////////
//

//////////////////////////////////////////////
        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Username=username.getText().toString().trim();
//                Password=password.getText().toString().trim();
//
//                Login login = new Login();
//
//                if (TextUtils.isEmpty(Username)) {
//                    // activityMainBinding.txtEmailAddress.setError("Please Enter Your E-mail Address");
//                    Toast.makeText(getApplicationContext(), "FILL USERNAME ", Toast.LENGTH_LONG).show();
//                } else if (TextUtils.isEmpty(Password)) {
//                    // activityMainBinding.txtPassword.setError("Please Enter Your Password");
//                    Toast.makeText(getApplicationContext(), "FILL PASSWORD", Toast.LENGTH_LONG).show();
//
//                } else {
//                    Boolean userIn = false, passIn = false, credential = false;
//
//                    if (userMasters != null && userMasters.size() != 0)
//                        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
//                            /*userIn =  userMasters.stream().filter(userMaster -> userMaster.getEmpName().equals(userName)).findFirst().isPresent();
//                             */
//                            credential = userMasters
//                                    .stream()
//                                    .filter(userMaster -> userMaster.getUserName().equals(Username) && userMaster.getPassword().equals(Password))
//                                    .findFirst()
//                                    .isPresent();
//                        }
//                    if (userMasters != null && userMasters.size() != 0)
//                        if (credential == true) {
//                            String empCode = "Empty";
//                            String empType = "Empty";
//                            String fullName = "EmptyUser";
//
//                            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
//                                Optional<Login> matchingObject = userMasters
//                                        .stream()
//                                        .filter(um -> Username.equals(um.getUserName()))
//                                        .findFirst();
//                                Login userMasterSession = matchingObject.get();
//
//
//                            }
//
//                            login.setUserName(Username);
//                            login.setPassword(Password);
//
//
//                            try {
//
//
//                                SharedPreferences.Editor editor = pref.edit();
//                                Gson gson = new Gson();
//                                String json = gson.toJson(login);
//                                editor.putString("login", json);
//
//                                //editor.putString("headerValid", checkHeader.toString());
//                                editor.commit();
//
//                                loginViewModel.insertLoginData(login);
//
//                                Intent intent = new Intent(MainActivity.this, PostMethod.class);
//                                startActivity(intent);
//
//                            } catch (Exception e) {
//                                e.printStackTrace();
//                            }
//
//
//                        }
//                }

                //////////////////////////////////////////

//                Intent a = new Intent(MainActivity.this, PostMethod.class);
//                startActivity(a);
//                finish();
//                TestTable testTable=new TestTable("hi","hi","hi");

//                Username=username.getText().toString().trim();
//                            Password=password.getText().toString().trim();
//
//                 loginViewModel.getAllLoginDetail().observe(MainActivity.this, new Observer<List<Login>>() {
//                    @Override
//                    public void onChanged(List<Login> logins) {
//
//
//                        for (Login h:logins){
//                            Username=username.getText().toString().trim();
//                            Password=password.getText().toString().trim();
//
//                            if( Username.equals(h.getUserName())  ){
//
//                                loginViewModel.getAllLoginDetail().observe(MainActivity.this, new Observer<List<Login>>() {
//                                    @Override
//                                    public void onChanged(List<Login> logins) {
//                                        for(Login i:logins){
//                                            if(Password.equals(i.getPassword()) ){
//
//
//                                                Toast.makeText(MainActivity.this, "Success", Toast.LENGTH_SHORT).show();
//
//                                            }}
//
//                                    }
//
//                                    private void startActivities(Intent intent) {
//                                    }
//                                });
//
//
//                                Toast.makeText(MainActivity.this, "password fail", Toast.LENGTH_SHORT).show();
//                            }else{
//                                Toast.makeText(MainActivity.this, "login Failed", Toast.LENGTH_SHORT).show();
//                            }
//
//
//
//
//
//                        }
//                    }
//                });

//                Username=username.getText().toString().trim();
//                Password=password.getText().toString().trim();
//
////                final Login login =  loginViewModel.getlogin(Username,Password);
//                 new  Thread(new Runnable() {
//                     @Override
//                     public void run() {
//                         if(login !=null){
//                             Toast.makeText(MainActivity.this, "Sucexss", Toast.LENGTH_SHORT).show();
//                         }else {
//                             Toast.makeText(MainActivity.this, "Failed", Toast.LENGTH_SHORT).show();
//                         }
//
//                     }
//                 });




            }
        });


        /////////////////////////////////////////////////////

         btn_sync.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {

      try{

        Call<List<LoginServiceModel>> call=loginService.getData();
        call.enqueue(new Callback<List<LoginServiceModel>>() {
          @Override
           public void onResponse(Call<List<LoginServiceModel>> call, Response<List<LoginServiceModel>> response) {
              Log.e(TAG, "onResponse: " +  response.body());
                List<LoginServiceModel> data=response.body();
                 try{for(LoginServiceModel i: data){

//                    Login login=new Login(i.getUserID(),i.getPassword());
//                    loginViewModel.insert(login);
                }}catch ( Exception e){
                    Toast.makeText(MainActivity.this, "failed", Toast.LENGTH_SHORT).show();
                }


         }

         @Override
         public void onFailure(Call<List<LoginServiceModel>> call, Throwable t) {
             Log.e(TAG, "onFailure: " + t.getLocalizedMessage() );


         }
     });

 }catch (Exception e){
     Toast.makeText(MainActivity.this, "Already Added", Toast.LENGTH_SHORT).show();
 }


             }
         });

    }








}