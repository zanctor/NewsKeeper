package com.mozidev.newskeeper.presentation.publishers;

import com.mozidev.newskeeper.domain.publishers.Publisher;

/**
 * Created by mozi on 31.08.16.
 */
public interface PublishersListRouter {

    void openPublisher(PublisherViewModel publisher);

    void openArticles();

}
