package com.mozidev.newskeeper.presentation.articles;

import com.mozidev.newskeeper.domain.articles.Article;

import java.util.List;

/**
 * Created by mozi on 31.08.16.
 */
public interface ArticlesListView {

    void updateArticles(List<Article> data);

}
