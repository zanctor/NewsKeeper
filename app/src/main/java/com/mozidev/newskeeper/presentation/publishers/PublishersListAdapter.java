package com.mozidev.newskeeper.presentation.publishers;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.mozidev.newskeeper.R;
import com.mozidev.newskeeper.domain.publishers.Publisher;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by mozi on 31.08.16.
 */
public class PublishersListAdapter extends RecyclerView.Adapter<PublishersListAdapter.ViewHolder> {

    private List<Publisher> data;
    private View.OnClickListener onItemClickListener;

    public PublishersListAdapter(List<Publisher> data) {
        this.data = data;
    }

    public void setOnItemClickListener(View.OnClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
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
            for (Publisher publisher : data) {
                publisher.setChecked(check);
            }
        }
        notifyDataSetChanged();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.publisher_name)
        TextView publisherName;
        @BindView(R.id.publisher_check)
        CheckBox publisherCheck;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
            itemView.setOnClickListener(onItemClickListener);
        }

        public void bind(Publisher publisher) {
            publisherName.setText(publisher.getPublisherName());
            publisherCheck.setChecked(publisher.isChecked());
            itemView.setTag(publisher);
        }
    }
}
