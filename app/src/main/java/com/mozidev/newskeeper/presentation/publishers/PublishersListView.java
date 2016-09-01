package com.mozidev.newskeeper.presentation.publishers;

import com.mozidev.newskeeper.domain.publishers.Publisher;

import java.util.List;

/**
 * Created by mozi on 31.08.16.
 */
public interface PublishersListView {

    void setPublishers(List<Publisher> data);

    void showNotificationsDialog();

}
