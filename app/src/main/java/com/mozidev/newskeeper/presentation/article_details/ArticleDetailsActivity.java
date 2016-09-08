package com.mozidev.newskeeper.presentation.article_details;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.mozidev.newskeeper.Constants;
import com.mozidev.newskeeper.R;
import com.mozidev.newskeeper.domain.articles.Article;
import com.mozidev.newskeeper.presentation.articles.ArticleViewModel;
import com.mozidev.newskeeper.presentation.common.BaseActivity;
import com.mozidev.newskeeper.presentation.common.BasePresenter;
import com.mozidev.newskeeper.presentation.common.Layout;
import com.mozidev.newskeeper.presentation.injection.DaggerHelper;

import javax.inject.Inject;

import butterknife.Bind;

@Layout(id = R.layout.activity_article_details)
public class ArticleDetailsActivity extends BaseActivity implements ArticleDetailsView, ArticleDetailsRouter {

    @Inject
    ArticleDetailsPresenter articleDetailsPresenter;

    @Bind(R.id.image_article)
    ImageView articleImage;
    @Bind(R.id.title_article)
    TextView articleTitle;
    @Bind(R.id.text_article)
    TextView articleText;
    @Bind(R.id.button_visit_site)
    Button visitSiteButton;
    @Bind(R.id.button_view_video)
    Button viewVideoButton;

    private ArticleViewModel article;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DaggerHelper.getMainComponent().inject(this);
        initToolbar(getToolbarTitle());
        article = (ArticleViewModel) getIntent().getSerializableExtra(Constants.EXTRA_ARTICLE);
        articleDetailsPresenter.init(article);
    }

    @Override
    protected String getToolbarTitle() {
        return getString(R.string.app_name);
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
    public void fillContent(ArticleViewModel article) {
        String image = article.getImage();
        if (article.getVideo() == null) {
            viewVideoButton.setVisibility(View.GONE);
        } else {
            if (article.getImage() == null) {
                image = "http://img.youtube.com/vi/" + Uri.parse(article.getVideo()).getQueryParameter("v") + "/0.jpg";
            }
            viewVideoButton.setOnClickListener(v -> articleDetailsPresenter.showVideo());
        }
        if (article.getLink() == null) {
            visitSiteButton.setVisibility(View.GONE);
        } else {
            visitSiteButton.setOnClickListener(v -> articleDetailsPresenter.showLink());
        }
        articleTitle.setText(article.getTitle());
        Glide.with(this)
                .load(image)
                .placeholder(R.drawable.placeholder)
                .centerCrop()
                .into(articleImage);
        articleText.setText(Html.fromHtml(article.getText()));
    }

}
