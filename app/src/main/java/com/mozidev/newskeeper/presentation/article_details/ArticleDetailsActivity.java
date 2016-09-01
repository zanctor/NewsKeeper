package com.mozidev.newskeeper.presentation.article_details;

import android.os.Bundle;

import com.mozidev.newskeeper.Constants;
import com.mozidev.newskeeper.R;
import com.mozidev.newskeeper.domain.articles.Article;
import com.mozidev.newskeeper.presentation.common.BaseActivity;
import com.mozidev.newskeeper.presentation.common.BasePresenter;

import javax.inject.Inject;

/**
 * Created by mozi on 31.08.16.
 */
public class ArticleDetailsActivity extends BaseActivity implements ArticleDetailsView {

    private Article article;

    @Inject
    ArticleDetailsPresenter articleDetailsPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        article = (Article) getIntent().getSerializableExtra(Constants.EXTRA_ARTICLE);
    }

    @Override
    protected BasePresenter getPresenter() {
        return articleDetailsPresenter;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_article_details;
    }

    @Override
    public void setToolbar(String title) {
        initToolbar(title);
    }
}
