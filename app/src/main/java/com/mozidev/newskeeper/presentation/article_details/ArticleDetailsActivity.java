package com.mozidev.newskeeper.presentation.article_details;

import com.mozidev.newskeeper.R;
import com.mozidev.newskeeper.presentation.common.BaseActivity;
import com.mozidev.newskeeper.presentation.common.BasePresenter;

import javax.inject.Inject;

/**
 * Created by mozi on 31.08.16.
 */
public class ArticleDetailsActivity extends BaseActivity {

    @Inject
    ArticleDetailsPresenter articleDetailsPresenter;

    @Override
    protected BasePresenter getPresenter() {
        return articleDetailsPresenter;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_article_details;
    }
}
