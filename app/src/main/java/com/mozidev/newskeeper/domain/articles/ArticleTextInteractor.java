package com.mozidev.newskeeper.domain.articles;

import com.mozidev.newskeeper.data.APIDataProviderImpl;
import com.mozidev.newskeeper.domain.common.Interactor;
import com.mozidev.newskeeper.presentation.injection.DomainModule;

import javax.inject.Inject;
import javax.inject.Named;

import io.realm.Realm;
import rx.Observable;
import rx.Scheduler;

public class ArticleTextInteractor extends Interactor<Void, Integer> {

    private final APIDataProviderImpl provider;
    private Realm realm;

    @Inject
    public ArticleTextInteractor(@Named(DomainModule.IO) Scheduler jobScheduler, @Named(DomainModule.UI) Scheduler uiScheduler, APIDataProviderImpl provider, Realm realm) {
        super(jobScheduler, uiScheduler);
        this.provider = provider;
        this.realm = realm;
    }

    @Override
    protected Observable<Void> buildObservable(Integer parameter) {
        provider.getArticleText(parameter)
                .subscribe(it -> {
                    realm.beginTransaction();
                    Article article = realm.where(Article.class)
                            .equalTo("id", parameter)
                            .findFirst();
                    article.setText(it);
                    realm.copyToRealmOrUpdate(article);
                    realm.commitTransaction();
                });

        return Observable.empty();
    }
}
