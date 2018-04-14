package com.brhm.githubrepos.reposList;

import com.brhm.githubrepos.GithubApi;
import com.brhm.githubrepos.Utils;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class ReposListPresenter implements ReposListMVP.Presenter {

    private final ReposListMVP.View view;
    private final GithubApi githubApi;

    private int page = 1;

    private Disposable disposable;

    @Inject
    public ReposListPresenter(ReposListMVP.View view, GithubApi githubApi) {
        this.view = view;
        this.githubApi = githubApi;
    }


    public void loadRepos() {
        // don't load if we are still loading something
        if(disposable != null && !disposable.isDisposed()) return;

        view.showLoading();
        disposable = githubApi.getMostStaredRepos(Utils.getLastMonthSearchFilter(),page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
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
