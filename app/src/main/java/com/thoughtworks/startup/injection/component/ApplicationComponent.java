package com.thoughtworks.startup.injection.component;

import android.app.Application;
import android.content.Context;

import com.squareup.otto.Bus;

import javax.inject.Singleton;

import dagger.Component;
import com.thoughtworks.startup.data.DataManager;
import com.thoughtworks.startup.data.SyncService;
import com.thoughtworks.startup.data.local.DatabaseHelper;
import com.thoughtworks.startup.data.local.PreferencesHelper;
import com.thoughtworks.startup.data.remote.GitHubService;
import com.thoughtworks.startup.data.remote.RibotsService;
import com.thoughtworks.startup.data.remote.SignInService;
import com.thoughtworks.startup.injection.ApplicationContext;
import com.thoughtworks.startup.injection.module.ApplicationModule;

@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {

    void inject(SyncService syncService);

    @ApplicationContext
    Context context();
    Application application();
    RibotsService ribotsService();
    SignInService signInService();
    GitHubService gitHubService();
    PreferencesHelper preferencesHelper();
    DatabaseHelper databaseHelper();
    DataManager dataManager();
    Bus eventBus();

}
