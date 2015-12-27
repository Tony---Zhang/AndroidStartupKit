package com.thoughtworks.startup.ui.signin;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.runners.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyBoolean;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.internal.verification.VerificationModeFactory.times;

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
        verify(mSignInView, never()).showProgress(anyBoolean());
        verify(mSignInView, never()).showSignInSuccess();
        verify(mSignInView, never()).showSignInFailed();
    }

    @Test
    public void signInWithTWEmailReturnSuccessful() throws Exception {
        String email = "tw@tw.com";
        String pwd = "password";
        doAnswer(new Answer() {
            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable {
                SignInInteractor.Callback callback = (SignInInteractor.Callback) invocation.getArguments()[2];
                callback.onSignInSuccessful();
                return null;
            }
        }).when(mSignInInteractor).signIn(anyString(), anyString(), any(SignInInteractor.Callback.class));


        mSignInPresenter.signIn(email, pwd);

        verify(mSignInView, times(2)).showProgress(anyBoolean());
        verify(mSignInView).showSignInSuccess();
        verify(mSignInView, never()).showSignInFailed();
    }

    @Test
    public void signInWithTWEmailReturnFailed() throws Exception {
        String email = "tw@tw.com";
        String pwd = "wrong";
        doAnswer(new Answer() {
            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable {
                SignInInteractor.Callback callback = (SignInInteractor.Callback) invocation.getArguments()[2];
                callback.onSignInFailed("error");
                return null;
            }
        }).when(mSignInInteractor).signIn(anyString(), anyString(), any(SignInInteractor.Callback.class));


        mSignInPresenter.signIn(email, pwd);

        verify(mSignInView, times(2)).showProgress(anyBoolean());
        verify(mSignInView).showSignInFailed();
        verify(mSignInView, never()).showSignInSuccess();
    }

    @Test
    public void signInWithoutEmailShowEmailIsEmpty() throws Exception {
        String pwd = "password";

        mSignInPresenter.signIn(null, pwd);

        verify(mSignInView).showEmailIsEmpty();
        verify(mSignInView, never()).showProgress(anyBoolean());
        verify(mSignInView, never()).showSignInSuccess();
        verify(mSignInView, never()).showSignInFailed();
    }

    @Test
    public void signInWithoutPasswordShowPasswordIsEmpty() throws Exception {
        String email = "tw@gmail.com";

        mSignInPresenter.signIn(email, null);

        verify(mSignInView).showPasswordIsEmpty();
        verify(mSignInView, never()).showProgress(anyBoolean());
        verify(mSignInView, never()).showSignInSuccess();
        verify(mSignInView, never()).showSignInFailed();
    }
}
