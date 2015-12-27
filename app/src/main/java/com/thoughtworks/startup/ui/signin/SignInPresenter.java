package com.thoughtworks.startup.ui.signin;

import com.thoughtworks.startup.ui.base.BasePresenter;

import javax.inject.Inject;

import static com.thoughtworks.startup.util.RegexUtil.isThoughtWorkEmail;


public class SignInPresenter extends BasePresenter<SignInView> implements SignInInteractor.Callback {

    private SignInInteractor mSignInInteractor;

    @Inject
    public SignInPresenter(SignInInteractor signInInteractor) {
        this.mSignInInteractor = signInInteractor;
    }

    public void signIn(String email, String pwd) {
        if (!isThoughtWorkEmail(email)) {
            getMvpView().showSignInEmailInvalid();
            return;
        }
        mSignInInteractor.signIn(email, pwd, this);
    }

    public void onSignInSuccessful() {
        getMvpView().showSignInSuccess();
    }

    public void onSignInFailed(String error) {
        getMvpView().showSignInFailed();
    }
}
