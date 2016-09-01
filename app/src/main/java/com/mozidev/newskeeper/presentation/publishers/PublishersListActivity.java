package com.mozidev.newskeeper.presentation.publishers;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;

import com.mozidev.newskeeper.Constants;
import com.mozidev.newskeeper.R;
import com.mozidev.newskeeper.domain.publishers.Publisher;
import com.mozidev.newskeeper.presentation.articles.ArticlesListActivity;
import com.mozidev.newskeeper.presentation.common.BaseActivity;
import com.mozidev.newskeeper.presentation.common.BasePresenter;
import com.mozidev.newskeeper.presentation.publisher_details.PublisherDetailsActivity;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by mozi on 31.08.16.
 */
public class PublishersListActivity extends BaseActivity implements PublishersListView, PublishersListRouter {

    @Inject
    PublishersPresenter publishersPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initToolbar(getToolbarTitle());
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_publishers_list;
    }

    @Override
    protected BasePresenter getPresenter() {
        return publishersPresenter;
    }

    @Override
    public void openPublisher(Publisher publisher) {
        Intent intent = new Intent(this, PublisherDetailsActivity.class);
        intent.putExtra(Constants.EXTRA_PUBLISHER, publisher);
        startActivity(intent);
    }

    @Override
    public void openArticles() {
        startActivity(new Intent(this, ArticlesListActivity.class));
    }

    @Override
    public void setPublishers(List<Publisher> data) {
        PublishersListAdapter adapter = new PublishersListAdapter(data);
        adapter.setOnItemClickListener(null); //todo
        recycler.setAdapter(adapter);
    }

    @Override
    public void showNotificationsDialog() {
        new AlertDialog.Builder(this)
                .setMessage(R.string.notifications_question)
                .setPositiveButton(android.R.string.yes, (dialogInterface, i) -> {

                })
                .setNegativeButton(android.R.string.no, (dialogInterface, i) -> {
                    prefs.setShowNotifications(false);
                })
                .setOnDismissListener(dialogInterface -> prefs.setFirstRun(false))
                .create()
                .show();
    }
}
