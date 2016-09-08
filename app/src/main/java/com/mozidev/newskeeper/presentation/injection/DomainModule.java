package com.mozidev.newskeeper.presentation.injection;

import com.mozidev.newskeeper.data.APIDataProviderImpl;
import com.mozidev.newskeeper.domain.common.net.APIDataProvider;

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
@Singleton
@Module
public class DomainModule {

    public static final String IO = "IO";
    public static final String UI = "UI";

    @Provides
    @Singleton
    @Named(IO)
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
    public APIDataProvider provideRestInterface() {
        return new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl(APIDataProvider.API_URL)
                .build()
                .create(APIDataProvider.class);
    }

    @Singleton
    @Provides
    public APIDataProviderImpl provideRest(APIDataProvider APIDataProvider) {
        return new APIDataProviderImpl(APIDataProvider);
    }

}
