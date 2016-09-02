package com.mozidev.newskeeper.presentation.categories;

import com.mozidev.newskeeper.domain.categories.Category;
import com.mozidev.newskeeper.domain.categories.GetCategoriesInteractor;
import com.mozidev.newskeeper.domain.categories.SaveCategoriesInteractor;
import com.mozidev.newskeeper.presentation.common.BasePresenter;

import java.util.List;

import javax.inject.Inject;

import rx.Subscriber;

/**
 * Created by mozi on 31.08.16.
 */
public class CategoriesPresenter extends BasePresenter<CategoriesListView, Void> {

    private final GetCategoriesInteractor getCategoriesInteractor;
    private final SaveCategoriesInteractor saveCategoriesInteractor;
    private List<Category> data;

    @Inject
    public CategoriesPresenter(GetCategoriesInteractor getCategoriesInteractor, SaveCategoriesInteractor saveCategoriesInteractor) {
        this.getCategoriesInteractor = getCategoriesInteractor;
        this.saveCategoriesInteractor = saveCategoriesInteractor;
    }

    @Override
    public void onStart() {
        getCategoriesInteractor.execute(new Subscriber<List<Category>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(List<Category> categories) {
                data = categories;
                getView().setCategories(categories);
            }
        });
    }

    @Override
    public void onStop() {
        getCategoriesInteractor.unsubscribe();
    }

    public void selectUnselect() {
        for (Category category : data) {
            if (category.isChecked()) {
                getView().checkAll(true);
                return;
            }
        }
        getView().checkAll(false);
    }

    private boolean isAtLeastOneSelected() {
        for (Category category : data) {
            if (category.isChecked()) {
                return true;
            }
        }
        return false;
    }

    public void checkAndReturn() {
        if (isAtLeastOneSelected()) {
            saveData();
        } else {
            showCheckDialog();
        }
    }

    public void showCheckDialog() {
        getView().showCheckDialog();
    }

    public void saveData() {
        saveCategoriesInteractor.execute(data, new Subscriber<Void>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(Void aVoid) {
                ((CategoriesListActivity) getView()).finish();
            }
        });
    }
}
