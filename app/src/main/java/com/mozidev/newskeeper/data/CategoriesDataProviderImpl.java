package com.mozidev.newskeeper.data;

import com.mozidev.newskeeper.domain.categories.CategoriesDataProvider;
import com.mozidev.newskeeper.domain.categories.Category;

import java.util.List;

import io.realm.Realm;
import rx.Observable;

/**
 * Created by mozi on 31.08.16.
 */
public class CategoriesDataProviderImpl implements CategoriesDataProvider {

    @Override
    public Observable<List<Category>> getCategories() {
        return Realm.getDefaultInstance()
                .asObservable()
                .map(it -> it.where(Category.class)
                        .not()
                        .equalTo("is_deleted", true)
                        .findAll());
    }

    @Override
    public void saveCategories(List<Category> data) {
        Realm.getDefaultInstance().copyToRealmOrUpdate(data);
    }
}
