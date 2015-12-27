package com.thoughtworks.startup.ui.signin;

import com.thoughtworks.startup.ui.base.BasePresenter;

import javax.inject.Inject;

import static com.google.common.base.Strings.isNullOrEmpty;
import static com.thoughtworks.startup.util.RegexUtil.isThoughtWorkEmail;


public class SignInPresenter extends BasePresenter<SignInView> implements SignInInteractor.Callback {

    private SignInInteractor mSignInInteractor;

    @Inject
    public SignInPresenter(SignInInteractor signInInteractor) {
        this.mSignInInteractor = signInInteractor;
    }

    public void signIn(String email, String password) {
        if (isNullOrEmpty(email)) {
            getMvpView().showEmailIsEmpty();
            return;
        }
        if (isNullOrEmpty(password)) {
            getMvpView().showPasswordIsEmpty();
            return;
        }
        if (!isThoughtWorkEmail(email)) {
            getMvpView().showSignInEmailInvalid();
            return;
        }
        getMvpView().showProgress(true);
        mSignInInteractor.signIn(email, password, this);
    }

    @Override
    public void onSignInSuccessful() {
        getMvpView().showProgress(false);
        getMvpView().showSignInSuccess();
    }

    @Override
    public void onSignInFailed(String error) {
        getMvpView().showProgress(false);
        getMvpView().showSignInFailed();
    }
}
