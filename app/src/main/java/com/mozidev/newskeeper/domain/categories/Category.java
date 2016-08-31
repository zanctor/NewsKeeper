package com.mozidev.newskeeper.domain.categories;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Category extends RealmObject {

    @PrimaryKey
    public int id;
    public boolean checked;
    public String title;
    public boolean is_deleted;

}
