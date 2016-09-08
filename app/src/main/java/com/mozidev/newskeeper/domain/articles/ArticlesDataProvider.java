package com.mozidev.newskeeper.domain.articles;

import com.mozidev.newskeeper.presentation.articles.ArticleViewModel;

import java.util.List;

import rx.Observable;
import rx.Scheduler;

public interface ArticlesDataProvider {

    Observable<List<ArticleViewModel>> getArticles();

    void refreshArticles(List<Article> data);

    void cleanArticles(Scheduler ioScheduler);
}
