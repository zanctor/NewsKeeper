package com.mozidev.newskeeper.domain.articles;

import com.mozidev.newskeeper.domain.common.Interactor;
import com.mozidev.newskeeper.presentation.injection.DomainModule;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import rx.Observable;
import rx.Scheduler;

public class ArticlesListInteractor extends Interactor<List<Article>, Void> {

    private final ArticlesDataProvider articlesDataProvider;

    @Inject
    public ArticlesListInteractor(@Named(DomainModule.IO) Scheduler jobScheduler, @Named(DomainModule.UI) Scheduler uiScheduler, ArticlesDataProvider articlesDataProvider) {
        super(jobScheduler, uiScheduler);
        this.articlesDataProvider = articlesDataProvider;
    }

    @Override
    protected Observable<List<Article>> buildObservable(Void parameter) {
        return articlesDataProvider.getArticles();
    }
}
