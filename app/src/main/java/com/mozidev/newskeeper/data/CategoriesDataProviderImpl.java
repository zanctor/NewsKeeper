package com.mozidev.newskeeper.data;

import com.mozidev.newskeeper.domain.categories.CategoriesDataProvider;
import com.mozidev.newskeeper.domain.categories.Category;
import com.mozidev.newskeeper.presentation.categories.CategoryViewModel;

import java.util.List;

import io.realm.Realm;
import rx.Observable;

/**
 * Created by mozi on 31.08.16.
 */
public class CategoriesDataProviderImpl implements CategoriesDataProvider {

    @Override
    public Observable<List<Category>> getCategories() {
        return Observable.just(Realm.getDefaultInstance()
                .where(Category.class)
                .equalTo("is_deleted", false)
                .findAll());
    }

    @Override
    public void saveCategories(List<CategoryViewModel> data) {
        Realm.getDefaultInstance().beginTransaction();
        Realm.getDefaultInstance().copyToRealmOrUpdate(Category.create(data));
        Realm.getDefaultInstance().commitTransaction();
    }
}
