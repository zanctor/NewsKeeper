package com.mozidev.newskeeper.domain.articles;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Article extends RealmObject {

    @PrimaryKey
    int id;

}
