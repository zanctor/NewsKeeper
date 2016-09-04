package com.mozidev.newskeeper.presentation.article_details;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;

import com.mozidev.newskeeper.R;
import com.mozidev.newskeeper.domain.articles.Article;
import com.mozidev.newskeeper.domain.articles.ArticleTextInteractor;
import com.mozidev.newskeeper.presentation.common.BasePresenter;

import javax.inject.Inject;

import rx.Subscriber;

/**
 * Created by mozi on 31.08.16.
 */
public class ArticleDetailsPresenter extends BasePresenter<ArticleDetailsView, ArticleDetailsRouter> {

    private Article article;
    private Context context;

    @Inject
    public ArticleDetailsPresenter(Context context) {
        this.context = context;
    }

    public void init(Article article) {
        this.article = article;
        getView().setToolbar(article.getTitle());
    }

    @Override
    public void onStart() {
        getView().fillContent(article);
    }

    @Override
    public void onStop() {

    }

    public void showLink() {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(article.getLink()));
        getRouter().showLink(intent);
    }

    public void showVideo() {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        try {
            context.getPackageManager().getApplicationInfo("com.google.android.youtube", 0);
            intent.setPackage("com.google.android.youtube");
        } catch (PackageManager.NameNotFoundException e) {

        }
        intent.setData(Uri.parse(article.getVideo()));
        getRouter().showLink(intent);
    }

    public void shareArticle() {
        Intent intent = new Intent();
        intent.setType("*/*");
        intent.putExtra(Intent.EXTRA_STREAM, Uri.parse(article.getImage()));
        intent.putExtra(Intent.EXTRA_TEXT, String.format("%s\n\n%s", article.getTitle(), article.getText()));
        Intent.createChooser(intent, context.getString(R.string.share));
    }
}
