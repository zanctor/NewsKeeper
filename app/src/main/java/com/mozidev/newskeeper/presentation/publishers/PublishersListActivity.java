package com.mozidev.newskeeper.presentation.publishers;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.widget.Button;

import com.mozidev.newskeeper.Constants;
import com.mozidev.newskeeper.R;
import com.mozidev.newskeeper.domain.publishers.Publisher;
import com.mozidev.newskeeper.presentation.articles.ArticlesListActivity;
import com.mozidev.newskeeper.presentation.common.BaseActivity;
import com.mozidev.newskeeper.presentation.common.BasePresenter;
import com.mozidev.newskeeper.presentation.common.Layout;
import com.mozidev.newskeeper.presentation.publisher_details.PublisherDetailsActivity;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

@Layout(id = R.layout.activity_publishers_list)
public class PublishersListActivity extends BaseActivity implements PublishersListView, PublishersListRouter {

    @Inject
    PublishersPresenter publishersPresenter;

    @BindView(R.id.fab_view_articles)
    Button viewArticlesFab;

    private PublishersListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initToolbar(getToolbarTitle());
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
        adapter = new PublishersListAdapter(data);
        adapter.setOnItemClickListener(v -> publishersPresenter.openPublisher((Publisher) v.getTag()));
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

    @Override
    public void showCheckDialog() {
        new AlertDialog.Builder(this)
                .setMessage(R.string.dialog_check_message)
                .setNeutralButton(android.R.string.cancel, ((dialogInterface, i) -> {
                }))
                .setPositiveButton(android.R.string.yes, ((dialogInterface, i) -> {
                    publishersPresenter.selectUnselect();
                    finish();
                }))
                .create()
                .show();
    }

    @Override
    public void checkAll(boolean check) {
        toolbar.getMenu().findItem(R.id.select_unselect).setTitle(check ? R.string.unselect_all : R.string.select_all);
        adapter.checkAll(check);
    }

    @Override
    public void showDataDialog() {
        new AlertDialog.Builder(this)
                .setMessage(R.string.data_warning)
                .setNeutralButton(android.R.string.ok, (dialogInterface, i) -> {

                })
                .setOnDismissListener(dialogInterface -> prefs.setFirstRun(false))
                .create()
                .show();
    }
}
