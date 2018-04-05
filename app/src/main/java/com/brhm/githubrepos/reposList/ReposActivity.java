package com.brhm.githubrepos.reposList;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import com.brhm.githubrepos.GitReposApplication;
import com.brhm.githubrepos.R;
import com.brhm.githubrepos.models.Repo;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ReposActivity extends AppCompatActivity implements ReposListView {
    static final String TAG = "GITHUB_REPOS";

    private ReposListPresenter presenter;

    @BindView(R.id.repos_list)
    RecyclerView reposList;
    @BindView(R.id.progressbar)
    ProgressBar progressBar;
    @BindView(R.id.error_view)
    View errorView;

    ReposListAdapter reposListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repos);
        ButterKnife.bind(this);

        GitReposApplication application = (GitReposApplication) getApplication();

        presenter = new ReposListPresenter(this,application.getGithubApi());
        presenter.loadRepos();

        reposListAdapter = new ReposListAdapter();
        reposList.setAdapter(reposListAdapter);
        reposList.setLayoutManager(new LinearLayoutManager(this));

    }

    @Override
    public void addRepos(List<Repo> repos) {
        progressBar.setVisibility(View.GONE);
        reposList.setVisibility(View.VISIBLE);
        reposListAdapter.addRepos(repos);
    }

    @Override
    public void showLoading() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void showErrorMessage() {
        reposList.setVisibility(View.GONE);
        progressBar.setVisibility(View.GONE);
    }

    @Override
    protected void onStop() {
        super.onStop();
        presenter.destroy();
    }
}
