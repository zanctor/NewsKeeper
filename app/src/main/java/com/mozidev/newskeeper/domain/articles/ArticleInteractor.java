package com.mozidev.newskeeper.domain.articles;

import com.mozidev.newskeeper.domain.common.Interactor;
import com.mozidev.newskeeper.presentation.injection.DomainModule;

import javax.inject.Inject;
import javax.inject.Named;

import rx.Observable;
import rx.Scheduler;

public class ArticleInteractor extends Interactor<Article, Integer> {

    private final ArticlesDataProvider articlesDataProvider;

    @Inject
    public ArticleInteractor(@Named(DomainModule.JOB) Scheduler jobScheduler, @Named(DomainModule.UI) Scheduler uiScheduler, ArticlesDataProvider articlesDataProvider) {
        super(jobScheduler, uiScheduler);
        this.articlesDataProvider = articlesDataProvider;
    }

    @Override
    protected Observable<Article> buildObservable(Integer parameter) {
        return articlesDataProvider.getArticle(jobScheduler, parameter);
    }
}
