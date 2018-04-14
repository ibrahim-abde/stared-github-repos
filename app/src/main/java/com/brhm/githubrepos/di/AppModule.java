package com.brhm.githubrepos.di;

import com.brhm.githubrepos.schedulers.BaseSchedulerProvider;
import com.brhm.githubrepos.schedulers.SchedulerProvider;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Dagger module providing dependencies with application(Singleton) scope.
 */

@Module
public class AppModule {

    @Provides
    @Singleton
    BaseSchedulerProvider baseSchedulerProvider() {
        return SchedulerProvider.getInstance();
    }

}
