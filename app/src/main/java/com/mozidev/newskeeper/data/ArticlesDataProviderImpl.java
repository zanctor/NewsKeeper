package com.mozidev.newskeeper.data;

import com.mozidev.newskeeper.domain.articles.Article;
import com.mozidev.newskeeper.domain.articles.ArticlesDataProvider;
import com.mozidev.newskeeper.domain.categories.Category;
import com.mozidev.newskeeper.domain.publishers.Publisher;
import com.mozidev.newskeeper.presentation.articles.ArticleViewModel;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.realm.Realm;
import rx.Observable;
import rx.Scheduler;

@Singleton
public class ArticlesDataProviderImpl implements ArticlesDataProvider {

    private static final long TWO_WEEKS_INTERVAL = 1209600000;

    private final Realm realm;

    @Inject
    public ArticlesDataProviderImpl(Realm realm) {
        this.realm = realm;
    }

    @Override
    public Observable<List<ArticleViewModel>> getArticles() {

        List<ArticleViewModel> articles = ArticleViewModel.create(realm.where(Article.class)
                .equalTo("status", true)
                .equalTo("is_deleted", false)
                .findAll());

        System.out.println("FUCK " + "retrieved list size: " + articles.size());

        List<Publisher> publishers = realm.where(Publisher.class)
                        .equalTo("is_deleted", false)
                        .equalTo("checked", true)
                        .findAll();

        System.out.println("FUCK " + "retrieved list size: " + publishers.size());

        List<Category> categories = realm.where(Category.class)
                        .equalTo("is_deleted", false)
                        .equalTo("checked", true)
                        .findAll();

        System.out.println("FUCK " + "retrieved list size: " + categories.size());

        List<ArticleViewModel> articleViewModels = new ArrayList<>();

        for (ArticleViewModel articleViewModel : articles) {
            for (Publisher publisher : publishers) {
                for (Category category : categories) {
                    if (publisher.getId() == articleViewModel.getPublisherId() && category.getId() == articleViewModel.getCategoryId()) {
                        articleViewModel.setPublisherName(publisher.getPublisherName());
                        articleViewModel.setPublisherLogo(publisher.getLogo());
                        articleViewModels.add(articleViewModel);
                    }
                }
            }
        }



        return Observable.just(articleViewModels);

    }

    @Override
    public void refreshArticles(List<Article> data) {
        realm.beginTransaction();
        realm.copyToRealmOrUpdate(data);
        realm.commitTransaction();
    }

    @Override
    public void cleanArticles(Scheduler ioScheduler) {
        realm.asObservable()
                .flatMapIterable(it -> it.where(Article.class)
                        .lessThan("publisher_time", System.currentTimeMillis() - TWO_WEEKS_INTERVAL)
                        .findAll())
                .subscribe(it -> it.deleteFromRealm());
    }
}
