package com.example.gpiproject.ViewModel;

import android.app.Activity;
import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.gpiproject.Repository.TestRepository;
import com.example.gpiproject.model.TestTable;
import com.example.gpiproject.model.TestTableCreation;

import java.util.List;

public class TestViewModel extends AndroidViewModel {
   private TestRepository testRepository;
    private LiveData<List<TestTable>> getAll;
    List<TestTable> testTables;
    Activity activity;
    public TestViewModel(@NonNull Application application,Activity activity) {
        super(application);
        this.activity=activity;
        testRepository= new TestRepository(application,activity);
        getAll=testRepository.getAll();



    }
    public  LiveData<List<TestTable>> getAll(){
        return getAll;
    }

    public  void inserttestPost(TestTableCreation testTablecreation){
        testRepository.SendTestPost(testTablecreation.getTestTables());
    }
    public  void insert(TestTable testTable){
        testRepository.insertTest(testTable);
    }

    public  void inserttestPost(List<TestTable> testTables){
        testRepository.SendTestPost(testTables);
    }
    public List<TestTable> getTestTable(){
        return testRepository.getTabletata();
    }

}
