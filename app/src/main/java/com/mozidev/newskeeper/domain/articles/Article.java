package com.mozidev.newskeeper.domain.articles;

import android.support.annotation.Nullable;

import com.mozidev.newskeeper.domain.categories.Category;

import java.io.Serializable;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Article extends RealmObject implements Serializable {

    @PrimaryKey
    int id;
    long publisher_time;
    String title;
    String image;
    String link;
    @Nullable
    String video;
    @Nullable
    String text;
    boolean status;
    boolean is_deleted;
    Category category;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public long getPublisherTime() {
        return publisher_time;
    }

    public void setPublisherTime(long publisher_time) {
        this.publisher_time = publisher_time;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public boolean isDeleted() {
        return is_deleted;
    }

    public void setDeleted(boolean is_deleted) {
        this.is_deleted = is_deleted;
    }

    public String getVideo() {
        return video;
    }

    public void setVideo(String video) {
        this.video = video;
    }

    public int getCategoryId() {
        return category.getId();
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    @Nullable
    public String getText() {
        return text;
    }

    public void setText(@Nullable String text) {
        this.text = text;
    }
}
