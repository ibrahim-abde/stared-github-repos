package com.brhm.githubrepos.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ReposResponse {

    @SerializedName("items")
    private List<Repo> repos;

    public List<Repo> getRepos() {
        return repos;
    }
}
