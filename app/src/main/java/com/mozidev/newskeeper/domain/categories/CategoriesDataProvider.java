package com.mozidev.newskeeper.domain.categories;

import java.util.List;

import rx.Observable;

public interface CategoriesDataProvider {

    Observable<List<Category>> getCategories();

    void saveCategories(List<Category> data);

}
