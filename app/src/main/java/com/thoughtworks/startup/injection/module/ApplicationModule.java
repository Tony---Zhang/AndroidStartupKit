package com.thoughtworks.startup.injection.module;

import android.app.Application;
import android.content.Context;

import com.squareup.otto.Bus;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

import com.thoughtworks.startup.data.remote.GitHubService;
import com.thoughtworks.startup.data.remote.RibotsService;
import com.thoughtworks.startup.data.remote.SignInService;
import com.thoughtworks.startup.injection.ApplicationContext;

/**
 * Provide application-level dependencies.
 */
@Module
public class ApplicationModule {
    protected final Application mApplication;

    public ApplicationModule(Application application) {
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

    @Provides
    @Singleton
    RibotsService provideRibotsService() {
        return RibotsService.Creator.newRibotsService();
    }

    @Provides
    @Singleton
    SignInService provideSignInService() {
        return SignInService.Creator.newSignInService();
    }

    @Provides
    @Singleton
    GitHubService provideGitHubService() {
        return GitHubService.Creator.newGitHubService();
    }
}
