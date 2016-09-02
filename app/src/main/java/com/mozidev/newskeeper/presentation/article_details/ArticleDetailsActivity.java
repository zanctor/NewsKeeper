package com.mozidev.newskeeper.presentation.article_details;

import android.os.Bundle;

import com.mozidev.newskeeper.Constants;
import com.mozidev.newskeeper.R;
import com.mozidev.newskeeper.domain.articles.Article;
import com.mozidev.newskeeper.presentation.common.BaseActivity;
import com.mozidev.newskeeper.presentation.common.BasePresenter;
import com.mozidev.newskeeper.presentation.common.Layout;

import javax.inject.Inject;

@Layout(id = R.layout.activity_article_details)
public class ArticleDetailsActivity extends BaseActivity implements ArticleDetailsView {

    @Inject
    ArticleDetailsPresenter articleDetailsPresenter;
    private Article article;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        article = (Article) getIntent().getSerializableExtra(Constants.EXTRA_ARTICLE);
        if (article != null) {
            articleDetailsPresenter.init(article);
        }
    }

    @Override
    protected BasePresenter getPresenter() {
        return articleDetailsPresenter;
    }

    @Override
    public void setToolbar(String title) {
        initToolbar(title);
    }
}
