package com.mozidev.newskeeper.domain.publishers;

import com.mozidev.newskeeper.domain.common.Interactor;
import com.mozidev.newskeeper.presentation.injection.DomainModule;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import rx.Observable;
import rx.Scheduler;

/**
 * Created by mozi on 02.09.16.
 */
public class SavePublishersInteractor extends Interactor<Void, List<Publisher>> {

    PublishersDataProvider provider;

    @Inject
    public SavePublishersInteractor(@Named(DomainModule.IO) Scheduler jobScheduler, @Named(DomainModule.UI) Scheduler uiScheduler, PublishersDataProvider provider) {
        super(jobScheduler, uiScheduler);
        this.provider = provider;
    }

    @Override
    protected Observable<Void> buildObservable(List<Publisher> parameter) {
        provider.savePublishers(parameter);

        return Observable.empty();
    }
}
