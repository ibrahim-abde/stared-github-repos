package com.brhm.githubrepos.reposList;

import com.brhm.githubrepos.GithubApi;
import com.brhm.githubrepos.Utils;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.PublishSubject;

public class ReposListViewModel {

    // this can be put in an abstract class
    private PublishSubject<ReposListModel> reposListSubject = PublishSubject.create();

    private GithubApi githubApi;

    private int page = 1;

    @Inject
    public ReposListViewModel(GithubApi githubApi) {
        this.githubApi = githubApi;
    }


    public void loadRepos() {
        ReposListModel model = new ReposListModel();
        model.loading = true;
        reposListSubject.onNext(model);

        githubApi.getMostStaredRepos(Utils.getLastMonthSearchFilter(),page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(reposResponse -> {
                    ReposListModel tmp = new ReposListModel();
                    tmp.repos = reposResponse.getRepos();
                    page++;
                    return tmp;
                })
                .subscribe(reposListSubject::onNext);
    }

    public Observable<ReposListModel> model() {
        return reposListSubject;
    }


}
