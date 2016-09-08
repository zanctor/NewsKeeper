package com.mozidev.newskeeper.domain.categories;

import com.mozidev.newskeeper.presentation.categories.CategoryViewModel;

import java.util.List;

import rx.Observable;

public interface CategoriesDataProvider {

    Observable<List<Category>> getCategories();

    void saveCategories(List<CategoryViewModel> data);

}
