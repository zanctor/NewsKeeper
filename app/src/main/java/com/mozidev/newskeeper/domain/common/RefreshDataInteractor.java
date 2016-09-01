package com.mozidev.newskeeper.domain.common;

import com.mozidev.newskeeper.data.APIDataProviderImpl;
import com.mozidev.newskeeper.presentation.injection.DomainModule;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import io.realm.Realm;
import io.realm.RealmObject;
import rx.Observable;
import rx.Scheduler;

/**
 * Created by mozi on 01.09.16.
 */
public class RefreshDataInteractor extends Interactor<Object, Void> {

    APIDataProviderImpl provider;
    Realm realm;

    @Inject
    public RefreshDataInteractor(@Named(DomainModule.IO) Scheduler jobScheduler, @Named(DomainModule.UI) Scheduler uiScheduler, APIDataProviderImpl provider) {
        super(jobScheduler, uiScheduler);
        this.provider = provider;
    }

    @Override
    protected Observable<Object> buildObservable(Void parameter) {
        return provider.getPublishers()
                .map(it -> {
                    realm = Realm.getDefaultInstance();
                    saveData(it);
                    provider.getCategories()
                            .subscribe(data -> {
                                saveData(data);
                                provider.getArticles()
                                        .subscribe(this::saveData);
                            });
                    return it;
                });
    }

    private void saveData(List<? extends RealmObject> data) {
        realm.beginTransaction();
        realm.copyToRealmOrUpdate(data);
        realm.commitTransaction();
    }
}
