package com.mozidev.newskeeper.presentation.article_details;

import android.content.Intent;

import com.mozidev.newskeeper.domain.articles.Article;

/**
 * Created by mozi on 31.08.16.
 */
public interface ArticleDetailsRouter {

    void showVideo(Intent intent);

    void showLink(Intent intent);

}
