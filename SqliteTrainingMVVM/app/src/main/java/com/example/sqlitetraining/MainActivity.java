package com.example.sqlitetraining;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.sqlitetraining.Model.SqliteModel;
import com.example.sqlitetraining.ViewModel.SqliteViewModel;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    DatabaseHelper myDB;
    EditText name, surname, marks, id;
    Button btnAdd, btnView, btnUpdate, btnDelete;

    private SqliteViewModel sqliteViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myDB = new DatabaseHelper(this);

        name = findViewById(R.id.et_name);
        surname = findViewById(R.id.et_surname);
        marks = findViewById(R.id.et_marks);
        btnAdd = findViewById(R.id.btn_add);
        btnView = findViewById(R.id.btn_view);
        id = findViewById(R.id.et_id);
        btnUpdate = findViewById(R.id.btn_update);
        btnDelete = findViewById(R.id.btn_delete);

        sqliteViewModel = ViewModelProviders.of(this).get(SqliteViewModel.class);


        viewAll();
        update();
        DeleteData();
        addData();

        sqliteViewModel.getAllStudent().observe(MainActivity.this, new Observer<List<SqliteModel>>() {
            @Override
            public void onChanged (List<SqliteModel> sqliteModels) {
                btnView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {


                        StringBuffer buffer = new StringBuffer();
                        for(SqliteModel values: sqliteModels){
                            buffer.append("Id :" + values.getId() + "\n");
                            buffer.append("Name :" + values.getName() + "\n");
                            buffer.append("Surname :" + values.getSurname() + "\n");
                            buffer.append("Marks :" + values.getMarks() + "\n\n");

                        }
                        showMessage("Data", buffer.toString());

                    }
                });


            }
        });

    }


    public void showMessage(String title, String message) {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }

    public void clearText() {
        id.setText("");
        name.setText("");
        surname.setText("");
        marks.setText("");
    }

    public void DeleteData() {

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try{ String Id= id.getText().toString().trim();

                    Integer i= Integer.parseInt(Id);

                    SqliteModel sqliteModel= new SqliteModel(i,null,null,null);
                    sqliteViewModel.delete(sqliteModel);
                     Toast.makeText(MainActivity.this, " Delete Succesfuly", Toast.LENGTH_SHORT).show();

                }catch (Exception e){
                     Toast.makeText(MainActivity.this, " Delete to failed", Toast.LENGTH_SHORT).show();

                }



            }
        });

    }

    public void update() {

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try{
                    String Id= id.getText().toString().trim();
                    String Name = name.getText().toString().trim();
                    String surName = surname.getText().toString().trim();
                    String Mark = marks.getText().toString().trim();

                    Integer i= Integer.parseInt(Id);

                    SqliteModel sqliteModel=new SqliteModel(i,Name,surName,Mark);
                    sqliteViewModel.update(sqliteModel);
                    Toast.makeText(MainActivity.this, " Updated Succesfuly", Toast.LENGTH_SHORT).show();

                }catch (Exception e){
                    Toast.makeText(MainActivity.this, " Update to failed", Toast.LENGTH_SHORT).show();

                }






            }
        });

    }

    private void viewAll() {


    }

    public void addData() {

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {
                    String Name = name.getText().toString().trim();
                    String surName = surname.getText().toString().trim();
                    String Mark = marks.getText().toString().trim();

                    SqliteModel sqliteModel = new SqliteModel(null,Name, surName, Mark);

                    sqliteViewModel.insert(sqliteModel);
                    Toast.makeText(MainActivity.this, "Data Inserted Succesfuly", Toast.LENGTH_SHORT).show();

                } catch (Exception e) {
                    Toast.makeText(MainActivity.this, " Insert to failed", Toast.LENGTH_SHORT).show();

                }


            }

        });
    }

}