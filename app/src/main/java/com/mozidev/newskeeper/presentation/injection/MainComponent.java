package com.mozidev.newskeeper.presentation.injection;

import com.mozidev.newskeeper.presentation.article_details.ArticleDetailsActivity;
import com.mozidev.newskeeper.presentation.article_details.ArticlesListAdapter;
import com.mozidev.newskeeper.presentation.articles.ArticlesListActivity;
import com.mozidev.newskeeper.presentation.categories.CategoriesListActivity;
import com.mozidev.newskeeper.presentation.common.BaseActivity;
import com.mozidev.newskeeper.presentation.publisher_details.PublisherDetailsActivity;
import com.mozidev.newskeeper.presentation.publishers.PublishersListActivity;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {DataModule.class, DomainModule.class})
public interface MainComponent {
    void inject(BaseActivity target);
    void inject(PublishersListActivity target);
    void inject(PublisherDetailsActivity target);
    void inject(ArticlesListActivity target);
    void inject(ArticleDetailsActivity target);
    void inject(CategoriesListActivity target);
    void inject(ArticlesListAdapter target);
}
