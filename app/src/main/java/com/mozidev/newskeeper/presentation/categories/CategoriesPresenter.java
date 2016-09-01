package com.mozidev.newskeeper.presentation.categories;

import com.mozidev.newskeeper.domain.categories.Category;
import com.mozidev.newskeeper.domain.categories.GetCategoriesInteractor;
import com.mozidev.newskeeper.presentation.common.BasePresenter;

import java.util.List;

import javax.inject.Inject;

import rx.Subscriber;

/**
 * Created by mozi on 31.08.16.
 */
public class CategoriesPresenter extends BasePresenter<CategoriesListView, Void> {

    private final GetCategoriesInteractor getCategoriesInteractor;

    @Inject
    public CategoriesPresenter(GetCategoriesInteractor getCategoriesInteractor) {
        this.getCategoriesInteractor = getCategoriesInteractor;
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
                getView().setCategories(categories);
            }
        });
    }

    @Override
    public void onStop() {
        getCategoriesInteractor.unsubscribe();
    }
}
