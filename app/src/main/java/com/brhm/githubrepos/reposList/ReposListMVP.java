package com.brhm.githubrepos.reposList;

import com.brhm.githubrepos.models.Repo;

import java.util.List;

/**
 * Contract class between {@link ReposActivity} and {@link ReposListPresenter}.
 */

public interface ReposListMVP {

    interface View {
        void addRepos(List<Repo> repos);
        void showLoading();
        void showErrorMessage();
    }

    interface Presenter {
        void loadRepos();
        void onDestroy();
    }

}

