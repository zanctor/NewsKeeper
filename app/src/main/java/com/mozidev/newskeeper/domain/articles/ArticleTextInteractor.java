package com.mozidev.newskeeper.domain.articles;

import com.mozidev.newskeeper.data.APIDataProviderImpl;
import com.mozidev.newskeeper.domain.common.Interactor;
import com.mozidev.newskeeper.presentation.injection.DomainModule;

import javax.inject.Inject;
import javax.inject.Named;

import rx.Observable;
import rx.Scheduler;

public class ArticleTextInteractor extends Interactor<String, Integer> {

    private final APIDataProviderImpl provider;

    @Inject
    public ArticleTextInteractor(@Named(DomainModule.IO) Scheduler jobScheduler, @Named(DomainModule.UI) Scheduler uiScheduler, APIDataProviderImpl provider) {
        super(jobScheduler, uiScheduler);
        this.provider = provider;
    }

    @Override
    protected Observable<String> buildObservable(Integer parameter) {
        return provider.getArticleText(parameter);
    }
}
