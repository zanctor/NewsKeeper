package com.mozidev.newskeeper.presentation.articles;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.mozidev.newskeeper.Constants;
import com.mozidev.newskeeper.R;
import com.mozidev.newskeeper.domain.articles.Article;
import com.mozidev.newskeeper.presentation.article_details.ArticleDetailsActivity;
import com.mozidev.newskeeper.presentation.categories.CategoriesListActivity;
import com.mozidev.newskeeper.presentation.common.BaseActivity;
import com.mozidev.newskeeper.presentation.common.BasePresenter;
import com.mozidev.newskeeper.presentation.common.Layout;
import com.mozidev.newskeeper.presentation.publishers.PublishersListActivity;
import com.mozidev.newskeeper.presentation.settings.SettingsActivity;

import java.util.List;

import javax.inject.Inject;

@Layout(id = R.layout.activity_articles_list)
public class ArticlesListActivity extends BaseActivity implements ArticlesListRouter, ArticlesListView {

    @Inject
    ArticlesPresenter articlesPresenter;

    @Override
    protected int getMenuXML() {
        return R.menu.menu_articles;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initToolbar(getToolbarTitle());
        toolbar.setNavigationOnClickListener(v -> {
            startActivity(new Intent(this, PublishersListActivity.class));
            finish();
        });
    }

    @Override
    protected Toolbar.OnMenuItemClickListener getItemMenuListener() {
        return item -> {
            switch (item.getItemId()) {
                case R.id.action_categories:
                    articlesPresenter.openCategories();
                    return true;

                case R.id.action_settings:
                    articlesPresenter.openSettings();
                    return true;
            }
            return false;
        };
    }

    @Override
    protected BasePresenter getPresenter() {
        return articlesPresenter;
    }

    @Override
    public void openSettings() {
        startActivity(new Intent(this, SettingsActivity.class));
    }

    @Override
    public void openCategories() {
        startActivity(new Intent(this, CategoriesListActivity.class));
    }

    @Override
    public void openArticle(Article article) {
        Intent intent = new Intent(this, ArticleDetailsActivity.class);
        intent.putExtra(Constants.EXTRA_ARTICLE, article);
        startActivity(intent);
    }

    @Override
    public void updateArticles(List<Article> data) {
        ArticlesListAdapter adapter = new ArticlesListAdapter(data);
        adapter.setOnItemClickListener(v -> articlesPresenter.openArticle((Article) v.getTag()));
        recycler.setAdapter(adapter);
    }

    @Override
    protected String getToolbarTitle() {
        return getString(R.string.articles);
    }
}
