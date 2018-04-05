package com.brhm.githubrepos.reposList;

import com.brhm.githubrepos.models.Repo;

import java.util.List;

public interface ReposListView {
    void addRepos(List<Repo> repos);
    void showLoading();
    void showErrorMessage();
}
