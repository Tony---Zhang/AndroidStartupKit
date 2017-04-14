package com.thoughtworks.startup.ui.github;

import com.thoughtworks.startup.data.model.GitHubUser;
import com.thoughtworks.startup.ui.base.BasePresenter;

import java.util.List;

import javax.inject.Inject;

public class GitHubPresenter extends BasePresenter<GitHubView> implements GitHubInteractor.Callback {

    private GitHubInteractor gitHubInteractor;

    @Inject
    public GitHubPresenter(GitHubInteractor gitHubInteractor) {
        this.gitHubInteractor = gitHubInteractor;
    }

    public void loadUserList(String keyword) {
        gitHubInteractor.load(keyword, this);
    }

    @Override
    public void onLoadUserListComplete(List<GitHubUser> userList) {
        getMvpView().showList(userList);
    }

    @Override
    public void onLoadUserListFailed() {
        getMvpView().showError();
    }
}
