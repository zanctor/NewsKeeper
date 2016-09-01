package com.mozidev.newskeeper.domain.common;

import rx.Observable;
import rx.Scheduler;
import rx.Subscriber;
import rx.subscriptions.CompositeSubscription;

public abstract class Interactor<ResultType, ParameterType> {
    protected final Scheduler jobScheduler;
    protected final Scheduler uiScheduler;
    private final CompositeSubscription subscription = new CompositeSubscription();

    public Interactor(Scheduler jobScheduler, Scheduler uiScheduler) {
        this.jobScheduler = jobScheduler;
        this.uiScheduler = uiScheduler;
    }

    protected Observable<ResultType> buildObservable(ParameterType parameter) {
        return null;
    }

    public void execute(ParameterType parameter, Subscriber<ResultType> subscriber) {
        subscription.add(buildObservable(parameter)
                .subscribeOn(jobScheduler)
                .observeOn(uiScheduler)
                .subscribe(subscriber));
    }

    public void execute(Subscriber<ResultType> subscriber) {
        execute(null, subscriber);
    }

    public void unsubscribe() {
        subscription.clear();
    }
}
