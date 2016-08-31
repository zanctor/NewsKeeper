package com.mozidev.newskeeper.domain.categories;

import com.mozidev.newskeeper.domain.common.Interactor;
import com.mozidev.newskeeper.presentation.injection.DomainModule;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import rx.Observable;
import rx.Scheduler;

public class GetCategoriesInteractor extends Interactor<List<Category>, Void> {

    private final CategoriesDataProvider categoriesDataProvider;

    @Inject
    public GetCategoriesInteractor(@Named(DomainModule.JOB) Scheduler jobScheduler, @Named(DomainModule.UI) Scheduler uiScheduler, CategoriesDataProvider categoriesDataProvider) {
        super(jobScheduler, uiScheduler);
        this.categoriesDataProvider = categoriesDataProvider;
    }

    @Override
    protected Observable<List<Category>> buildObservable(Void parameter) {
        return categoriesDataProvider.getCategories();
    }
}
