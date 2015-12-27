package com.thoughtworks.startup.injection.module;

import android.accounts.AccountManager;
import android.app.ActivityManager;
import android.app.AlarmManager;
import android.app.SearchManager;
import android.content.ContentResolver;
import android.content.Context;
import android.content.pm.PackageManager;
import android.hardware.SensorManager;
import android.location.LocationManager;
import android.media.AudioManager;
import android.net.ConnectivityManager;
import android.net.wifi.WifiManager;
import android.nfc.NfcManager;
import android.os.PowerManager;
import android.support.v4.content.LocalBroadcastManager;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;


@Module
public class AndroidServicesModule {

    @Provides
    @Singleton
    NfcManager provideNfcManager(Context context) {
        return (NfcManager) context.getSystemService(Context.NFC_SERVICE);
    }

    @Provides
    @Singleton
    ConnectivityManager provideConnectivityManager(Context context) {
        return (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
    }

    @Provides
    @Singleton
    WifiManager provideWifiManager(Context context) {
        return (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
    }

    @Provides
    @Singleton
    AlarmManager provideAlarmManager(Context context) {
        return (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
    }

    @Provides
    @Singleton
    WindowManager provideWindowManager(Context context) {
        return (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
    }

    @Provides
    @Singleton
    PowerManager providePowerManager(Context context) {
        return (PowerManager) context.getSystemService(Context.POWER_SERVICE);
    }

    @Provides
    @Singleton
    PackageManager providePackageManager(Context context) {
        return context.getPackageManager();
    }

    @Provides
    @Singleton
    ContentResolver provideContentResolver(Context context) {
        return context.getContentResolver();
    }

    @Provides
    @Singleton
    AudioManager provideAudioManager(Context context) {
        return (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
    }

    @Provides
    @Singleton
    InputMethodManager provideInputMethodManager(Context context) {
        return (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
    }

    @Provides
    @Singleton
    LocationManager provideLocationManager(Context context) {
        return (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
    }

    @Provides
    @Singleton
    SensorManager provideSensorManager(Context context) {
        return (SensorManager) context.getSystemService(Context.SENSOR_SERVICE);
    }

    @Provides
    @Singleton
    LocalBroadcastManager provideLocalBroadcastManager(Context context) {
        return LocalBroadcastManager.getInstance(context);
    }

    @Provides
    @Singleton
    ActivityManager provideActivityManager(Context context) {
        return (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
    }

    @Provides
    @Singleton
    AccountManager provideAccountManager(Context context) {
        return AccountManager.get(context);
    }

    @Provides
    @Singleton
    SearchManager provideSearchManager(Context context) {
        return (SearchManager) context.getSystemService(Context.SEARCH_SERVICE);
    }
}
