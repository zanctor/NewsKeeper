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

import java.util.List;

import javax.inject.Inject;

@Layout(id = R.layout.activity_categories_list)
public class CategoriesListActivity extends BaseActivity implements CategoriesListView {

    @Inject
    CategoriesPresenter categoriesPresenter;

    private CategoriesListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
    public void setCategories(List<Category> data) {
        recycler.setLayoutManager(new LinearLayoutManager(this));
        adapter = new CategoriesListAdapter(data);
        adapter.setOnItemClickListener(v -> {
            Category category = ((Category) v.getTag());
            category.setChecked(!category.isChecked());
            adapter.notifyDataSetChanged();
        });
        recycler.setAdapter(adapter);
    }

    @Override
    public void checkAll(boolean check) {
        toolbar.getMenu().findItem(R.id.select_unselect).setTitle(check ? R.string.unselect_all : R.string.select_all);
        adapter.checkAll(check);
    }

    @Override
    public void showCheckDialog() {
        new AlertDialog.Builder(this)
                .setMessage(R.string.dialog_check_message)
                .setNeutralButton(android.R.string.cancel, ((dialogInterface, i) -> {
                }))
                .setPositiveButton(android.R.string.yes, ((dialogInterface, i) -> {
                    categoriesPresenter.selectUnselect();
                    finish();
                }))
                .create()
                .show();
    }
}
