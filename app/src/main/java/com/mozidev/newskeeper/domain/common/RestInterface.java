package com.mozidev.newskeeper.domain.common;

import com.mozidev.newskeeper.domain.articles.Article;
import com.mozidev.newskeeper.domain.categories.Category;
import com.mozidev.newskeeper.domain.publishers.Publisher;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by mozi on 31.08.16.
 */
public interface RestInterface {

    String API_URL = "http://46.101.127.19/v1/";

    @GET("articles")
    Observable<ApiResponse<List<Article>>> getArticles();

    @GET("categories")
    Observable<ApiResponse<List<Category>>> getCategories();

    @GET("publishers")
    Observable<ApiResponse<List<Publisher>>> getPublishers();

    @GET("articles/text/{id}")
    Observable<ApiResponse<String>> getArticleText(@Path("id") int id);

}
