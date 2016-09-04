package com.mozidev.newskeeper.data;

import com.mozidev.newskeeper.domain.publishers.Publisher;
import com.mozidev.newskeeper.domain.publishers.PublishersDataProvider;

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
        return realm.asObservable()
                .map(it -> it.where(Publisher.class)
                .not()
                .equalTo("is_deleted", true)
                .findAll());
    }

    @Override
    public Observable<String> getPublisherLogo(Integer id) {
        return realm.asObservable()
                .map(it -> it.where(Publisher.class)
                        .equalTo("id", id)
                        .findFirst()
                        .getLogo());
    }

    @Override
    public void savePublishers(List<Publisher> data) {
        Realm.getDefaultInstance()
                .copyToRealmOrUpdate(data);
    }
}
