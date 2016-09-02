package com.mozidev.newskeeper.domain.categories;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

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
}
