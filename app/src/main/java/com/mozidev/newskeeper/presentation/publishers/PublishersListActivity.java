package com.mozidev.newskeeper.presentation.publishers;

import com.mozidev.newskeeper.R;
import com.mozidev.newskeeper.presentation.common.BaseActivity;
import com.mozidev.newskeeper.presentation.common.BasePresenter;

import javax.inject.Inject;

/**
 * Created by mozi on 31.08.16.
 */
public class PublishersListActivity extends BaseActivity implements PublishersListView, PublishersListRouter{

    @Inject
    PublishersPresenter publishersPresenter;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_publishers_list;
    }

    @Override
    protected BasePresenter getPresenter() {
        return publishersPresenter;
    }
}
