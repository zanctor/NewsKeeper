package com.mozidev.newskeeper.presentation.publisher_details;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;

import com.mozidev.newskeeper.domain.publishers.Publisher;
import com.mozidev.newskeeper.presentation.common.BasePresenter;
import com.mozidev.newskeeper.presentation.publishers.PublisherViewModel;

import java.lang.reflect.Array;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by mozi on 31.08.16.
 */
@Singleton
public class PublisherDetailsPresenter extends BasePresenter<PublisherDetailsView, PublisherDetailsRouter> {

    private PublisherViewModel publisher;
    private Context context;

    @Inject
    public PublisherDetailsPresenter(Context context) {
        this.context = context;
    }

    public void init(PublisherViewModel publisher) {
        this.publisher = publisher;
    }

    @Override
    public void onStart() {
        getView().fillContent(publisher);
    }

    @Override
    public void onStop() {

    }

    public void visitSite() {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(publisher.getSite()));

        getRouter().visitSite(intent);
    }

    public void contactPublisher() {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("*/*");
        intent.putExtra(Intent.EXTRA_EMAIL, new String[]{ publisher.getEmail() });

        getRouter().contactPublisher(intent);
    }

    public void viewStream() {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(publisher.getStream()));
        try {
            context.getPackageManager().getApplicationInfo("com.google.android.youtube", 0);
            intent.setPackage("com.google.android.youtube");
        } catch (PackageManager.NameNotFoundException e) {

        }
        getRouter().viewStream(intent);
    }
}
