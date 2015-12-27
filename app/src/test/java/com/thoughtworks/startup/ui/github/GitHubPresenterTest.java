package com.thoughtworks.startup.ui.github;


import com.thoughtworks.startup.data.model.GitHubUser;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.runners.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyListOf;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class GitHubPresenterTest {

    @Mock GitHubView mMockGitHubView;
    @Mock GitHubInteractor mMockGitHubInteractor;

    GitHubPresenter mGitHubPresenter;

    @Before
    public void setUp() throws Exception {
        mGitHubPresenter = new GitHubPresenter(mMockGitHubInteractor);
        mGitHubPresenter.attachView(mMockGitHubView);
    }

    @After
    public void tearDown() throws Exception {
        mGitHubPresenter.detachView();
    }

    @Test
    public void loadGitHubUserListReturnUsers() throws Exception {
        doAnswer(new Answer() {
            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable {
                GitHubInteractor.Callback callback = (GitHubInteractor.Callback) invocation.getArguments()[1];
                callback.onLoadUserListComplete(anyListOf(GitHubUser.class));
                return null;
            }
        }).when(mMockGitHubInteractor).load(anyString(), any(GitHubInteractor.Callback.class));

        mGitHubPresenter.loadUserList("keyword");

        verify(mMockGitHubView).showList(anyListOf(GitHubUser.class));
        verify(mMockGitHubView, never()).showError();
    }

    @Test
    public void loadGitHubUserListFailed() throws Exception {
        doAnswer(new Answer() {
            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable {
                GitHubInteractor.Callback callback = (GitHubInteractor.Callback) invocation.getArguments()[1];
                callback.onLoadUserListFailed();
                return null;
            }
        }).when(mMockGitHubInteractor).load(anyString(), any(GitHubInteractor.Callback.class));

        mGitHubPresenter.loadUserList("keyword");

        verify(mMockGitHubView).showError();
        verify(mMockGitHubView, never()).showList(anyListOf(GitHubUser.class));
    }
}