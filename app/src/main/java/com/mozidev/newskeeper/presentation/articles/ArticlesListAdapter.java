package com.mozidev.newskeeper.presentation.articles;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.mozidev.newskeeper.R;
import com.mozidev.newskeeper.domain.articles.Article;
import com.mozidev.newskeeper.presentation.injection.DaggerHelper;

import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by mozi on 31.08.16.
 */
public class ArticlesListAdapter extends RecyclerView.Adapter<ArticlesListAdapter.ViewHolder> {

    @Inject
    Context context;
    private List<ArticleViewModel> data;
    private View.OnClickListener onItemClickListener;

    public ArticlesListAdapter(List<ArticleViewModel> data) {
        DaggerHelper.getMainComponent().inject(this);
        this.data = data;
    }

    public void setOnItemClickListener(View.OnClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_article, parent, false);
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

        @Bind(R.id.article_title)
        TextView articleTitle;
        @Bind(R.id.article_image)
        ImageView articleImage;
        @Bind(R.id.article_time)
        TextView articleTime;
        @Bind(R.id.publisher_title)
        TextView publisherTitle;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
            itemView.setOnClickListener(onItemClickListener);
        }

        public void bind(ArticleViewModel article) {
            articleTitle.setText(article.getTitle());
            articleTime.setText(String.valueOf(article.getPublisherTime()));
            publisherTitle.setText(article.getPublisherName());
            Glide.with(context)
                    .load(article.getImage())
                    .placeholder(R.drawable.placeholder)
                    .centerCrop()
                    .into(articleImage);
            itemView.setTag(article);
        }
    }
}
