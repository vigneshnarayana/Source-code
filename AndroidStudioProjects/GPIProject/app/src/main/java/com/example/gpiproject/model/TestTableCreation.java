package com.example.gpiproject.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class TestTableCreation {

    @SerializedName("testTable")
    @Expose
    private List<TestTable> testTables=null;

    public List<TestTable> getTestTables() {
        return testTables;
    }

    public void setTestTables(List<TestTable> testTables) {
        this.testTables = testTables;
    }
}
