package com.mozidev.newskeeper.presentation.publisher_details;

import com.mozidev.newskeeper.domain.articles.Article;
import com.mozidev.newskeeper.domain.publishers.Publisher;
import com.mozidev.newskeeper.presentation.article_details.ArticleDetailsRouter;
import com.mozidev.newskeeper.presentation.article_details.ArticleDetailsView;
import com.mozidev.newskeeper.presentation.common.BasePresenter;

import javax.inject.Inject;

/**
 * Created by mozi on 31.08.16.
 */
public class PublisherDetailsPresenter extends BasePresenter<PublisherDetailsView, PublisherDetailsRouter> {

    private Publisher publisher;

    @Inject
    public PublisherDetailsPresenter() {
    }

    public void init(Publisher publisher) {
        this.publisher = publisher;
    }

    @Override
    public void onStart() {
        if (publisher == null) {
            getView().setToolbar(publisher.getPublisherName());
        }
    }

    @Override
    public void onStop() {

    }

}
