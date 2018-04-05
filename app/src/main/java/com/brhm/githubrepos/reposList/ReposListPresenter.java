package com.brhm.githubrepos.reposList;

import android.util.Log;

import com.brhm.githubrepos.GithubApi;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

import static com.brhm.githubrepos.reposList.ReposActivity.TAG;

public class ReposListPresenter {

    private final ReposListView view;
    private final GithubApi githubApi;

    private int page = 1;

    private Disposable disposable;

    public ReposListPresenter(ReposListView view, GithubApi githubApi) {
        this.view = view;
        this.githubApi = githubApi;
    }


    public void loadRepos() {
        // don't load if we are still loading something
        if(disposable != null && !disposable.isDisposed()) return;

        view.showLoading();
        disposable = githubApi.getMostStaredRepos()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())

                .doOnError((error) -> view.showErrorMessage())

                .subscribe((reposResponse) -> {
                    view.addRepos(reposResponse.getRepos());
                    page++;
                });
    }


    public void destroy() {
        disposable.dispose();
    }
}