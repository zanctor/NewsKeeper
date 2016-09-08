package com.mozidev.newskeeper.presentation.publisher_details;

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
import com.mozidev.newskeeper.domain.publishers.Publisher;
import com.mozidev.newskeeper.presentation.common.BaseActivity;
import com.mozidev.newskeeper.presentation.common.BasePresenter;
import com.mozidev.newskeeper.presentation.common.Layout;
import com.mozidev.newskeeper.presentation.injection.DaggerHelper;
import com.mozidev.newskeeper.presentation.publishers.PublisherViewModel;

import javax.inject.Inject;

import butterknife.Bind;

@Layout(id = R.layout.activity_publisher_details)
public class PublisherDetailsActivity extends BaseActivity implements PublisherDetailsRouter, PublisherDetailsView {

    @Inject
    PublisherDetailsPresenter publisherDetailsPresenter;

    @Bind(R.id.logo_publisher)
    ImageView publisherLogo;
    @Bind(R.id.name_publisher)
    TextView publisherName;
    @Bind(R.id.description_publisher)
    TextView publisherDescription;
    @Bind(R.id.button_visit_site)
    Button visitSiteButton;
    @Bind(R.id.button_view_stream)
    Button viewStreamButton;

    private PublisherViewModel publisher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DaggerHelper.getMainComponent().inject(this);
        initToolbar(getToolbarTitle());
        publisher = (PublisherViewModel) getIntent().getExtras().getSerializable(Constants.EXTRA_PUBLISHER);
        if (publisher != null) {
            publisherDetailsPresenter.init(publisher);
        }
    }

    @Override
    protected int getMenuXML() {
        return R.menu.menu_publisher;
    }

    @Override
    protected String getToolbarTitle() {
        return getString(R.string.app_name);
    }

    @Override
    protected Toolbar.OnMenuItemClickListener getItemMenuListener() {
        return item -> {
            if (item.getItemId() == R.id.action_contact) {
                publisherDetailsPresenter.contactPublisher();

                return true;
            }

            return false;
        };
    }

    @Override
    public void contactPublisher(Intent intent) {
        Intent.createChooser(intent, getString(R.string.send));
    }

    @Override
    public void visitSite(Intent intent) {
        startActivity(intent);
    }

    @Override
    public void viewStream(Intent intent) {
        startActivity(intent);
    }

    @Override
    public void fillContent(PublisherViewModel publisher) {
        Glide.with(this)
                .load(publisher.getLogo())
                .placeholder(R.drawable.placeholder)
                .into(publisherLogo);
        publisherName.setText(publisher.getPublisherName());
        publisherDescription.setText(publisher.getDescription());
        if (publisher.getSite() == null || publisher.getSite().equals("")) {
            visitSiteButton.setVisibility(View.GONE);
        } else {
            visitSiteButton.setOnClickListener(v -> publisherDetailsPresenter.visitSite());
        }
        if (publisher.getStream() == null || publisher.getStream().equals("")) {
            viewStreamButton.setVisibility(View.GONE);
        } else {
            viewStreamButton.setOnClickListener(v -> publisherDetailsPresenter.viewStream());
        }
    }

    @Override
    protected BasePresenter getPresenter() {
        return publisherDetailsPresenter;
    }
}
