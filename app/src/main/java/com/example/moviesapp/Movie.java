package com.example.moviesapp;
import java.io.Serializable;

public class Movie implements Serializable {
    private String title, year, duration, type, description, image, url;

    public Movie(String title, String year, String duration, String type, String description, String image, String url) {
        this.title = title;
        this.year = year;
        this.duration = duration;
        this.type = type;
        this.description = description;
        this.image = image;
        this.url = url;
    }

    public String getImage() {
        return image;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
