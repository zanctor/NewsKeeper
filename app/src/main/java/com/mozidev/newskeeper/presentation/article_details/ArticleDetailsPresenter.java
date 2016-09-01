package com.mozidev.newskeeper.presentation.article_details;

import com.mozidev.newskeeper.domain.articles.Article;
import com.mozidev.newskeeper.domain.articles.ArticleTextInteractor;
import com.mozidev.newskeeper.presentation.common.BasePresenter;

import javax.inject.Inject;

import rx.Subscriber;

/**
 * Created by mozi on 31.08.16.
 */
public class ArticleDetailsPresenter extends BasePresenter<ArticleDetailsView, ArticleDetailsRouter> {

    private Article article;
    private ArticleTextInteractor articleTextInteractor;

    @Inject
    public ArticleDetailsPresenter(ArticleTextInteractor articleTextInteractor) {
        this.articleTextInteractor = articleTextInteractor;
    }

    public void init(Article article) {
        this.article = article;
    }

    @Override
    public void onStart() {
        if (article == null) return;
        articleTextInteractor.execute(article.getId(), new Subscriber<String>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(String text) {
                getView().setToolbar(article.getTitle()); //todo
            }
        });
    }

    @Override
    public void onStop() {
        articleTextInteractor.unsubscribe();
    }

}
