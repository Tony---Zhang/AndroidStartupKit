package com.thoughtworks.startup.ui.signin;

import com.thoughtworks.startup.ui.base.MvpView;

public interface SignInView extends MvpView {

    void showSignInSuccess();

    void showSignInFailed();

    void showSignInEmailInvalid();

    void showEmailIsEmpty();

    void showPasswordIsEmpty();

    void showProgress(boolean show);
}
