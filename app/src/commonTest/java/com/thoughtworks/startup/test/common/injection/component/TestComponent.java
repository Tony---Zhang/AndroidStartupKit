package com.thoughtworks.startup.test.common.injection.component;

import com.thoughtworks.startup.injection.component.ApplicationComponent;
import com.thoughtworks.startup.test.common.injection.module.ApplicationTestModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = ApplicationTestModule.class)
public interface TestComponent extends ApplicationComponent {

}
