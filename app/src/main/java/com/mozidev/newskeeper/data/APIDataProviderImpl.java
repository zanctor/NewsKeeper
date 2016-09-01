package com.mozidev.newskeeper.data;

import com.mozidev.newskeeper.domain.articles.Article;
import com.mozidev.newskeeper.domain.categories.Category;
import com.mozidev.newskeeper.domain.common.net.APIDataProvider;
import com.mozidev.newskeeper.domain.common.net.ApiResponse;
import com.mozidev.newskeeper.domain.publishers.Publisher;

import java.util.List;

import javax.inject.Inject;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class APIDataProviderImpl {

    private APIDataProvider provider;

    @Inject
    public APIDataProviderImpl(APIDataProvider provider) {
        this.provider = provider;
    }

    private <T> Observable.Transformer<ApiResponse<T>, T> extractResponse() {
        return request -> request
                .map(it -> it.data);
    }

    public Observable<List<Article>> getArticles() {
        return provider.getArticles()
                .compose(extractResponse());
    }

    public Observable<List<Publisher>> getPublishers() {
        return provider.getPublishers()
                .compose(extractResponse());
    }

    public Observable<List<Category>> getCategories() {
        return provider.getCategories()
                .compose(extractResponse());
    }

    public Observable<String> getArticleText(int id) {
        return provider.getArticleText(id)
                .compose(extractResponse());
    }

    public Observable<?> postRegisterDevice(String token) {
        return provider.postRegisterDevice(token)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Observable<?> postSelectedFilters(String id, List<Integer> categories, List<Integer> publishers) {
        return provider.postSelectedFilters(id, categories, publishers);
    }

}
