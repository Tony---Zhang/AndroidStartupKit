package com.thoughtworks.startup.rules;

import android.content.Context;

import com.thoughtworks.startup.StartupApplication;
import com.thoughtworks.startup.data.DataManager;
import com.thoughtworks.startup.injection.component.DaggerTestComponent;
import com.thoughtworks.startup.injection.component.TestComponent;
import com.thoughtworks.startup.injection.module.TestApplicationModule;
import com.thoughtworks.startup.injection.module.TestAndroidServicesModule;

import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;

/**
 * Test rule that creates and sets a Dagger TestComponent into the application overriding the
 * existing application component.
 * Use this rule in your test case in order for the app to use mock dependencies.
 * It also exposes some of the dependencies so they can be easily accessed from the tests, e.g. to
 * stub mocks etc.
 */
public class TestComponentRule implements TestRule {

    private final TestComponent mTestComponent;
    private final Context mContext;

    public TestComponentRule(Context context) {
        mContext = context;
        StartupApplication application = StartupApplication.get(context);
        mTestComponent = DaggerTestComponent.builder()
                .testApplicationModule(new TestApplicationModule(application))
                .testAndroidServicesModule(new TestAndroidServicesModule())
                .build();
    }

    public Context getContext() {
        return mContext;
    }

    public DataManager getMockDataManager() {
        return mTestComponent.dataManager();
    }

    @Override
    public Statement apply(final Statement base, Description description) {
        return new Statement() {
            @Override
            public void evaluate() throws Throwable {
                StartupApplication application = StartupApplication.get(mContext);
                application.setComponent(mTestComponent);
                base.evaluate();
                application.setComponent(null);
            }
        };
    }
}
