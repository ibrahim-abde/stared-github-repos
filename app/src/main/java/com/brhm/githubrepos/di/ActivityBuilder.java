package com.brhm.githubrepos.di;


import com.brhm.githubrepos.reposList.ReposActivity;
import com.brhm.githubrepos.reposList.ReposListModule;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityBuilder {

    @ContributesAndroidInjector(modules = {ReposListModule.class})
    abstract ReposActivity bindReposListActivity();
}
