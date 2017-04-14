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
    AutoCompleteTextView emailView;
    @BindView(R.id.password)
    EditText passwordView;
    @BindView(R.id.login_progress)
    View progressView;
    @BindView(R.id.login_form)
    View loginFormView;

    @Inject
    SignInPresenter signInPresenter;

    private final String[] defaultEmails = new String[]{"tw@tw.com", "test@tw.com"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);
        getActivityComponent().inject(this);
        ButterKnife.bind(this);
        signInPresenter.attachView(this);

        initViews();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        signInPresenter.detachView();
    }

    @OnClick(R.id.email_sign_in_button)
    void signIn() {
        final String email = emailView.getText().toString();
        final String password = passwordView.getText().toString();
        signInPresenter.signIn(email, password);
    }

    @Override
    public void showSignInSuccess() {
        Toast.makeText(this, "Success", Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(SignInActivity.this, GitHubListActivity.class);
        startActivity(intent);
    }

    @Override
    public void showSignInFailed() {
        passwordView.setError(getString(R.string.error_msg_incorrect_password));
        passwordView.requestFocus();
    }

    @Override
    public void showSignInEmailInvalid() {
        emailView.setError(getString(R.string.error_msg_invalid_email));
        emailView.requestFocus();
    }

    @Override
    public void showEmailIsEmpty() {
        emailView.setError(getString(R.string.error_msg_field_required));
        emailView.requestFocus();
    }

    @Override
    public void showPasswordIsEmpty() {
        passwordView.setError(getString(R.string.error_msg_field_required));
        passwordView.requestFocus();
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

            loginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
            loginFormView.animate().setDuration(shortAnimTime).alpha(show ? 0 : 1)
                    .setListener(new AnimatorListenerAdapter() {
                        @Override
                        public void onAnimationEnd(Animator animation) {
                            loginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
                        }
                    });

            progressView.setVisibility(show ? View.VISIBLE : View.GONE);
            progressView.animate().setDuration(shortAnimTime).alpha(show ? 1 : 0)
                    .setListener(new AnimatorListenerAdapter() {
                        @Override
                        public void onAnimationEnd(Animator animation) {
                            progressView.setVisibility(show ? View.VISIBLE : View.GONE);
                        }
                    });
        } else {
            progressView.setVisibility(show ? View.VISIBLE : View.GONE);
            loginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
        }
    }

    private void initViews() {
        passwordView.setOnEditorActionListener(new TextView.OnEditorActionListener() {
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
        Collections.addAll(emails, defaultEmails);
        addEmailsToAutoComplete(emails);
    }

    private void addEmailsToAutoComplete(List<String> emailAddressCollection) {
        ArrayAdapter<String> adapter =
                new ArrayAdapter<>(SignInActivity.this,
                        android.R.layout.simple_dropdown_item_1line, emailAddressCollection);
        emailView.setAdapter(adapter);
    }
}
