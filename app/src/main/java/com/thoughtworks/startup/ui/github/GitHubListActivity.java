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
import com.thoughtworks.startup.ui.base.BaseActivity;

import butterknife.Bind;
import butterknife.ButterKnife;

public class GitHubListActivity extends BaseActivity {

    @Bind(R.id.github_recycle_view)
    RecyclerView mGitHubRecycleView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActivityComponent().inject(this);
        setContentView(R.layout.activity_git_hub_list);
        ButterKnife.bind(this);

        mGitHubRecycleView.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_git_hub_list, menu);
        initSearchMenu(menu);
        return super.onCreateOptionsMenu(menu);
    }

    public void onQuerySubmit(String query) {
        Toast.makeText(GitHubListActivity.this, "query: " + query, Toast.LENGTH_SHORT).show();
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
}
