package com.mozidev.newskeeper.presentation.article_details;

import com.mozidev.newskeeper.domain.articles.Article;
import com.mozidev.newskeeper.domain.articles.ArticleInteractor;
import com.mozidev.newskeeper.presentation.common.BasePresenter;

import javax.inject.Inject;

import rx.Subscriber;

/**
 * Created by mozi on 31.08.16.
 */
public class ArticleDetailsPresenter extends BasePresenter {

    private Integer articleId;
    private ArticleInteractor articleInteractor;

    public void init(Integer articleId){
        this.articleId = articleId;
    }

    @Inject
    public ArticleDetailsPresenter(ArticleInteractor articleInteractor){
        this.articleInteractor = articleInteractor;
    }

    @Override
    public void onStart() {
        if (articleId == null) return;
        articleInteractor.execute(articleId, new Subscriber<Article>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(Article article) {

            }
        });
    }

    @Override
    public void onStop() {
        articleInteractor.unsubscribe();
    }

}
