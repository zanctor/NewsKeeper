package com.mozidev.newskeeper.domain.articles;

import com.mozidev.newskeeper.domain.common.Interactor;
import com.mozidev.newskeeper.presentation.injection.DomainModule;

import javax.inject.Inject;
import javax.inject.Named;

import rx.Observable;
import rx.Scheduler;

/**
 * Created by mozi on 01.09.16.
 */
public class ClearArticlesInteractor extends Interactor<Void, Void> {

    ArticlesDataProvider dataProvider;

    @Inject
    public ClearArticlesInteractor(@Named(DomainModule.IO) Scheduler jobScheduler, @Named(DomainModule.UI) Scheduler uiScheduler, ArticlesDataProvider dataProvider) {
        super(jobScheduler, uiScheduler);
        this.dataProvider = dataProvider;
    }

    @Override
    protected Observable<Void> buildObservable(Void parameter) {
        dataProvider.cleanArticles(jobScheduler);
        return Observable.empty();
    }
}
