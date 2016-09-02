package com.mozidev.newskeeper.presentation.article_details;

import com.mozidev.newskeeper.domain.articles.Article;
import com.mozidev.newskeeper.presentation.common.BasePresenter;

import javax.inject.Inject;

/**
 * Created by mozi on 31.08.16.
 */
public class ArticleDetailsPresenter extends BasePresenter<ArticleDetailsView, ArticleDetailsRouter> {

    private Article article;

    @Inject
    public ArticleDetailsPresenter() {
    }

    public void init(Article article) {
        this.article = article;
    }

    @Override
    public void onStart() {
        if (article == null) {
            getView().setToolbar(article.getTitle());
        }
    }

    @Override
    public void onStop() {

    }

}
