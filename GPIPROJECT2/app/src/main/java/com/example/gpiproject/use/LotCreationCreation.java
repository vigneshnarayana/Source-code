package com.example.gpiproject.use;

import com.example.gpiproject.model.LotCreationModel;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class LotCreationCreation {

    @SerializedName("headerLotCreation")
    @Expose
    private List<LotCreationModel> testLot=null;

    public List<LotCreationModel> getTestLot() {
        return testLot;
    }

    public void setTestLot(List<LotCreationModel> testLot) {
        this.testLot = testLot;
    }
}
