package com.mozidev.newskeeper.presentation.publishers;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.mozidev.newskeeper.R;
import com.mozidev.newskeeper.domain.publishers.Publisher;
import com.mozidev.newskeeper.presentation.injection.DaggerHelper;

import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import io.realm.Realm;

/**
 * Created by mozi on 31.08.16.
 */
public class PublishersListAdapter extends RecyclerView.Adapter<PublishersListAdapter.ViewHolder> {

    @Inject
    Context context;
    @Inject
    PublishersPresenter presenter;
    private List<PublisherViewModel> data;
    private View.OnClickListener onClickListener;

    public PublishersListAdapter(List<PublisherViewModel> data) {
        this.data = data;
    }

    public void setOnClickListener(View.OnClickListener onClickListener) {
        DaggerHelper.getMainComponent().inject(this);
        this.onClickListener = onClickListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_publisher, parent, false);
        return new ViewHolder(convertView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.bind(data.get(position));
    }

    @Override
    public int getItemCount() {
        return data == null ? 0 : data.size();
    }

    public void checkAll(boolean check) {
        if (data != null) {
            for (PublisherViewModel publisher : data) {
                publisher.setChecked(check);
            }
            notifyDataSetChanged();
        }
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.publisher_name)
        TextView publisherName;
        @Bind(R.id.publisher_check)
        CheckBox publisherCheck;
        @Bind(R.id.publisher_logo)
        ImageView publisherLogo;
        @Bind(R.id.container)
        LinearLayout container;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }

        public void bind(PublisherViewModel publisher) {
            container.setOnClickListener(onClickListener);
            publisherName.setText(publisher.getPublisherName());
            publisherCheck.setChecked(publisher.isChecked());
            publisherCheck.setOnClickListener(v -> {
                PublisherViewModel model = ((PublisherViewModel) container.getTag());
                publisherCheck.setChecked(!model.isChecked());
                model.setChecked(!model.isChecked());
                presenter.getView().changeItemTitle(presenter.isAtLeastOneSelected());
            });
            Glide.with(context)
                    .load(publisher.getLogo())
                    .placeholder(R.drawable.placeholder)
                    .into(publisherLogo);
            container.setTag(publisher);
        }
    }
}
