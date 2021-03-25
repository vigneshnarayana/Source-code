package com.example.tvs.activity.activitys;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProviders;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.example.tvs.R;
import com.example.tvs.activity.Home;
import com.example.tvs.activity.Model.Login;
import com.example.tvs.activity.Model.User;
import com.example.tvs.activity.ViewModel.UserViewModel;

import java.util.ArrayList;
import java.util.List;

public class LoginActivity extends AppCompatActivity {


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
        setContentView(R.layout.activity_login);

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("MDC SCL");
        toolbar.setSubtitle("Manufacturing Data Collection");
        setSupportActionBar(toolbar);

//        mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipeToRefresh);
//        mSwipeRefreshLayout.setColorSchemeResources(R.color.design_default_color_error);

        et_password = findViewById(R.id.et_password);
        et_username = findViewById(R.id.et_username);

        btn_submit=findViewById(R.id.btn_submit);


        userViewModel = ViewModelProviders.of(this).get(UserViewModel.class);

        userViewModel.getUserService();
        userViewModel.getLocation();

        sp=getSharedPreferences("data", Context.MODE_PRIVATE);

        loginMasters = userViewModel.getLoginDetailList();

    }
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

                            Intent intent= new Intent(LoginActivity.this,Home.class);

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

    public void UserDetails(View view) {

        Toast.makeText(this, "Update Will Soon", Toast.LENGTH_SHORT).show();
    }
}