package com.thoughtworks.startup.ui.signin;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.thoughtworks.startup.R;
import com.thoughtworks.startup.ui.base.BaseActivity;
import com.thoughtworks.startup.ui.github.GitHubListActivity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SignInActivity extends BaseActivity implements SignInView {

    @BindView(R.id.email)
    AutoCompleteTextView mEmailView;
    @BindView(R.id.password)
    EditText mPasswordView;
    @BindView(R.id.login_progress)
    View mProgressView;
    @BindView(R.id.login_form)
    View mLoginFormView;

    @Inject
    SignInPresenter mSignInPresenter;

    private final String[] mDefaultEmails = new String[]{"tw@tw.com", "test@tw.com"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);
        getActivityComponent().inject(this);
        ButterKnife.bind(this);
        mSignInPresenter.attachView(this);

        initViews();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mSignInPresenter.detachView();
    }

    @OnClick(R.id.email_sign_in_button)
    void signIn() {
        final String email = mEmailView.getText().toString();
        final String password = mPasswordView.getText().toString();
        mSignInPresenter.signIn(email, password);
    }

    @Override
    public void showSignInSuccess() {
        Toast.makeText(this, "Success", Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(SignInActivity.this, GitHubListActivity.class);
        startActivity(intent);
    }

    @Override
    public void showSignInFailed() {
        mPasswordView.setError(getString(R.string.error_msg_incorrect_password));
        mPasswordView.requestFocus();
    }

    @Override
    public void showSignInEmailInvalid() {
        mEmailView.setError(getString(R.string.error_msg_invalid_email));
        mEmailView.requestFocus();
    }

    @Override
    public void showEmailIsEmpty() {
        mEmailView.setError(getString(R.string.error_msg_field_required));
        mEmailView.requestFocus();
    }

    @Override
    public void showPasswordIsEmpty() {
        mPasswordView.setError(getString(R.string.error_msg_field_required));
        mPasswordView.requestFocus();
    }

    /**
     * On Honeycomb MR2 we have the ViewPropertyAnimator APIs, which allow
     * for very easy animations. If available, use these APIs to fade-in
     * the progress spinner.
     *
     * The ViewPropertyAnimator APIs are not available below Honeycomb MR2,
     * so simply show and hide the relevant UI components.
     * @param show
     */
    @Override
    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    public void showProgress(final boolean show) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            int shortAnimTime = getResources().getInteger(android.R.integer.config_shortAnimTime);

            mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
            mLoginFormView.animate().setDuration(shortAnimTime).alpha(show ? 0 : 1)
                    .setListener(new AnimatorListenerAdapter() {
                        @Override
                        public void onAnimationEnd(Animator animation) {
                            mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
                        }
                    });

            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            mProgressView.animate().setDuration(shortAnimTime).alpha(show ? 1 : 0)
                    .setListener(new AnimatorListenerAdapter() {
                        @Override
                        public void onAnimationEnd(Animator animation) {
                            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
                        }
                    });
        } else {
            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
        }
    }

    private void initViews() {
        mPasswordView.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent) {
                if (id == R.id.login || id == EditorInfo.IME_NULL) {
                    signIn();
                    return true;
                }
                return false;
            }
        });
        List<String> emails = new ArrayList<>();
        Collections.addAll(emails, mDefaultEmails);
        addEmailsToAutoComplete(emails);
    }

    private void addEmailsToAutoComplete(List<String> emailAddressCollection) {
        ArrayAdapter<String> adapter =
                new ArrayAdapter<>(SignInActivity.this,
                        android.R.layout.simple_dropdown_item_1line, emailAddressCollection);
        mEmailView.setAdapter(adapter);
    }
}
