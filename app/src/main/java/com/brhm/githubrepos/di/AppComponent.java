package com.brhm.githubrepos.di;

import com.brhm.githubrepos.GitReposApplication;

import javax.inject.Singleton;

import dagger.Component;
import dagger.android.AndroidInjectionModule;
import dagger.android.AndroidInjector;

@Singleton
@Component(modules = {NetworkModule.class,ActivityBindingModule.class, AndroidInjectionModule.class})
public interface AppComponent extends AndroidInjector<GitReposApplication> {

}
