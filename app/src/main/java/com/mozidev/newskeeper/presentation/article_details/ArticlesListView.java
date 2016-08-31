package com.mozidev.newskeeper.presentation.article_details;

import com.mozidev.newskeeper.domain.articles.Article;

import java.util.List;

/**
 * Created by mozi on 31.08.16.
 */
public interface ArticlesListView {

    void setArticles(List<Article> data);

}
