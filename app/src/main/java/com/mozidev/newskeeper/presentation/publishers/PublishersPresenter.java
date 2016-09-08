package com.mozidev.newskeeper.presentation.publishers;

import android.content.Context;

import com.mozidev.newskeeper.domain.common.MainPrefs;
import com.mozidev.newskeeper.domain.common.SaveFiltersInteractor;
import com.mozidev.newskeeper.domain.common.util.NetworkUtils;
import com.mozidev.newskeeper.domain.publishers.GetPublishersInteractor;
import com.mozidev.newskeeper.domain.publishers.Publisher;
import com.mozidev.newskeeper.domain.publishers.SavePublishersInteractor;
import com.mozidev.newskeeper.presentation.common.BasePresenter;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import rx.Subscriber;

@Singleton
public class PublishersPresenter extends BasePresenter<PublishersListView, PublishersListRouter> {

    MainPrefs prefs;
    List<PublisherViewModel> data;
    Context context;

    private final GetPublishersInteractor getPublishersInteractor;
    private final SavePublishersInteractor savePublishersInteractor;
    private final SaveFiltersInteractor saveFiltersInteractor;

    @Inject
    public PublishersPresenter(GetPublishersInteractor getPublishersInteractor, SavePublishersInteractor savePublishersInteractor, SaveFiltersInteractor saveFiltersInteractor, MainPrefs prefs, Context context) {
        this.getPublishersInteractor = getPublishersInteractor;
        this.savePublishersInteractor = savePublishersInteractor;
        this.saveFiltersInteractor = saveFiltersInteractor;
        this.prefs = prefs;
        this.context = context;
    }

    @Override
    public void onStart() {
        if (prefs.isFirstRun()) {
            if (!NetworkUtils.isConnected(context)) {
                getView().showDataDialog();
            }
            getView().showNotificationsDialog();
        }
        getPublishersInteractor.execute(new Subscriber<List<Publisher>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(List<Publisher> publishers) {
                data = PublisherViewModel.create(publishers);
                getView().setPublishers(data);
            }
        });
    }

    @Override
    public void onStop() {
        getPublishersInteractor.unsubscribe();
        savePublishersInteractor.unsubscribe();
    }

    void openPublisher(PublisherViewModel publisher) {
        getRouter().openPublisher(publisher);
    }

    boolean isAtLeastOneSelected() {
        for (PublisherViewModel publisher : data) {
            if (publisher.isChecked()) {
                return true;
            }
        }
        return false;
    }

    void selectUnselect() {
        for (PublisherViewModel publisher : data) {
            if (publisher.isChecked()) {
                getView().checkAll(false);
                return;
            }
        }
        getView().checkAll(true);
    }

    void checkAndGo() {
        if (data != null) {
            if (isAtLeastOneSelected()) {
                saveData();
            } else {
                showCheckDialog();
            }
        } else {
            openAricles();
        }
    }

    private void showCheckDialog() {
        getView().showCheckDialog();
    }

    void openAricles(){
        getRouter().openArticles();
    }

    private void saveData() {
        savePublishersInteractor.execute(data, new Subscriber<Void>() {
            @Override
            public void onCompleted() {
                if (NetworkUtils.isConnected(context)) {
                    saveFiltersInteractor.execute(new Subscriber<Object>() {

                        @Override
                        public void onNext(Object o) {

                        }

                        @Override
                        public void onError(Throwable e) {

                        }

                        @Override
                        public void onCompleted() {

                        }
                    });
                }
                openAricles();
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(Void aVoid) {

            }
        });
    }
}
