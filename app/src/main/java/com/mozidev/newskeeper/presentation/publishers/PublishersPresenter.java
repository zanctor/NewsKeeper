package com.mozidev.newskeeper.presentation.publishers;

import com.mozidev.newskeeper.domain.common.MainPrefs;
import com.mozidev.newskeeper.domain.publishers.GetPublishersInteractor;
import com.mozidev.newskeeper.domain.publishers.Publisher;
import com.mozidev.newskeeper.presentation.common.BasePresenter;

import java.util.List;

import javax.inject.Inject;

import rx.Subscriber;

/**
 * Created by mozi on 31.08.16.
 */
public class PublishersPresenter extends BasePresenter<PublishersListView, PublishersListRouter> {

    MainPrefs prefs;

    private final GetPublishersInteractor getPublishersInteractor;

    @Inject
    public PublishersPresenter(GetPublishersInteractor getPublishersInteractor, MainPrefs prefs) {
        this.getPublishersInteractor = getPublishersInteractor;
        this.prefs = prefs;
    }

    @Override
    public void onStart() {
        if (prefs.isFirstRun()) getView().showNotificationsDialog();
        getPublishersInteractor.execute(new Subscriber<List<Publisher>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(List<Publisher> publishers) {
                getView().setPublishers(publishers);
            }
        });
    }

    @Override
    public void onStop() {
        getPublishersInteractor.unsubscribe();
    }

    public void openArticles() {
        getRouter().openArticles();
    }

    public void openPublisher(Publisher publisher) {
        getRouter().openPublisher(publisher);
    }
}
