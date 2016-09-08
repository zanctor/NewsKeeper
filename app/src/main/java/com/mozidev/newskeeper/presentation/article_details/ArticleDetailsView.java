package com.mozidev.newskeeper.presentation.article_details;

import com.mozidev.newskeeper.domain.articles.Article;
import com.mozidev.newskeeper.presentation.articles.ArticleViewModel;

/**
 * Created by mozi on 31.08.16.
 */
public interface ArticleDetailsView {

    void fillContent(ArticleViewModel article);

}
