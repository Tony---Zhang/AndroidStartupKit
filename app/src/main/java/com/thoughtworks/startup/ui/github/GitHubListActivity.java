package com.thoughtworks.startup.ui.github;

import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.thoughtworks.startup.R;
import com.thoughtworks.startup.data.model.GitHubUser;
import com.thoughtworks.startup.ui.base.BaseActivity;
import com.thoughtworks.startup.util.DialogFactory;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class GitHubListActivity extends BaseActivity implements GitHubView {

    @BindView(R.id.github_recycle_view)
    RecyclerView mGitHubRecycleView;

    @Inject
    GitHubPresenter mGitHubPresenter;

    private GitHubAdapter mGitHubAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActivityComponent().inject(this);
        setContentView(R.layout.activity_git_hub_list);
        ButterKnife.bind(this);

        mGitHubPresenter.attachView(this);
        initViews();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mGitHubPresenter.detachView();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_git_hub_list, menu);
        initSearchMenu(menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public void showList(List<GitHubUser> userList) {
        mGitHubAdapter.setUserList(userList);
        mGitHubAdapter.notifyDataSetChanged();
        if (userList.isEmpty()) {
            Toast.makeText(this, R.string.error_msg_empty_github_user, Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void showError() {
        DialogFactory.createGenericErrorDialog(this, getString(R.string.error_msg_loading_github_user))
                .show();
    }

    public void onQuerySubmit(String query) {
        Toast.makeText(GitHubListActivity.this, "query: " + query, Toast.LENGTH_SHORT).show();
        mGitHubPresenter.loadUserList(query);
    }

    private void initSearchMenu(Menu menu) {
        MenuItem searchItem = menu.findItem(R.id.action_search);

        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);

        SearchView searchView = null;
        if (searchItem != null) {
            searchView = (SearchView) searchItem.getActionView();
        }
        if (searchView != null) {
            searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
            searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String query) {
                    onQuerySubmit(query);
                    return true;
                }

                @Override
                public boolean onQueryTextChange(String newText) {
                    return false;
                }
            });
        }
    }

    private void initViews() {
        mGitHubAdapter = new GitHubAdapter(this);
        mGitHubRecycleView.setLayoutManager(new LinearLayoutManager(this));
        mGitHubRecycleView.setAdapter(mGitHubAdapter);
    }
}
