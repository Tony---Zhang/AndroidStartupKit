package com.thoughtworks.startup;

import android.app.Application;
import android.content.Context;

import com.thoughtworks.startup.injection.component.ApplicationComponent;
import com.thoughtworks.startup.injection.component.DaggerApplicationComponent;
import com.thoughtworks.startup.injection.module.ApplicationModule;

import timber.log.Timber;

public class StartupApplication extends Application  {

    ApplicationComponent mApplicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        }
    }

    public static StartupApplication get(Context context) {
        return (StartupApplication) context.getApplicationContext();
    }

    public ApplicationComponent getComponent() {
        if (mApplicationComponent == null) {
            mApplicationComponent = DaggerApplicationComponent.builder()
                    .applicationModule(new ApplicationModule(this))
                    .build();
        }
        return mApplicationComponent;
    }

    // Needed to replace the component with a test specific one
    public void setComponent(ApplicationComponent applicationComponent) {
        mApplicationComponent = applicationComponent;
    }
}
