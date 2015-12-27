package com.thoughtworks.startup.ui.signin;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class SignInPresenterTest {

    @Mock
    SignInView mSignInView;
    @Mock
    SignInInteractor mSignInInteractor;

    SignInPresenter mSignInPresenter;

    @Before
    public void setUp() throws Exception {
        mSignInPresenter = new SignInPresenter(mSignInInteractor);
        mSignInPresenter.attachView(mSignInView);
    }

    @After
    public void tearDown() throws Exception {
        mSignInPresenter.detachView();
    }

    @Test
    public void signInWithoutTWEmailShowEmailInvalid() throws Exception {
        String email = "tw@gmail.com";
        String pwd = "password";

        mSignInPresenter.signIn(email, pwd);

        verify(mSignInView).showSignInEmailInvalid();
        verify(mSignInView, never()).showSignInSuccess();
        verify(mSignInView, never()).showSignInFailed();
    }

    @Test
    public void signInWithTWEmailReturnSuccessful() throws Exception {
        String email = "tw@tw.com";
        String pwd = "password";

        mSignInPresenter.signIn(email, pwd);

        verify(mSignInView).showSignInSuccess();
        verify(mSignInView, never()).showSignInFailed();
    }
}
