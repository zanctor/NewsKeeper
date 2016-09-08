package com.mozidev.newskeeper.presentation.categories;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.mozidev.newskeeper.R;
import com.mozidev.newskeeper.domain.categories.Category;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import io.realm.Realm;

/**
 * Created by mozi on 31.08.16.
 */
public class CategoriesListAdapter extends RecyclerView.Adapter<CategoriesListAdapter.ViewHolder> {

    private List<CategoryViewModel> data;
    private View.OnClickListener onItemClickListener;

    public CategoriesListAdapter(List<CategoryViewModel> data) {
        this.data = data;
    }

    public void setOnItemClickListener(View.OnClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_category, parent, false);
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
            for (CategoryViewModel category : data) {
                category.setChecked(check);
            }
            notifyDataSetChanged();
        }
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.category_title)
        TextView categoryTitle;
        @Bind(R.id.category_check)
        CheckBox categoryCheck;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
            itemView.setOnClickListener(onItemClickListener);
        }

        public void bind(CategoryViewModel category) {
            categoryTitle.setText(category.getTitle());
            categoryCheck.setChecked(category.isChecked());
            itemView.setTag(category);
        }
    }
}
