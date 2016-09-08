package com.mozidev.newskeeper.presentation.injection;

import com.mozidev.newskeeper.domain.common.gcm.RegistrationIntentService;
import com.mozidev.newskeeper.presentation.article_details.ArticleDetailsActivity;
import com.mozidev.newskeeper.presentation.articles.ArticlesListActivity;
import com.mozidev.newskeeper.presentation.articles.ArticlesListAdapter;
import com.mozidev.newskeeper.presentation.categories.CategoriesListActivity;
import com.mozidev.newskeeper.presentation.common.BaseActivity;
import com.mozidev.newskeeper.presentation.publisher_details.PublisherDetailsActivity;
import com.mozidev.newskeeper.presentation.publishers.PublishersListActivity;
import com.mozidev.newskeeper.presentation.publishers.PublishersListAdapter;
import com.mozidev.newskeeper.presentation.settings.SettingsActivity;
import com.mozidev.newskeeper.presentation.splash.SplashActivity;

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

    void inject(RegistrationIntentService target);

    void inject(PublishersListAdapter target);

    void inject(SplashActivity target);

    void inject(SettingsActivity target);
}
