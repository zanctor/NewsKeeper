package com.mozidev.newskeeper.presentation.publishers;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.widget.CompoundButton;

import com.mozidev.newskeeper.Constants;
import com.mozidev.newskeeper.R;
import com.mozidev.newskeeper.domain.publishers.Publisher;
import com.mozidev.newskeeper.presentation.articles.ArticlesListActivity;
import com.mozidev.newskeeper.presentation.common.BaseActivity;
import com.mozidev.newskeeper.presentation.common.BasePresenter;
import com.mozidev.newskeeper.presentation.common.Layout;
import com.mozidev.newskeeper.presentation.injection.DaggerHelper;
import com.mozidev.newskeeper.presentation.publisher_details.PublisherDetailsActivity;

import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import io.realm.Realm;

@Layout(id = R.layout.activity_publishers_list)
public class PublishersListActivity extends BaseActivity implements PublishersListView, PublishersListRouter {

    @Inject
    PublishersPresenter publishersPresenter;

    @Bind(R.id.fab_view_articles)
    FloatingActionButton viewArticlesFab;

    private PublishersListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DaggerHelper.getMainComponent().inject(this);
        initToolbar(getToolbarTitle());
        toolbar.setNavigationIcon(null);
        viewArticlesFab.setOnClickListener(v -> publishersPresenter.checkAndGo());
    }

    @Override
    protected String getToolbarTitle() {
        return getString(R.string.publishers);
    }

    @Override
    protected BasePresenter getPresenter() {
        return publishersPresenter;
    }

    @Override
    protected int getMenuXML() {
        return R.menu.menu_check;
    }

    @Override
    protected Toolbar.OnMenuItemClickListener getItemMenuListener() {
        return item -> {
            if (item.getItemId() == R.id.select_unselect) {
                publishersPresenter.selectUnselect();
                return true;
            }
            return false;
        };
    }

    @Override
    public void openPublisher(PublisherViewModel publisher) {
        Intent intent = new Intent(this, PublisherDetailsActivity.class);
        intent.putExtra(Constants.EXTRA_PUBLISHER, publisher);
        startActivity(intent);
    }

    @Override
    public void openArticles() {
        startActivity(new Intent(this, ArticlesListActivity.class));
        finish();
    }

    @Override
    public void setPublishers(List<PublisherViewModel> data) {
        adapter = new PublishersListAdapter(data);
        adapter.setOnClickListener(v -> publishersPresenter.openPublisher((PublisherViewModel) v.getTag()));
        recycler.setAdapter(adapter);
    }

    @Override
    public void showNotificationsDialog() {
        new AlertDialog.Builder(this, R.style.DialogStyle)
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

    @Override
    public void showCheckDialog() {
        new AlertDialog.Builder(this, R.style.DialogStyle)
                .setMessage(R.string.dialog_check_message)
                .setNeutralButton(android.R.string.cancel, ((dialogInterface, i) -> {
                }))
                .setPositiveButton(android.R.string.yes, ((dialogInterface, i) -> {
                    publishersPresenter.selectUnselect();
                    publishersPresenter.openAricles();
                }))
                .create()
                .show();
    }

    @Override
    public void checkAll(boolean check) {
        changeItemTitle(check);
        adapter.checkAll(check);
    }

    @Override
    public void changeItemTitle(boolean condition) {
        toolbar.getMenu().findItem(R.id.select_unselect).setTitle(condition ? R.string.unselect_all : R.string.select_all);
    }

    @Override
    public void showDataDialog() {
        new AlertDialog.Builder(this, R.style.DialogStyle)
                .setMessage(R.string.data_warning)
                .setNeutralButton(android.R.string.ok, (dialogInterface, i) -> {

                })
                .setOnDismissListener(dialogInterface -> prefs.setFirstRun(false))
                .create()
                .show();
    }
}
