package com.brhm.githubrepos.reposList;


import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;

@Module
public abstract class ReposListModule {

    @Binds
    abstract ReposListView getReposListView(ReposActivity activity);

    @Provides
    public static ReposListAdapter getListAdapter() {
        return new ReposListAdapter();
    }

    @Provides
    public static RecyclerView.LayoutManager getLayoutManager(ReposActivity activity) {
        return new LinearLayoutManager(activity);
    }

}
