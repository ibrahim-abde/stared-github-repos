package com.brhm.githubrepos;

import com.brhm.githubrepos.models.ReposResponse;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface GithubApi {

    @GET("search/repositories?sort=stars&order=desc")
    Single<ReposResponse> getMostStaredRepos(@Query("q") String filter,@Query("page") int page);

}
