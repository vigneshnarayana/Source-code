package com.example.gpiproject.use;

import android.app.Activity;
import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.gpiproject.Viewmodel.WeighmentViewModel;

public class WeighmentViewFactory implements ViewModelProvider.Factory {
    private Application application;
    private Activity activity;

    public WeighmentViewFactory(Application application, Activity activity) {
        this.application = application;
        this.activity = activity;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new WeighmentViewModel(application,activity);
    }
}
