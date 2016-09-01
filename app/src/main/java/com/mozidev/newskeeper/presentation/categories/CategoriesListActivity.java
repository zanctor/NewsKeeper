package com.mozidev.newskeeper.presentation.categories;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;

import com.mozidev.newskeeper.R;
import com.mozidev.newskeeper.domain.categories.Category;
import com.mozidev.newskeeper.presentation.common.BaseActivity;
import com.mozidev.newskeeper.presentation.common.BasePresenter;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by mozi on 31.08.16.
 */
public class CategoriesListActivity extends BaseActivity implements CategoriesListView {

    @Inject
    CategoriesPresenter categoriesPresenter;

    private CategoriesListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initToolbar(getToolbarTitle());
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_categories_list;
    }

    @Override
    protected BasePresenter getPresenter() {
        return categoriesPresenter;
    }

    @Override
    public void setCategories(List<Category> data) {
        recycler.setLayoutManager(new LinearLayoutManager(this));
        adapter = new CategoriesListAdapter(data);
        adapter.setOnItemClickListener(v -> {
            Category category = ((Category) v.getTag());
            category.checked = !category.checked;
            adapter.notifyDataSetChanged();
        });
        recycler.setAdapter(adapter);
    }
}
