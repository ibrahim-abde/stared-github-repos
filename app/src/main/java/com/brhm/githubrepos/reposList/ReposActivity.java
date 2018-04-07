package com.brhm.githubrepos.reposList;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.brhm.githubrepos.EndlessRecyclerViewScrollListener;
import com.brhm.githubrepos.R;
import com.brhm.githubrepos.models.Repo;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import dagger.android.AndroidInjection;
import io.reactivex.disposables.CompositeDisposable;

public class ReposActivity extends AppCompatActivity {
    static final String TAG = "GITHUB_REPOS";

    @BindView(R.id.repos_list)
    RecyclerView reposList;
    @BindView(R.id.progressbar)
    ProgressBar progressBar;
    @BindView(R.id.error_view)
    View errorView;

    ReposListAdapter reposListAdapter;

    @Inject
    ReposListViewModel viewModel;

    CompositeDisposable disposables = new CompositeDisposable();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repos);
        ButterKnife.bind(this);

        AndroidInjection.inject(this);

        reposListAdapter = new ReposListAdapter();
        reposList.setAdapter(reposListAdapter);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        reposList.setLayoutManager(layoutManager);

        reposList.addOnScrollListener(new EndlessRecyclerViewScrollListener(layoutManager) {
            @Override
            public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
                viewModel.loadRepos();
            }
        });


        disposables.add(viewModel.model()
                .subscribe(reposListModel -> {

                   if(reposListModel.loading) {
                       showLoading();
                   } else if(reposListModel.error) {
                        showErrorMessage();
                   } else {
                       addRepos(reposListModel.repos);
                   }

                }));

        viewModel.loadRepos();
    }

    public void addRepos(List<Repo> repos) {
        progressBar.setVisibility(View.GONE);
        reposList.setVisibility(View.VISIBLE);
        reposListAdapter.addRepos(repos);
    }

    public void showLoading() {
        if(reposListAdapter.isEmpty())
            progressBar.setVisibility(View.VISIBLE);
        else
            reposListAdapter.setLoading(true);
    }

    public void showErrorMessage() {
        if(reposListAdapter.isEmpty()) {
            reposList.setVisibility(View.GONE);
            progressBar.setVisibility(View.GONE);
            errorView.setVisibility(View.VISIBLE);
        } else
            Toast.makeText(this, R.string.loading_error_message, Toast.LENGTH_LONG).show();

    }

    @Override
    protected void onStop() {
        super.onStop();
        disposables.dispose();
        disposables.clear();
    }
}
