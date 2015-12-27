package com.thoughtworks.startup.injection.component;

import com.thoughtworks.startup.injection.PerActivity;
import com.thoughtworks.startup.injection.module.ActivityModule;
import com.thoughtworks.startup.ui.github.GitHubListActivity;
import com.thoughtworks.startup.ui.main.MainActivity;
import com.thoughtworks.startup.ui.signin.SignInActivity;

import dagger.Component;

/**
 * This component inject dependencies to all Activities across the application
 */
@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {

    void inject(MainActivity mainActivity);

    void inject(SignInActivity signInActivity);

    void inject(GitHubListActivity gitHubListActivity);

}
