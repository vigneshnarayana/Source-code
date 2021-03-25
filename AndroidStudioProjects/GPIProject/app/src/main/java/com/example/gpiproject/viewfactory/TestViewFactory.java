package com.example.gpiproject.viewfactory;

import android.app.Activity;
import android.app.Application;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.gpiproject.ViewModel.TestViewModel;

public class TestViewFactory implements ViewModelProvider.Factory {

   private Application application;
   private Activity activity;

    public TestViewFactory(Application application, Activity activity) {
        this.application = application;
        this.activity = activity;
    }


  @Override
    public <T  extends ViewModel> T create (Class<T> modelClass){
        return  (T) new TestViewModel(application,activity);
  }
}
