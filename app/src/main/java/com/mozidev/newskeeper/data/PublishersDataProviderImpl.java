package com.mozidev.newskeeper.data;

import com.mozidev.newskeeper.domain.publishers.Publisher;
import com.mozidev.newskeeper.domain.publishers.PublishersDataProvider;

import java.util.List;

import io.realm.Realm;
import rx.Observable;

public class PublishersDataProviderImpl implements PublishersDataProvider {

    @Override
    public Observable<List<Publisher>> getPublishers() {
        return null;
    }

    @Override
    public void savePublishers(List<Publisher> data) {
        Realm.getDefaultInstance()
                .copyToRealmOrUpdate(data);
    }
}
