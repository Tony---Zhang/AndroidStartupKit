package com.thoughtworks.startup.ui.base;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.thoughtworks.startup.StartupApplication;
import com.thoughtworks.startup.injection.component.ActivityComponent;
import com.thoughtworks.startup.injection.component.DaggerActivityComponent;
import com.thoughtworks.startup.injection.module.ActivityModule;

abstract public class BaseActivity extends AppCompatActivity {

    private ActivityComponent activityComponent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public ActivityComponent getActivityComponent() {
        if (activityComponent == null) {
            activityComponent = DaggerActivityComponent.builder()
                    .activityModule(new ActivityModule(this))
                    .applicationComponent(StartupApplication.get(this).getComponent())
                    .build();
        }
        return activityComponent;
    }

}
