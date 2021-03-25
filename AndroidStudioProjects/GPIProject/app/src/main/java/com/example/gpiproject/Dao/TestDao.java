package com.example.gpiproject.Dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.gpiproject.model.Organization;
import com.example.gpiproject.model.TestTable;

import java.util.List;

@Dao
public interface TestDao {
    @Insert
    void  inserttest(TestTable testTable);

    @Query("SELECT * FROM testtable")
    LiveData<List<TestTable>> getAll();


    @Query("select * from testtable")
    public  List<TestTable> gettableData();



}
