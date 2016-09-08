package com.mozidev.newskeeper.domain.common.net;

import com.mozidev.newskeeper.domain.articles.Article;
import com.mozidev.newskeeper.domain.categories.Category;
import com.mozidev.newskeeper.domain.publishers.Publisher;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

public interface APIDataProvider {

    String API_URL = "http://46.101.127.19/v1/";

    @GET("articles")
    Observable<ApiResponse<List<Article>>> getArticles();

    @GET("categories")
    Observable<ApiResponse<List<Category>>> getCategories();

    @GET("publishers")
    Observable<ApiResponse<List<Publisher>>> getPublishers();

    @GET("articles/text/{id}")
    Observable<ApiResponse<String>> getArticleText(@Path("id") int id);

    @POST("devices/android")
    Observable<Object> postRegisterDevice(@Query("id") String id);

    @POST("devices/filter-android")
    Observable<Object> postSelectedFilters(@Query("id") String id, @Query("publishers_ids") List<Integer> publishers, @Query("category_ids") List<Integer> categories);

}
