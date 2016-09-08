package com.mozidev.newskeeper.presentation.articles;

import com.mozidev.newskeeper.domain.articles.Article;

import java.io.Serializable;
import java.util.List;

import rx.Observable;

public class ArticleViewModel implements Serializable {

    private int id;
    private int publisher_id;
    private int category_id;
    private long publisher_time;
    private String publisher_name;
    private String publisher_logo;
    private String title;
    private String image;
    private String link;
    private String video;
    private String text;

    private ArticleViewModel(int id, int publisher_id, int category_id, long publisher_time, String title, String image, String link, String video, String text) {
        this.id = id;
        this.publisher_id = publisher_id;
        this.category_id = category_id;
        this.publisher_time = publisher_time;
        this.title = title;
        this.image = image;
        this.link = link;
        this.video = video;
        this.text = text;
    }

    public static ArticleViewModel create(Article article) {
        return new ArticleViewModel(article.getId(),
                article.getPublisherId(),
                article.getCategoryId(),
                article.getPublisherTime(),
                article.getTitle(),
                article.getImage(),
                article.getLink(),
                article.getVideo(),
                article.getText());
    }

    public static List<ArticleViewModel> create(List<Article> articles) {
        return Observable.from(articles)
                .map(ArticleViewModel::create)
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

    public int getPublisherId() {
        return publisher_id;
    }

    public void setPublisherId(int publisher_id) {
        this.publisher_id = publisher_id;
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

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getVideo() {
        return video;
    }

    public void setVideo(String video) {
        this.video = video;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getPublisherLogo() {
        return publisher_logo;
    }

    public void setPublisherLogo(String publisher_logo) {
        this.publisher_logo = publisher_logo;
    }

    public String getPublisherName() {
        return publisher_name;
    }

    public void setPublisherName(String publisher_name) {
        this.publisher_name = publisher_name;
    }

    public int getCategoryId() {
        return category_id;
    }

    public void setCategoryId(int category_id) {
        this.category_id = category_id;
    }
}
