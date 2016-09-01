package com.mozidev.newskeeper.domain.publishers;

import java.util.List;

import rx.Observable;

public interface PublishersDataProvider {

    Observable<List<Publisher>> getPublishers();

    Observable<Publisher> getPublisher(int id);

    void savePublishers(List<Publisher> data);

}
