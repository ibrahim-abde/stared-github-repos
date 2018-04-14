package com.brhm.githubrepos.reposList;

import com.brhm.githubrepos.GithubApi;
import com.brhm.githubrepos.Utils;
import com.brhm.githubrepos.schedulers.BaseSchedulerProvider;

import javax.inject.Inject;

import io.reactivex.disposables.Disposable;

public class ReposListPresenter implements ReposListMVP.Presenter {

    private final ReposListMVP.View view;
    private final GithubApi githubApi;
    private final BaseSchedulerProvider schedulerProvider;

    private int page = 1;

    private Disposable disposable;

    @Inject
    public ReposListPresenter(ReposListMVP.View view, GithubApi githubApi, BaseSchedulerProvider schedulerProvider) {
        this.view = view;
        this.githubApi = githubApi;
        this.schedulerProvider = schedulerProvider;
    }


    public void loadRepos() {
        // don't load if we are still loading something
        if(disposable != null && !disposable.isDisposed()) return;

        view.showLoading();
        disposable = githubApi.getMostStaredRepos(Utils.getLastMonthSearchFilter(),page)
                .subscribeOn(schedulerProvider.io())
                .observeOn(schedulerProvider.mainThread())
                .subscribe((reposResponse) -> {
                    view.addRepos(reposResponse.getRepos());
                    page++;
                }
                , (error) -> view.showErrorMessage());
    }

    @Override
    public void onDestroy() {
        disposable.dispose();
    }
}
