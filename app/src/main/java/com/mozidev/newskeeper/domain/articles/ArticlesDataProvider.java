package com.mozidev.newskeeper.domain.articles;

import java.util.List;

import rx.Observable;
import rx.Scheduler;

public interface ArticlesDataProvider {

    Observable<List<Article>> getArticles();

    Observable<Article> getArticle(int id);

    void saveArticles(List<Article> data);

    void cleanArticles(Scheduler ioScheduler);
}
