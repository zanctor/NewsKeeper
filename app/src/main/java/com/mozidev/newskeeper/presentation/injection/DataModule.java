package com.mozidev.newskeeper.presentation.injection;

import android.content.Context;

import com.mozidev.newskeeper.data.ArticlesDataProviderImpl;
import com.mozidev.newskeeper.data.CategoriesDataProviderImpl;
import com.mozidev.newskeeper.data.PublishersDataProviderImpl;
import com.mozidev.newskeeper.domain.articles.ArticlesDataProvider;
import com.mozidev.newskeeper.domain.categories.CategoriesDataProvider;
import com.mozidev.newskeeper.domain.common.MainPrefs;
import com.mozidev.newskeeper.domain.publishers.PublishersDataProvider;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.realm.Realm;

@Module
public class DataModule {

    Context context;

    public DataModule(Context context) {
        this.context = context;
    }

    @Singleton
    @Provides
    public CategoriesDataProvider getCategoriesDataProvider() {
        return new CategoriesDataProviderImpl();
    }

    @Singleton
    @Provides
    public PublishersDataProvider getPublishersDataProvider() {
        return new PublishersDataProviderImpl();
    }

    @Singleton
    @Provides
    public Context getContext() {
        return context;
    }

    @Singleton
    @Provides
    public ArticlesDataProvider getArticlesDataProvider(Realm realm) {
        return new ArticlesDataProviderImpl(realm);
    }

    @Singleton
    @Provides
    public MainPrefs getMainPrefs(Context context) {
        return MainPrefs.with(context);
    }

    @Singleton
    @Provides
    public Realm getRealm() {
        return Realm.getDefaultInstance();
    }

}
