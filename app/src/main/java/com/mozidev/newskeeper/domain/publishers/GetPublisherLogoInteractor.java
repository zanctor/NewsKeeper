package com.mozidev.newskeeper.domain.publishers;

import com.mozidev.newskeeper.domain.common.Interactor;
import com.mozidev.newskeeper.presentation.injection.DomainModule;

import javax.inject.Inject;
import javax.inject.Named;

import rx.Observable;
import rx.Scheduler;

/**
 * Created by TS_HArk on 04.09.2016.
 */
public class GetPublisherLogoInteractor extends Interactor<String, Integer> {

    PublishersDataProvider publishersDataProvider;

    @Inject
    public GetPublisherLogoInteractor(@Named(DomainModule.IO) Scheduler jobScheduler, @Named(DomainModule.UI) Scheduler uiScheduler, PublishersDataProvider publishersDataProvider) {
        super(jobScheduler, uiScheduler);
        this.publishersDataProvider = publishersDataProvider;
    }

    @Override
    protected Observable<String> buildObservable(Integer parameter) {
        return publishersDataProvider.getPublisherLogo(parameter);
    }
}
