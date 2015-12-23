package com.thoughtworks.startup.injection.module;

import android.app.Activity;
import android.content.Context;

import com.thoughtworks.startup.injection.ActivityContext;

import dagger.Module;
import dagger.Provides;

@Module(includes = {AndroidServicesModule.class})
public class ActivityModule {

    private Activity mActivity;

    public ActivityModule(Activity activity) {
        mActivity = activity;
    }

    @Provides
    Activity provideActivity() {
        return mActivity;
    }

    @Provides
    @ActivityContext
    Context providesContext() {
        return mActivity;
    }
}
