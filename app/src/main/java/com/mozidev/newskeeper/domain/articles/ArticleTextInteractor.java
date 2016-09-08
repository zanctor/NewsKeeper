package com.mozidev.newskeeper.domain.articles;

import com.mozidev.newskeeper.data.APIDataProviderImpl;
import com.mozidev.newskeeper.domain.common.Interactor;
import com.mozidev.newskeeper.presentation.articles.ArticleViewModel;
import com.mozidev.newskeeper.presentation.injection.DomainModule;

import javax.inject.Inject;
import javax.inject.Named;

import io.realm.Realm;
import rx.Observable;
import rx.Scheduler;

public class ArticleTextInteractor extends Interactor<Void, ArticleViewModel> {

    private final APIDataProviderImpl provider;
    private Realm realm;

    @Inject
    public ArticleTextInteractor(@Named(DomainModule.IO) Scheduler jobScheduler, @Named(DomainModule.UI) Scheduler uiScheduler, APIDataProviderImpl provider, Realm realm) {
        super(jobScheduler, uiScheduler);
        this.provider = provider;
        this.realm = realm;
    }

    @Override
    protected Observable<Void> buildObservable(ArticleViewModel parameter) {
        return realm.asObservable()
                .map(it -> {
                    provider.getArticleText(parameter.getId())
                            .doOnNext(s -> {
                                realm.beginTransaction();
                                parameter.setText(s);
                                realm.where(Article.class)
                                        .equalTo("id", parameter.getId())
                                        .findFirst()
                                        .setText(s);
                                realm.commitTransaction();
                            });

                    return null;
                });
    }
}
