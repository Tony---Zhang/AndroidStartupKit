package com.thoughtworks.startup.injection.module;

import android.app.Application;
import android.content.Context;

import com.squareup.otto.Bus;
import com.thoughtworks.startup.data.DataManager;
import com.thoughtworks.startup.data.remote.GitHubService;
import com.thoughtworks.startup.data.remote.RibotsService;
import com.thoughtworks.startup.data.remote.SignInService;
import com.thoughtworks.startup.injection.ApplicationContext;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

import static org.mockito.Mockito.mock;

/**
 * Provides application-level dependencies for an app running on a testing environment
 * This allows injecting mocks if necessary.
 */
@Module
public class TestApplicationModule {

    private final Application mApplication;

    public TestApplicationModule(Application application) {
        mApplication = application;
    }

    @Provides
    Application provideApplication() {
        return mApplication;
    }

    @Provides
    @ApplicationContext
    Context provideContext() {
        return mApplication;
    }

    @Provides
    @Singleton
    Bus provideEventBus() {
        return new Bus();
    }

    /*************
     * MOCKS
     *************/

    @Provides
    @Singleton
    DataManager provideDataManager() {
        return mock(DataManager.class);
    }

    @Provides
    @Singleton
    RibotsService provideRibotsService() {
        return mock(RibotsService.class);
    }

    @Provides
    @Singleton
    SignInService provideSignInService() {
        return mock(SignInService.class);
    }

    @Provides
    @Singleton
    GitHubService provideGitHubService() {
        return mock(GitHubService.class);
    }

}
