package com.mozidev.newskeeper.presentation.articles;

import com.mozidev.newskeeper.domain.articles.Article;
import com.mozidev.newskeeper.domain.articles.ArticleTextInteractor;
import com.mozidev.newskeeper.domain.articles.ArticlesListInteractor;
import com.mozidev.newskeeper.presentation.common.BasePresenter;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import rx.Subscriber;

@Singleton
public class ArticlesPresenter extends BasePresenter<ArticlesListView, ArticlesListRouter> {

    private final ArticlesListInteractor articlesListInteractor;
    private final ArticleTextInteractor articleTextInteractor;

    @Inject
    public ArticlesPresenter(ArticlesListInteractor articlesListInteractor, ArticleTextInteractor articleTextInteractor) {
        this.articlesListInteractor = articlesListInteractor;
        this.articleTextInteractor = articleTextInteractor;
    }

    @Override
    public void onStart() {
        updateArticles();
    }

    @Override
    public void onStop() {
        articlesListInteractor.unsubscribe();
    }

    public void openCategories() {
        getRouter().openCategories();
    }

    public void openArticle(ArticleViewModel article) {
        if (article.getText() == null) {
            articleTextInteractor.execute(article, new Subscriber<Void>() {
                @Override
                public void onCompleted() {

                }

                @Override
                public void onError(Throwable e) {

                }

                @Override
                public void onNext(Void s) {
                    getRouter().openArticle(article);
                }
            });
        } else {
            getRouter().openArticle(article);
        }
    }

    public void openSettings() {
        getRouter().openSettings();
    }

    public void updateArticles() {
        articlesListInteractor.execute(new Subscriber<List<ArticleViewModel>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                e.printStackTrace();
            }

            @Override
            public void onNext(List<ArticleViewModel> articles) {
                getView().updateArticles(articles);
            }
        });
    }
}
