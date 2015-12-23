package com.thoughtworks.startup.injection.module;


import android.content.Context;
import android.content.pm.PackageManager;
import android.location.LocationManager;
import android.support.v4.content.LocalBroadcastManager;
import android.view.inputmethod.InputMethodManager;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

import static org.mockito.Mockito.mock;


@Module
public class TestAndroidServicesModule {

    @Provides
    @Singleton
    public InputMethodManager provideInputMethodManager(Context context) {
        return mock(InputMethodManager.class);
    }

    @Provides
    @Singleton
    PackageManager providePackageManager(Context context) {
        return mock(PackageManager.class);
    }

    @Provides
    @Singleton
    LocationManager provideLocationManager(Context context) {
        return mock(LocationManager.class);
    }

    @Provides
    @Singleton
    LocalBroadcastManager provideLocalBroadcastManager(Context context) {
        return mock(LocalBroadcastManager.class);
    }

}
