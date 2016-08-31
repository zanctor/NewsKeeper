package com.mozidev.newskeeper.presentation.injection;

import com.mozidev.newskeeper.domain.common.RestInterface;
import com.mozidev.newskeeper.domain.common.RestService;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Scheduler;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by mozi on 31.08.16.
 */
@Module
public class DomainModule {

    public static final String JOB = "JOB";
    public static final String UI = "UI";

    @Provides
    @Singleton
    @Named(JOB)
    public Scheduler provideJobScheduler() {
        return Schedulers.io();
    }

    @Provides
    @Singleton
    @Named(UI)
    public Scheduler provideUIScheduler() {
        return AndroidSchedulers.mainThread();
    }

    @Singleton
    @Provides
    public RestInterface provideRestInterface(){
        return new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl(RestInterface.API_URL)
                .build()
                .create(RestInterface.class);
    }

    @Singleton
    @Provides
    public RestService provideRest(RestInterface restInterface){
        return new RestService(restInterface);
    }

}
