package com.thoughtworks.startup.injection.module;

import android.app.Activity;
import android.content.Context;

import com.thoughtworks.startup.injection.ActivityContext;

import dagger.Module;
import dagger.Provides;

@Module(includes = {AndroidServicesModule.class})
public class ActivityModule {

    private Activity activity;

    public ActivityModule(Activity activity) {
        this.activity = activity;
    }

    @Provides
    Activity provideActivity() {
        return activity;
    }

    @Provides
    @ActivityContext
    Context providesContext() {
        return activity;
    }
}
