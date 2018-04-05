package com.brhm.githubrepos;

import com.brhm.githubrepos.models.ReposResponse;

import io.reactivex.Single;
import retrofit2.http.GET;

public interface GithubApi {

    @GET("search/repositories?q=created:>2017-10-22&sort=stars&order=desc")
    Single<ReposResponse> getMostStaredRepos();

}
