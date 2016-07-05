
package com.thoughtworks.startup.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class GitHubUserList {

    @SerializedName("items")
    @Expose
    private List<GitHubUser> items = new ArrayList<>();

    /**
     *
     * @return
     *     The items
     */
    public List<GitHubUser> getItems() {
        return items;
    }

    /**
     *
     * @param items
     *     The items
     */
    public void setItems(List<GitHubUser> items) {
        this.items = items;
    }

}
