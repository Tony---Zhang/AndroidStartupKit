package com.thoughtworks.startup.ui.signin;

import android.os.Bundle;
import android.widget.Toast;

import com.thoughtworks.startup.R;
import com.thoughtworks.startup.ui.base.BaseActivity;

import javax.inject.Inject;

public class SignInActivity extends BaseActivity implements SignInView {

    @Inject SignInPresenter mSignInPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);

        getActivityComponent().inject(this);

        mSignInPresenter.attachView(this);

        mSignInPresenter.signIn("tw@tw.com", "password");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mSignInPresenter.detachView();
    }

    @Override
    public void showSignInSuccess() {
        Toast.makeText(this, "Success", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showSignInFailed() {
        Toast.makeText(this, "Failed", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showSignInEmailInvalid() {
        Toast.makeText(this, "Invalid", Toast.LENGTH_SHORT).show();
    }
}
