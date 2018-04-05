package com.brhm.githubrepos;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class ReposActivity extends AppCompatActivity {
    static final String TAG = "GITHUB_REPOS";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repos);

        GitReposApplication application = (GitReposApplication) getApplication();


        application.getGithubApi().getMostStaredRepos()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe((repo) -> {
                    Log.i(TAG, "onCreate: "+ repo.getRepos().size());
                });
    }
}
