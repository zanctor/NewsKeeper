package com.mozidev.newskeeper.presentation.publisher_details;

import com.mozidev.newskeeper.R;
import com.mozidev.newskeeper.presentation.common.BaseActivity;
import com.mozidev.newskeeper.presentation.common.BasePresenter;
import com.mozidev.newskeeper.presentation.publishers.PublishersPresenter;

import javax.inject.Inject;

/**
 * Created by mozi on 31.08.16.
 */
public class PublisherDetailsActivity extends BaseActivity implements PublisherDetailsRouter, PublisherDetailsView {

    @Inject
    PublishersPresenter publisherPresenter;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_publisher_details;
    }

    @Override
    protected BasePresenter getPresenter() {
        return publisherPresenter;
    }

    @Override
    public void setToolbar(String title) {
        initToolbar(title);
    }
}
