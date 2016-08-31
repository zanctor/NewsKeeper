package com.mozidev.newskeeper.domain.articles;

import java.util.List;

import rx.Observable;

public interface ArticlesDataProvider {

    Observable<List<Article>> getArticles();

    Observable<Article> getArticle(int id);
}
