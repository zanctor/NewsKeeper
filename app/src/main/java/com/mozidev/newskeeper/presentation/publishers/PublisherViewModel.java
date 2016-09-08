package com.mozidev.newskeeper.presentation.publishers;

import com.mozidev.newskeeper.domain.publishers.Publisher;

import java.io.Serializable;
import java.util.List;

import rx.Observable;

public class PublisherViewModel implements Serializable {

    private int id;
    private String publisher_name;
    private String description;
    private String logo;
    private String phone;
    private String stream;
    private String email;
    private String site;
    private String address;
    private boolean checked;

    private PublisherViewModel(int id, String publisher_name, String description, String logo, String phone, String stream, String email, String site, String address, boolean checked) {
        this.id = id;
        this.publisher_name = publisher_name;
        this.description = description;
        this.logo = logo;
        this.phone = phone;
        this.stream = stream;
        this.email = email;
        this.site = site;
        this.address = address;
        this.checked = checked;
    }

    public static PublisherViewModel create(Publisher publisher) {
        return publisher == null ? null : new PublisherViewModel(publisher.getId(),
                publisher.getPublisherName(),
                publisher.getDescription(),
                publisher.getLogo(),
                publisher.getPhone(),
                publisher.getStream(),
                publisher.getEmail(),
                publisher.getSite(),
                publisher.getAddress(),
                publisher.isChecked());
    }

    public static List<PublisherViewModel> create(List<Publisher> publishers) {
        return publishers == null ? null : Observable.from(publishers)
                .map(PublisherViewModel::create)
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

    public String getPublisherName() {
        return publisher_name;
    }

    public void setPublisherName(String publisher_name) {
        this.publisher_name = publisher_name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getStream() {
        return stream;
    }

    public void setStream(String stream) {
        this.stream = stream;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }
}
