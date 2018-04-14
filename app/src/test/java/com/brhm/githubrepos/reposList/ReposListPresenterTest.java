package com.brhm.githubrepos.reposList;

import com.brhm.githubrepos.GithubApi;
import com.brhm.githubrepos.models.Repo;
import com.brhm.githubrepos.models.ReposResponse;
import com.brhm.githubrepos.schedulers.BaseSchedulerProvider;
import com.brhm.githubrepos.schedulers.TrampolineSchedulerProvider;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;

import io.reactivex.Single;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Unit tests for {@link ReposListPresenter}.
 */

@RunWith(MockitoJUnitRunner.class)
public class ReposListPresenterTest {

    @Mock private ReposListMVP.View view;
    @Mock private GithubApi githubApi;
    private BaseSchedulerProvider schedulerProvider = new TrampolineSchedulerProvider();

    private ReposListPresenter presenter;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        presenter = new ReposListPresenter(view, githubApi, schedulerProvider);
    }

    @Test
    public void shouldLoadRepos() throws Exception {
        // Given
        ReposResponse response = response();
        when(githubApi.getMostStaredRepos(anyString(), anyInt()))
                .thenReturn(Single.just(response));

        // When
        presenter.loadRepos();

        // Then
        verify(view).showLoading();
        verify(view).addRepos(response.getRepos());
    }

    private ReposResponse response() {
        ReposResponse res = new ReposResponse();
        res.setRepos(repos());

        return res;
    }

    private List<Repo> repos(){
        Repo repo1 = new Repo();
        Repo repo2 = new Repo();
        Repo repo3 = new Repo();

        return Arrays.asList(repo1, repo2, repo3);
    }

    @Test
    public void shouldShowErrorMessage() throws Exception {
        // Given
        when(githubApi.getMostStaredRepos(anyString(), anyInt()))
                .thenReturn(Single.error(new Throwable()));

        // When
        presenter.loadRepos();

        // Then
        verify(view).showLoading();
        verify(view).showErrorMessage();
    }

}