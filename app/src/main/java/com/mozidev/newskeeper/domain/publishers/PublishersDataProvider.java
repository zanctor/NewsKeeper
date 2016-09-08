package com.mozidev.newskeeper.domain.publishers;

import com.mozidev.newskeeper.presentation.publishers.PublisherViewModel;

import java.util.List;

import rx.Observable;

public interface PublishersDataProvider {

    Observable<List<Publisher>> getPublishers();

    void savePublishers(List<PublisherViewModel> data);

}
