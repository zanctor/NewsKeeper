package com.mozidev.newskeeper.data;

import com.mozidev.newskeeper.domain.articles.Article;
import com.mozidev.newskeeper.domain.articles.ArticlesDataProvider;

import java.util.List;

import javax.inject.Inject;

import io.realm.Realm;
import rx.Observable;
import rx.Scheduler;

/**
 * Created by mozi on 31.08.16.
 */
public class ArticlesDataProviderImpl implements ArticlesDataProvider {

    private static final long TWO_WEEKS_INTERVAL = 1209600000;
    Realm realm;

    @Inject
    public ArticlesDataProviderImpl(Realm realm) {
        this.realm = realm;
    }

    @Override
    public Observable<List<Article>> getArticles() {
        return realm.asObservable()
                .map(it -> it.where(Article.class)
                        .equalTo("status", true)
                        .not()
                        .equalTo("is_deleted", true)
                        .findAll());
    }

    @Override
    public void refreshArticles(List<Article> data) {
        realm.copyToRealmOrUpdate(data);
    }

    @Override
    public void cleanArticles(Scheduler ioScheduler) {
        realm.asObservable()
                .subscribeOn(ioScheduler)
                .flatMapIterable(it -> it.where(Article.class)
                        .lessThan("publisher_time", System.currentTimeMillis() - TWO_WEEKS_INTERVAL)
                        .findAll())
                .subscribe(it -> it.deleteFromRealm());
    }
}
