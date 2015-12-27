package com.thoughtworks.startup.ui.github;

import com.thoughtworks.startup.data.model.GitHubUser;
import com.thoughtworks.startup.ui.base.MvpView;

import java.util.List;

public interface GitHubView extends MvpView {

    void showList(List<GitHubUser> userList);

    void showError();
}
