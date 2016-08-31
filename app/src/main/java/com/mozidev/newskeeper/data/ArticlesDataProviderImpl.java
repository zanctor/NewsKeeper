package com.mozidev.newskeeper.data;

import com.mozidev.newskeeper.domain.articles.Article;
import com.mozidev.newskeeper.domain.articles.ArticlesDataProvider;

import java.util.List;

import rx.Observable;

/**
 * Created by mozi on 31.08.16.
 */
public class ArticlesDataProviderImpl implements ArticlesDataProvider {

    @Override
    public Observable<List<Article>> getArticles() {
        return null;
    }

    @Override
    public Observable<Article> getArticle(int id) {
        return null;
    }
}
