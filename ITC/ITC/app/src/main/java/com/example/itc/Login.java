package com.example.itc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        EditText username = (EditText)findViewById(R.id.userName);
        EditText password = (EditText)findViewById(R.id.password);

        Button Login = (Button) findViewById(R.id.login);


        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(username.getText().toString().equals("") && password.getText().toString().equals("")){

                    Intent intent = new Intent(Login.this, Base.class);
                    startActivity(intent);

                    //correcct password
                }else{
                    //wrong password
                }

            }
        });



    }
}