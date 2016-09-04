package com.mozidev.newskeeper.presentation.article_details;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.mozidev.newskeeper.Constants;
import com.mozidev.newskeeper.R;
import com.mozidev.newskeeper.domain.articles.Article;
import com.mozidev.newskeeper.presentation.common.BaseActivity;
import com.mozidev.newskeeper.presentation.common.BasePresenter;
import com.mozidev.newskeeper.presentation.common.Layout;

import javax.inject.Inject;

import butterknife.BindView;

@Layout(id = R.layout.activity_article_details)
public class ArticleDetailsActivity extends BaseActivity implements ArticleDetailsView, ArticleDetailsRouter {

    @Inject
    ArticleDetailsPresenter articleDetailsPresenter;

    @BindView(R.id.image_article)
    ImageView articleImage;
    @BindView(R.id.title_article)
    TextView articleTitle;
    @BindView(R.id.text_article)
    TextView articleText;
    @BindView(R.id.button_visit_site)
    Button visitSiteButton;
    @BindView(R.id.button_view_video)
    Button viewVideoButton;

    private Article article;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        article = (Article) getIntent().getSerializableExtra(Constants.EXTRA_ARTICLE);
        articleDetailsPresenter.init(article);
    }

    @Override
    protected BasePresenter getPresenter() {
        return articleDetailsPresenter;
    }

    @Override
    protected int getMenuXML() {
        return R.menu.menu_share;
    }

    @Override
    protected Toolbar.OnMenuItemClickListener getItemMenuListener() {
        return item -> {
            if (item.getItemId() == R.id.action_share) {
               articleDetailsPresenter.shareArticle();

                return true;
            }

            return false;
        };
    }

    @Override
    public void showVideo(Intent intent) {
        startActivity(intent);
    }

    @Override
    public void showLink(Intent intent) {
        startActivity(intent);
    }

    @Override
    public void fillContent(Article article) {
        if (article.getVideo() == null) {
            viewVideoButton.setVisibility(View.GONE);
        } else {
            viewVideoButton.setOnClickListener(v -> articleDetailsPresenter.showVideo());
        }
        if (article.getLink() == null) {
            visitSiteButton.setVisibility(View.GONE);
        } else {
            visitSiteButton.setOnClickListener(v -> articleDetailsPresenter.showLink());
        }
        Glide.with(this)
                .load(article.getImage())
                .centerCrop()
                .into(articleImage);
        articleText.setText(article.getText());
        articleTitle.setText(article.getTitle());
    }

    @Override
    public void setToolbar(String title) {
        initToolbar(title);
    }
}
