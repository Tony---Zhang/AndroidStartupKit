package com.thoughtworks.startup.ui.github;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.thoughtworks.startup.R;
import com.thoughtworks.startup.data.model.GitHubUser;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class GitHubAdapter extends RecyclerView.Adapter<GitHubAdapter.GitHubUserViewHolder> {

    private List<GitHubUser> mUserList;
    private Context mContext;

    public GitHubAdapter(Context context) {
        mContext = context;
        mUserList = new ArrayList<>();
    }

    public void setUserList(List<GitHubUser> userList) {
        mUserList = userList;
    }

    @Override
    public GitHubUserViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_github_user, parent, false);
        return new GitHubUserViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(GitHubUserViewHolder holder, int position) {
        GitHubUser user = mUserList.get(position);
        Picasso.with(mContext).load(user.getAvatarUrl()).into(holder.userAvatar);
        holder.userName.setText(user.getLogin());
        holder.userType.setText(user.getType());
    }

    @Override
    public int getItemCount() {
        return mUserList.size();
    }

    class GitHubUserViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.user_avatar) ImageView userAvatar;
        @BindView(R.id.user_name) TextView userName;
        @BindView(R.id.user_type) TextView userType;

        public GitHubUserViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
