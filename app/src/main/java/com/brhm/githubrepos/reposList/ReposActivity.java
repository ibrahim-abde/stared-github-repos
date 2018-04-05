package com.brhm.githubrepos.reposList;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.brhm.githubrepos.GitReposApplication;
import com.brhm.githubrepos.R;
import com.brhm.githubrepos.models.Repo;

import java.util.List;

public class ReposActivity extends AppCompatActivity implements ReposListView {
    static final String TAG = "GITHUB_REPOS";

    ReposListPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repos);

        GitReposApplication application = (GitReposApplication) getApplication();

        presenter = new ReposListPresenter(this,application.getGithubApi());
        presenter.loadRepos();
    }

    @Override
    public void addRepos(List<Repo> repos) {
        Log.i(TAG, "addRepos: "+repos.size());
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void showErrorMessage() {

    }

    @Override
    protected void onStop() {
        super.onStop();
        presenter.destroy();
    }
}
