package com.example.filmlist.items;

import java.util.List;

public class Film implements Item {
    private int type;
    private long id;

    private String localized_name;
    private String name;
    private int year;
    private double rating;
    private String image_url;
    private String description;
    public List<String> genres;

    public Film(int type, long id, String localized_name, String name, int year, double rating, String image_url, String description, List<String> genres) {
        this.type = type;
        this.id = id;

        this.localized_name = localized_name;
        this.name = name;
        this.year = year;
        this.rating = rating;
        this.image_url = image_url;
        this.description = description;
        this.genres = genres;

    }

    public String getLocalizedName() {
        return localized_name;
    }

    public void setLocalizedName(String localized_name) {
        this.localized_name = localized_name;
    }

    public void setType(int type) {
        this.type = type;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public int getItemType() {
        return type;
    }

    @Override
    public long getID() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<String> getGenres() {
        return genres;
    }

    public void setGenres(List<String> genres) {
        this.genres = genres;
    }
}
