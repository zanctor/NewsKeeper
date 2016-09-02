package com.mozidev.newskeeper.presentation.categories;

import com.mozidev.newskeeper.domain.categories.Category;

import java.util.List;

/**
 * Created by mozi on 31.08.16.
 */
public interface CategoriesListView {

    void setCategories(List<Category> data);

    void checkAll(boolean check);

    void showCheckDialog();

}
