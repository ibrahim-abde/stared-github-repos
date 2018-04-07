package com.brhm.githubrepos.di;


import com.brhm.githubrepos.reposList.ReposActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityBuilder {

    @ContributesAndroidInjector
    abstract ReposActivity bindMainActivity();
}
