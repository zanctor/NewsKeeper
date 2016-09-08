package com.mozidev.newskeeper.domain.categories;

import com.mozidev.newskeeper.domain.common.Interactor;
import com.mozidev.newskeeper.presentation.categories.CategoryViewModel;
import com.mozidev.newskeeper.presentation.injection.DomainModule;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import rx.Observable;
import rx.Scheduler;

/**
 * Created by mozi on 02.09.16.
 */
public class SaveCategoriesInteractor extends Interactor<Void, List<CategoryViewModel>> {

    CategoriesDataProvider provider;

    @Inject
    public SaveCategoriesInteractor(@Named(DomainModule.IO) Scheduler jobScheduler, @Named(DomainModule.UI) Scheduler uiScheduler, CategoriesDataProvider provider) {
        super(jobScheduler, uiScheduler);
        this.provider = provider;
    }

    @Override
    protected Observable<Void> buildObservable(List<CategoryViewModel> parameter) {
        provider.saveCategories(parameter);

        return Observable.empty();
    }
}
