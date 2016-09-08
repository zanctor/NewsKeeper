package com.mozidev.newskeeper.domain.categories;

import com.mozidev.newskeeper.presentation.categories.CategoryViewModel;

import java.util.List;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import rx.Observable;

public class Category extends RealmObject {

    @PrimaryKey
    int id;
    boolean checked = true;
    String title;
    boolean is_deleted;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isDeleted() {
        return is_deleted;
    }

    public void setDeleted(boolean is_deleted) {
        this.is_deleted = is_deleted;
    }

    public Category(){}

    Category(int id, String title, boolean checked) {
        this.id = id;
        this.title = title;
        this.checked = checked;
    }

    public static Category create(CategoryViewModel category) {
        return category == null ? null : new Category(category.getId(), category.getTitle(), category.isChecked());
    }

    public static List<Category> create(List<CategoryViewModel> categories) {
        return categories == null ? null : Observable.from(categories)
                .map(Category::create)
                .toList()
                .toBlocking()
                .first();
    }
}
