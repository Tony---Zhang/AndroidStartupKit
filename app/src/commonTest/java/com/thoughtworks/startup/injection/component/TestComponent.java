package com.thoughtworks.startup.injection.component;

import com.thoughtworks.startup.injection.module.TestApplicationModule;
import com.thoughtworks.startup.injection.module.TestAndroidServicesModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {TestApplicationModule.class, TestAndroidServicesModule.class})
public interface TestComponent extends ApplicationComponent {

}
