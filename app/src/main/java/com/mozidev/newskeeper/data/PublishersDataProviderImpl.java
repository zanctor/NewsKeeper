package com.mozidev.newskeeper.data;

import com.mozidev.newskeeper.domain.publishers.Publisher;
import com.mozidev.newskeeper.domain.publishers.PublishersDataProvider;
import com.mozidev.newskeeper.presentation.publishers.PublisherViewModel;

import java.util.List;

import javax.inject.Inject;

import io.realm.Realm;
import rx.Observable;

public class PublishersDataProviderImpl implements PublishersDataProvider {

    Realm realm;

    @Inject
    public PublishersDataProviderImpl(Realm realm) {
        this.realm = realm;
    }

    @Override
    public Observable<List<Publisher>> getPublishers() {
        return Observable.just(realm.where(Publisher.class)
                        .equalTo("is_deleted", false)
                        .findAll());
    }

    @Override
    public void savePublishers(List<PublisherViewModel> data) {
        realm.beginTransaction();
        realm.copyToRealmOrUpdate(Publisher.create(data));
        realm.commitTransaction();
    }
}
