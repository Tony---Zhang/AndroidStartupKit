package com.thoughtworks.startup.data.local;

import android.content.Context;
import android.content.SharedPreferences;

import com.thoughtworks.startup.injection.ApplicationContext;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class PreferencesHelper {

    public static final String PREF_FILE_NAME = "android_startup_pref_file";

    private final SharedPreferences pref;

    @Inject
    public PreferencesHelper(@ApplicationContext Context context) {
        pref = context.getSharedPreferences(PREF_FILE_NAME, Context.MODE_PRIVATE);
    }

    public void clear() {
        pref.edit().clear().apply();
    }

}
