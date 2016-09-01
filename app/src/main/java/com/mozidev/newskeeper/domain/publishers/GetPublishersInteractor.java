package com.mozidev.newskeeper.domain.publishers;

import com.mozidev.newskeeper.domain.common.Interactor;
import com.mozidev.newskeeper.presentation.injection.DomainModule;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import rx.Observable;
import rx.Scheduler;

public class GetPublishersInteractor extends Interactor<List<Publisher>, Void> {

    private final PublishersDataProvider publishersDataProvider;

    @Inject
    public GetPublishersInteractor(@Named(DomainModule.IO) Scheduler jobScheduler, @Named(DomainModule.UI) Scheduler uiScheduler, PublishersDataProvider publishersDataProvider) {
        super(jobScheduler, uiScheduler);
        this.publishersDataProvider = publishersDataProvider;
    }

    @Override
    protected Observable<List<Publisher>> buildObservable(Void parameter) {
        return publishersDataProvider.getPublishers();
    }
}
