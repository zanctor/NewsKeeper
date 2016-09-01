package com.mozidev.newskeeper.presentation.articles;

import com.mozidev.newskeeper.domain.articles.Article;

/**
 * Created by mozi on 31.08.16.
 */
public interface ArticlesListRouter {

    void openArticle(Article article);

    void openCategories();

    void openSettings();

}
