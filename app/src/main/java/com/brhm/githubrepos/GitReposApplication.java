package com.brhm.githubrepos;

import android.app.Application;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class GitReposApplication extends Application {

    private GithubApi githubApi;

    @Override
    public void onCreate() {
        super.onCreate();

        setUpGithubApi();
    }

    private void setUpGithubApi() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.github.com/")
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        githubApi = retrofit.create(GithubApi.class);
    }

    public GithubApi getGithubApi() {
        return githubApi;
    }
}
