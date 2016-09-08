package com.mozidev.newskeeper.domain.common;

import android.content.Context;
import android.provider.Settings;

import com.mozidev.newskeeper.data.APIDataProviderImpl;
import com.mozidev.newskeeper.domain.categories.Category;
import com.mozidev.newskeeper.domain.publishers.Publisher;
import com.mozidev.newskeeper.presentation.injection.DomainModule;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import io.realm.Realm;
import rx.Observable;
import rx.Scheduler;

public class SaveFiltersInteractor extends Interactor<Object, Object> {

    APIDataProviderImpl provider;
    Context context;
    Realm realm;

    @Inject
    public SaveFiltersInteractor(@Named(DomainModule.IO) Scheduler jobScheduler, @Named(DomainModule.UI) Scheduler uiScheduler, APIDataProviderImpl provider, Context context, Realm realm) {
        super(jobScheduler, uiScheduler);
        this.provider = provider;
        this.context = context;
        this.realm = realm;
    }

    @Override
    protected Observable<Object> buildObservable(Object obj) {
        String id = Settings.Secure.getString(context.getContentResolver(),
                Settings.Secure.ANDROID_ID);

        List<Integer> publishers_ids = new ArrayList<>();
        List<Publisher> publishers = realm.where(Publisher.class)
                .equalTo("checked", true)
                .findAll();
        for (Publisher publisher : publishers) {
            if (publisher.isChecked()) publishers_ids.add(publisher.getId());
        }

        List<Integer> categories_ids = new ArrayList<>();
        List<Category> categories = realm.where(Category.class)
                .equalTo("checked", true)
                .findAll();
        for (Category category : categories) {
            if (category.isChecked()) categories_ids.add(category.getId());
        }

        return provider.postSelectedFilters(id, publishers_ids, categories_ids);
    }
}
