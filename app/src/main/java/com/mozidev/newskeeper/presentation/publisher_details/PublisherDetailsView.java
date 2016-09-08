package com.mozidev.newskeeper.presentation.publisher_details;

import com.mozidev.newskeeper.domain.publishers.Publisher;
import com.mozidev.newskeeper.presentation.publishers.PublisherViewModel;

/**
 * Created by mozi on 31.08.16.
 */
public interface PublisherDetailsView {

    void fillContent(PublisherViewModel publisher);

}
