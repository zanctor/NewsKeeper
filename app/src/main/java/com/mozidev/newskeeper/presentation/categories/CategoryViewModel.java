package com.mozidev.newskeeper.presentation.categories;

import com.mozidev.newskeeper.domain.categories.Category;

import java.util.List;

import rx.Observable;

public class CategoryViewModel {

    private int id;
    private String title;
    private boolean checked;

    private CategoryViewModel(int id, String title, boolean checked){
        this.id = id;
        this.title = title;
        this.checked = checked;
    }

    public static CategoryViewModel create(Category category) {
        return category == null ? null : new CategoryViewModel(category.getId(), category.getTitle(), category.isChecked());
    }

    public static List<CategoryViewModel> create(List<Category> categories) {
        return categories == null ? null : Observable.from(categories)
                .map(CategoryViewModel::create)
                .toList()
                .toBlocking()
                .first();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }
}
