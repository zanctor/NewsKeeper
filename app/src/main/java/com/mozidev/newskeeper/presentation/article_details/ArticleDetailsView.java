package com.mozidev.newskeeper.presentation.article_details;

import com.mozidev.newskeeper.domain.articles.Article;

/**
 * Created by mozi on 31.08.16.
 */
public interface ArticleDetailsView {

    void setToolbar(String title);

    void fillContent(Article article);

}
