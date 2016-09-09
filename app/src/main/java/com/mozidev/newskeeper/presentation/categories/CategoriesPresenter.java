package com.mozidev.newskeeper.presentation.categories;

import android.content.Context;

import com.mozidev.newskeeper.domain.categories.Category;
import com.mozidev.newskeeper.domain.categories.GetCategoriesInteractor;
import com.mozidev.newskeeper.domain.categories.SaveCategoriesInteractor;
import com.mozidev.newskeeper.domain.common.SaveFiltersInteractor;
import com.mozidev.newskeeper.domain.common.util.NetworkUtils;
import com.mozidev.newskeeper.presentation.common.BasePresenter;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import rx.Subscriber;

/**
 * Created by mozi on 31.08.16.
 */
@Singleton
public class CategoriesPresenter extends BasePresenter<CategoriesListView, Void> {

    private final GetCategoriesInteractor getCategoriesInteractor;
    private final SaveCategoriesInteractor saveCategoriesInteractor;
    private final SaveFiltersInteractor saveFiltersInteractor;
    private List<CategoryViewModel> data;
    private Context context;

    @Inject
    public CategoriesPresenter(GetCategoriesInteractor getCategoriesInteractor, SaveCategoriesInteractor saveCategoriesInteractor, SaveFiltersInteractor saveFiltersInteractor, Context context) {
        this.getCategoriesInteractor = getCategoriesInteractor;
        this.saveCategoriesInteractor = saveCategoriesInteractor;
        this.saveFiltersInteractor = saveFiltersInteractor;
        this.context = context;
    }

    @Override
    public void onStart() {
        getCategoriesInteractor.execute(new Subscriber<List<Category>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                e.printStackTrace();
            }

            @Override
            public void onNext(List<Category> categories) {
                data = CategoryViewModel.create(categories);
                getView().setCategories(data);
            }
        });
    }

    @Override
    public void onStop() {
        getCategoriesInteractor.unsubscribe();
    }

    void selectUnselect() {
        if (data != null) {
            for (CategoryViewModel category : data) {
                if (category.isChecked()) {
                    getView().checkAll(false);
                    return;
                }
            }
            getView().checkAll(true);
        }
    }

    boolean isAtLeastOneSelected() {
        for (CategoryViewModel category : data) {
            if (category.isChecked()) {
                return true;
            }
        }
        return false;
    }

    void checkAndReturn() {
        if (data != null) {
            saveData();
        } else {
            ((CategoriesListActivity) getView()).finish();
        }
    }

    private void saveData() {
        saveCategoriesInteractor.execute(data, new Subscriber<Void>() {
            @Override
            public void onCompleted() {
                if (NetworkUtils.isConnected(context)) {
                    saveFiltersInteractor.execute(new Subscriber<Object>() {

                        @Override
                        public void onNext(Object o) {

                        }

                        @Override
                        public void onError(Throwable e) {

                        }

                        @Override
                        public void onCompleted() {

                        }
                    });
                }
                ((CategoriesListActivity) getView()).finish();
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(Void aVoid) {

            }
        });
    }
}
