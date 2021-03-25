package com.example.gpiproject.use;

import com.example.gpiproject.model.LotCreationModel;
import com.example.gpiproject.model.PurchaseModel;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PurchaseCreation {
    @SerializedName("FarmerPurchasePost")
    @Expose
    private List<PurchaseModel> testLot=null;

    public List<PurchaseModel> getTestLot() {
        return testLot;
    }

    public void setTestLot(List<PurchaseModel> testLot) {
        this.testLot = testLot;
    }
}
