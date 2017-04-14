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

    @Mock GitHubView mockGitHubView;
    @Mock GitHubInteractor mockGitHubInteractor;

    GitHubPresenter gitHubPresenter;

    @Before
    public void setUp() throws Exception {
        gitHubPresenter = new GitHubPresenter(mockGitHubInteractor);
        gitHubPresenter.attachView(mockGitHubView);
    }

    @After
    public void tearDown() throws Exception {
        gitHubPresenter.detachView();
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
        }).when(mockGitHubInteractor).load(anyString(), any(GitHubInteractor.Callback.class));

        gitHubPresenter.loadUserList("keyword");

        verify(mockGitHubView).showList(anyListOf(GitHubUser.class));
        verify(mockGitHubView, never()).showError();
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
        }).when(mockGitHubInteractor).load(anyString(), any(GitHubInteractor.Callback.class));

        gitHubPresenter.loadUserList("keyword");

        verify(mockGitHubView).showError();
        verify(mockGitHubView, never()).showList(anyListOf(GitHubUser.class));
    }
}