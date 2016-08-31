package com.mozidev.newskeeper.presentation.article_details;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.mozidev.newskeeper.NewsKeeper;
import com.mozidev.newskeeper.R;
import com.mozidev.newskeeper.domain.publishers.Publisher;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by mozi on 31.08.16.
 */
public class ArticlesListAdapter extends RecyclerView.Adapter<ArticlesListAdapter.ViewHolder> {

    @Inject
    private Context context;
    private List<Publisher> data;
    private View.OnClickListener onItemClickListener;
    private View.OnClickListener checkBoxListener;

    public ArticlesListAdapter(List<Publisher> data) {
        NewsKeeper.getComponent().inject(this);
        this.data = data;
    }

    public void setOnItemClickListener(View.OnClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public void setCheckBoxListener(View.OnClickListener checkBoxListener) {
        this.checkBoxListener = checkBoxListener;
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

    class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.publisher_title)
        TextView publisherTitle;
        @BindView(R.id.publisher_check)
        CheckBox publisherCheck;
        @BindView(R.id.publisher_logo)
        ImageView publisherLogo;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
            itemView.setOnClickListener(onItemClickListener);
        }

        public void bind(Publisher publisher){
            publisherTitle.setText(publisher.getPublisherName());
            publisherCheck.setChecked(publisher.isChecked());
            Glide.with(context).load(publisher.getLogo()).centerCrop().into(publisherLogo);
            itemView.setTag(publisher);
        }
    }
}
