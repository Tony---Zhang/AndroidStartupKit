package com.thoughtworks.startup.data.remote;

import com.thoughtworks.startup.data.model.GitHubUserList;

import retrofit.GsonConverterFactory;
import retrofit.Retrofit;
import retrofit.RxJavaCallAdapterFactory;
import retrofit.http.GET;
import retrofit.http.Query;
import rx.Observable;

public interface GitHubService {

    String ENDPOINT = "https://api.github.com";

    @GET("/search/users")
    Observable<GitHubUserList> getGitHubUserList(@Query("q") String q);

    class Creator {

        public static GitHubService newGitHubService() {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(ENDPOINT)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .build();
            return retrofit.create(GitHubService.class);
        }
    }
}
