package com.thoughtworks.startup.ui.signin;

import com.thoughtworks.startup.data.model.SignInResponse;
import com.thoughtworks.startup.data.remote.SignInService;

import javax.inject.Inject;

import rx.Subscriber;

import static rx.android.schedulers.AndroidSchedulers.mainThread;
import static rx.schedulers.Schedulers.io;

public class SignInInteractor {

    private SignInService mSignInService;

    public interface Callback {
        void onSignInSuccessful();
        void onSignInFailed(String error);
    }

    @Inject
    public SignInInteractor(SignInService signInService) {
        this.mSignInService = signInService;
    }

    public void signIn(String email, String pwd, final Callback callback) {
        mSignInService.signIn(email, pwd).subscribeOn(io()).observeOn(mainThread()).subscribe(new Subscriber<SignInResponse>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                callback.onSignInFailed(e.getMessage());
            }

            @Override
            public void onNext(SignInResponse signInResponse) {
                if (signInResponse.getCode() ==  SignInService.SUCCESS_CODE) {
                    callback.onSignInSuccessful();
                } else {
                    callback.onSignInFailed(signInResponse.getMsg());
                }
            }
        });
    }
}
