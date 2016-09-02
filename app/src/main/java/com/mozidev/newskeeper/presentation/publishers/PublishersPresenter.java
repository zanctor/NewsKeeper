package com.mozidev.newskeeper.presentation.publishers;

import android.content.Context;

import com.mozidev.newskeeper.domain.common.MainPrefs;
import com.mozidev.newskeeper.domain.common.util.NetworkUtils;
import com.mozidev.newskeeper.domain.publishers.GetPublishersInteractor;
import com.mozidev.newskeeper.domain.publishers.Publisher;
import com.mozidev.newskeeper.domain.publishers.SavePublishersInteractor;
import com.mozidev.newskeeper.presentation.common.BasePresenter;

import java.util.List;

import javax.inject.Inject;

import rx.Subscriber;

/**
 * Created by mozi on 31.08.16.
 */
public class PublishersPresenter extends BasePresenter<PublishersListView, PublishersListRouter> {

    private final GetPublishersInteractor getPublishersInteractor;
    private final SavePublishersInteractor savePublishersInteractor;
    MainPrefs prefs;
    List<Publisher> data;
    Context context;

    @Inject
    public PublishersPresenter(GetPublishersInteractor getPublishersInteractor, SavePublishersInteractor savePublishersInteractor, MainPrefs prefs, Context context) {
        this.getPublishersInteractor = getPublishersInteractor;
        this.savePublishersInteractor = savePublishersInteractor;
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
                data = publishers;
                getView().setPublishers(publishers);
            }
        });
    }

    @Override
    public void onStop() {
        getPublishersInteractor.unsubscribe();
    }

    public void openPublisher(Publisher publisher) {
        getRouter().openPublisher(publisher);
    }

    public boolean isAtLeastOneSelected() {
        for (Publisher publisher : data) {
            if (publisher.isChecked()) {
                return true;
            }
        }
        return false;
    }

    public void selectUnselect() {
        for (Publisher publisher : data) {
            if (publisher.isChecked()) {
                getView().checkAll(true);
                return;
            }
        }
        getView().checkAll(false);
    }

    public void checkAndGo() {
        if (isAtLeastOneSelected()) {
            saveData();
        } else {
            showCheckDialog();
        }
    }

    public void showCheckDialog() {
        getView().showCheckDialog();
    }

    public void saveData() {
        savePublishersInteractor.execute(data, new Subscriber<Void>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(Void aVoid) {
                getRouter().openArticles();
            }
        });
    }
}
