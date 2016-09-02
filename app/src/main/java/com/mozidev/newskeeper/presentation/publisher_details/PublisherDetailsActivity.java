package com.mozidev.newskeeper.presentation.publisher_details;

import android.os.Bundle;

import com.mozidev.newskeeper.Constants;
import com.mozidev.newskeeper.R;
import com.mozidev.newskeeper.domain.publishers.Publisher;
import com.mozidev.newskeeper.presentation.common.BaseActivity;
import com.mozidev.newskeeper.presentation.common.BasePresenter;
import com.mozidev.newskeeper.presentation.common.Layout;

import javax.inject.Inject;

@Layout(id = R.layout.activity_publisher_details)
public class PublisherDetailsActivity extends BaseActivity implements PublisherDetailsRouter, PublisherDetailsView {

    @Inject
    PublisherDetailsPresenter publisherDetailsPresenter;

    private Publisher publisher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        publisher = (Publisher) getIntent().getSerializableExtra(Constants.EXTRA_PUBLISHER);
        if (publisher != null) {
            publisherDetailsPresenter.init(publisher);
        }
    }

    @Override
    protected BasePresenter getPresenter() {
        return publisherDetailsPresenter;
    }

    @Override
    public void setToolbar(String title) {
        initToolbar(title);
    }
}
