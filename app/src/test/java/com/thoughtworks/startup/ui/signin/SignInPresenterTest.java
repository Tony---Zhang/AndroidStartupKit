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
    SignInView signInView;
    @Mock
    SignInInteractor signInInteractor;

    SignInPresenter signInPresenter;

    @Before
    public void setUp() throws Exception {
        signInPresenter = new SignInPresenter(signInInteractor);
        signInPresenter.attachView(signInView);
    }

    @After
    public void tearDown() throws Exception {
        signInPresenter.detachView();
    }

    @Test
    public void signInWithoutTWEmailShowEmailInvalid() throws Exception {
        String email = "tw@gmail.com";
        String pwd = "password";

        signInPresenter.signIn(email, pwd);

        verify(signInView).showSignInEmailInvalid();
        verify(signInView, never()).showProgress(anyBoolean());
        verify(signInView, never()).showSignInSuccess();
        verify(signInView, never()).showSignInFailed();
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
        }).when(signInInteractor).signIn(anyString(), anyString(), any(SignInInteractor.Callback.class));


        signInPresenter.signIn(email, pwd);

        verify(signInView, times(2)).showProgress(anyBoolean());
        verify(signInView).showSignInSuccess();
        verify(signInView, never()).showSignInFailed();
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
        }).when(signInInteractor).signIn(anyString(), anyString(), any(SignInInteractor.Callback.class));


        signInPresenter.signIn(email, pwd);

        verify(signInView, times(2)).showProgress(anyBoolean());
        verify(signInView).showSignInFailed();
        verify(signInView, never()).showSignInSuccess();
    }

    @Test
    public void signInWithoutEmailShowEmailIsEmpty() throws Exception {
        String pwd = "password";

        signInPresenter.signIn(null, pwd);

        verify(signInView).showEmailIsEmpty();
        verify(signInView, never()).showProgress(anyBoolean());
        verify(signInView, never()).showSignInSuccess();
        verify(signInView, never()).showSignInFailed();
    }

    @Test
    public void signInWithoutPasswordShowPasswordIsEmpty() throws Exception {
        String email = "tw@gmail.com";

        signInPresenter.signIn(email, null);

        verify(signInView).showPasswordIsEmpty();
        verify(signInView, never()).showProgress(anyBoolean());
        verify(signInView, never()).showSignInSuccess();
        verify(signInView, never()).showSignInFailed();
    }
}
