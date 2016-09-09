package com.mozidev.newskeeper.presentation.categories;

import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;

import com.mozidev.newskeeper.R;
import com.mozidev.newskeeper.domain.categories.Category;
import com.mozidev.newskeeper.presentation.common.BaseActivity;
import com.mozidev.newskeeper.presentation.common.BasePresenter;
import com.mozidev.newskeeper.presentation.common.Layout;
import com.mozidev.newskeeper.presentation.injection.DaggerHelper;

import java.util.List;

import javax.inject.Inject;

import io.realm.Realm;

@Layout(id = R.layout.activity_categories_list)
public class CategoriesListActivity extends BaseActivity implements CategoriesListView {

    @Inject
    CategoriesPresenter categoriesPresenter;

    private CategoriesListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DaggerHelper.getMainComponent().inject(this);
        initToolbar(getToolbarTitle());
        toolbar.setNavigationOnClickListener(v -> categoriesPresenter.checkAndReturn());
    }

    @Override
    protected Toolbar.OnMenuItemClickListener getItemMenuListener() {
        return item -> {
            if (item.getItemId() == R.id.select_unselect) {
                categoriesPresenter.selectUnselect();
                return true;
            }
            return false;
        };
    }

    @Override
    protected int getMenuXML() {
        return R.menu.menu_check;
    }

    @Override
    protected BasePresenter getPresenter() {
        return categoriesPresenter;
    }

    @Override
    public void setCategories(List<CategoryViewModel> data) {
        recycler.setLayoutManager(new LinearLayoutManager(this));
        adapter = new CategoriesListAdapter(data);
        adapter.setOnItemClickListener(v -> {
            CategoryViewModel category = ((CategoryViewModel) v.getTag());
            category.setChecked(!category.isChecked());
            adapter.notifyDataSetChanged();
            changeItemTitle(categoriesPresenter.isAtLeastOneSelected());
        });
        recycler.setAdapter(adapter);
    }

    @Override
    public void checkAll(boolean check) {
        changeItemTitle(check);
        adapter.checkAll(check);
    }

    @Override
    public void changeItemTitle(boolean condition) {
        toolbar.getMenu().findItem(R.id.select_unselect).setTitle(condition ? R.string.unselect_all : R.string.select_all);
    }

    @Override
    public void onBackPressed() {
        categoriesPresenter.checkAndReturn();
    }

    @Override
    protected String getToolbarTitle() {
        return getString(R.string.categories);
    }

}
