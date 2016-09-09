package com.mozidev.newskeeper.presentation.categories;

import java.util.List;

/**
 * Created by mozi on 31.08.16.
 */
public interface CategoriesListView {

    void setCategories(List<CategoryViewModel> data);

    void checkAll(boolean check);

    void changeItemTitle(boolean condition);

}
