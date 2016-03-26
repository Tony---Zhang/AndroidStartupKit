package com.thoughtworks.startup;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.thoughtworks.startup.ui.signin.SignInActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.closeSoftKeyboard;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.clearText;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.RootMatchers.withDecorView;
import static android.support.test.espresso.matcher.ViewMatchers.hasErrorText;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;

@RunWith(AndroidJUnit4.class)
public class SignInActivityTest {


    @Rule
    public ActivityTestRule<SignInActivity> activityTestRule = new ActivityTestRule<>(SignInActivity.class);

    @Test
    public void shouldSigninSuccessfulWithValidCredential() throws Exception {
        String userName = "tw@tw.com";
        String password = "password";
        onView(withId(R.id.email)).perform(clearText()).perform(typeText(userName));
        onView(withText(userName)).perform(click());
        onView(withId(R.id.password)).perform(clearText()).perform(typeText(password));
        closeSoftKeyboard();

        onView(withId(R.id.email_sign_in_button)).perform(click());
    }

    @Test
    public void shouldSigninFailedWithInvalidCredential() throws Exception {
        String userName = "tw@gmail.com";
        String password = "password";
        onView(withId(R.id.email)).perform(clearText()).perform(typeText(userName));
        onView(withText(userName)).perform(click());
        onView(withId(R.id.password)).perform(clearText()).perform(typeText(password));
        closeSoftKeyboard();

        onView(withId(R.id.email_sign_in_button)).perform(click());

        onView(withId(R.id.email)).check(matches(hasErrorText("This email address is invalid")));
    }

    @Test
    public void shouldShowRequireEmailWithoutEmail() throws Exception {
        String password = "password";
        onView(withId(R.id.password)).perform(clearText()).perform(typeText(password));
        closeSoftKeyboard();

        onView(withId(R.id.email_sign_in_button)).perform(click());

        onView(withId(R.id.email)).check(matches(hasErrorText("This field is required")));
    }

    @Test
    public void shouldShowRequirePasswordWithoutPassword() throws Exception {
        String userName = "tw@tw.com";
        onView(withId(R.id.email)).perform(clearText()).perform(typeText(userName));
        onView(withText(userName)).perform(click());
        closeSoftKeyboard();

        onView(withId(R.id.email_sign_in_button)).perform(click());

        onView(withId(R.id.password)).check(matches(hasErrorText("This field is required")));
    }

    @Test
    public void shouldShowAutoSuggestionListWhenInputMoreThanTwoWordsInEmail() throws Exception {
        String userName = "tw";

        onView(withId(R.id.email)).perform(clearText()).perform(typeText(userName));

        onView(withText("tw@tw.com")).inRoot(withDecorView(not(is(activityTestRule.getActivity().getWindow().getDecorView())))).check(matches(isDisplayed()));
    }
}
