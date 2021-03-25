package com.example.gpiproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gpiproject.ViewModel.TestViewModel;
import com.example.gpiproject.model.TestTable;
import com.example.gpiproject.model.TestTableCreation;
import com.example.gpiproject.viewfactory.TestViewFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PostMethod extends AppCompatActivity {
  TestViewModel testViewModel;
  TextView txt;
  EditText clon1,clon2,clon3;
  Button btn_post;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_method);
        testViewModel= ViewModelProviders.of(PostMethod.this,new TestViewFactory(getApplication(),PostMethod.this)).get(TestViewModel.class);
        btn_post=findViewById(R.id.btn_post);
        txt=findViewById(R.id.txt12);
        clon1=findViewById(R.id.clon1);
        clon2=findViewById(R.id.clon2);
        clon3=findViewById(R.id.clon3);

//        testViewModel.getAll().observe(PostMethod.this, new Observer<List<TestTable>>() {
//            @Override
//            public void onChanged(List<TestTable> testTables) {
//                for(TestTable i:testTables){
//                    txt.setText(i.getClOne()+"------->\n"+i.getClTwo());
//                }
//            }
//        });

        btn_post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                TestTable testTable=new TestTable();
//                TestTableCreation creation=new TestTableCreation();
//                List<TestTable> testTableList=new ArrayList<>();
//                testTable.setClOne("no3");
//                testTable.setClTwo("data3");
//                testTable.setClThree("data3");
////            TestViewModel  testViewModel1= ViewModelProviders.of(PostMethod.this).get(TestViewModel.class);
//
////                TestTable test=new TestTable("viki","viki","viki");
////                TestTable testTable= new TestTable("123","321","456");
////                testViewModel.insert(testTable);
//
////                testViewModel.insert(testTable);
//                Toast.makeText(PostMethod.this, "sucess", Toast.LENGTH_SHORT).show();
//                testTableList.add(testTable);
//                creation.setTestTables(testTableList);
//                testViewModel.inserttestPost(creation);
//
                String val1=clon1.getText().toString().trim();
                String val2=clon2.getText().toString().trim();
                String val3=clon3.getText().toString().trim();

                  TestTable testTable= new TestTable();
                  testTable.setClOne(val1+"");
                  testTable.setClTwo(val2);
                  testTable.setClThree(val3);
//
//                try{  testViewModel.insert(testTable);
//                }catch (Exception e){}
//
//
//                  List<TestTable> testTablesList= Arrays.asList(testTable);
//                  testViewModel.inserttestPost(testTablesList);
////

                 List<TestTable> list =testViewModel.getTestTable();

                System.out.println("************************* START");
                if (list != null)
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                        list.forEach(System.out::println);
                    }
                if (list != null)
                    testViewModel.inserttestPost(list);

                System.out.println("************************* END");

                System.out.println("************************* LOT Creation retrive STARTS");





            }
        });


    }
}