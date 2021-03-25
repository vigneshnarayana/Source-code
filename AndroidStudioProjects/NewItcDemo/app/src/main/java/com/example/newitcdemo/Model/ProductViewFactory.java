package com.example.newitcdemo.Model;

import android.app.Activity;
import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.newitcdemo.ViewModel.ProductViewModel;

public class ProductViewFactory implements ViewModelProvider.Factory {
    private Application application;
    private Activity activity ;

    public ProductViewFactory(Application application, Activity activity) {
        this.application = application;
        this.activity = activity;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new ProductViewModel(application,activity);
    }
}
