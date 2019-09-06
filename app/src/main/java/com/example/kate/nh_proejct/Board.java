package com.example.kate.nh_proejct;

import com.bumptech.glide.load.model.stream.UrlLoader;

import java.net.URL;
import java.util.Date;

public class Board {

    String title;
    String host_id;
    String image;
    String description;
    int heart;
    Date date;

    public Board(String title, String host_id, String image, String description, int heart, Date date) {
        this.title = title;
        this.host_id = host_id;
        this.image = image;
        this.description = description;
        this.heart = heart;
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public String getHost_id() {
        return host_id;
    }

    public String getImage() {
        return image;
    }

    public String getDescription() {
        return description;
    }

    public int getHeart() {
        return heart;
    }

    public Date getDate() {
        return date;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setHost_id(String host_id) {
        this.host_id = host_id;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setHeart(int heart) {
        this.heart = heart;
    }

    public void setDate(Date date) {
        this.date = date;
    }







}
