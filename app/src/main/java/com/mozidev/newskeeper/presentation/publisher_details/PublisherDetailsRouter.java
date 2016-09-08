package com.mozidev.newskeeper.presentation.publisher_details;

import android.content.Intent;

/**
 * Created by mozi on 31.08.16.
 */
public interface PublisherDetailsRouter {

    void contactPublisher(Intent intent);

    void visitSite(Intent intent);

    void viewStream(Intent intent);
}
