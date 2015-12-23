package com.thoughtworks.startup;

import com.thoughtworks.startup.util.DefaultConfig;

import org.junit.runner.RunWith;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;

@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class, packageName = DefaultConfig.APPLICATION_ID, sdk = DefaultConfig.EMULATE_SDK)
abstract public class ApplicationTestCase {
}