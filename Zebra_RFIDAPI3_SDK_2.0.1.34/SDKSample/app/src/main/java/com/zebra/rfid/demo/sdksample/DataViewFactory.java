package com.zebra.rfid.demo.sdksample;

import android.app.Activity;
import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.zebra.rfid.demo.sdksample.Viewmodel.DataViewModel;

public class DataViewFactory implements ViewModelProvider.Factory{
    private Application application;
    private Activity activity;

    public DataViewFactory(Application application, Activity activity) {
        this.application = application;
        this.activity = activity;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new DataViewModel(application,activity);
    }
}
