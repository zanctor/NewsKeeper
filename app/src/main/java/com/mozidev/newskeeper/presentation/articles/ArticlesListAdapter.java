package com.mozidev.newskeeper.presentation.articles;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.mozidev.newskeeper.NewsKeeper;
import com.mozidev.newskeeper.R;
import com.mozidev.newskeeper.domain.articles.Article;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by mozi on 31.08.16.
 */
public class ArticlesListAdapter extends RecyclerView.Adapter<ArticlesListAdapter.ViewHolder> {

    @Inject
    Context context;
    private List<Article> data;
    private View.OnClickListener onItemClickListener;

    public ArticlesListAdapter(List<Article> data) {
        NewsKeeper.getComponent().inject(this);
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

        @BindView(R.id.article_title)
        TextView articleTitle;
        @BindView(R.id.article_image)
        ImageView articleImage;
        @BindView(R.id.article_time)
        TextView articleTime;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
            itemView.setOnClickListener(onItemClickListener);
        }

        public void bind(Article article) {
            articleTitle.setText(article.getTitle());
            articleTime.setText(String.valueOf(article.getPublisherTime()));
            Glide.with(context)
                    .load(article.getImage())
                    .centerCrop()
                    .into(articleImage);
            itemView.setTag(article);
        }
    }
}
