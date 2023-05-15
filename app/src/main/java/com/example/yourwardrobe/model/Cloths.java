package com.example.yourwardrobe.model;

import android.net.Uri;

public class Cloths {
    private int id;
    private String category;
    private String subcategory;
    private String path;

    public Cloths(){}

    public Cloths(String category, String subcategory, String path) {
        this.category = category;
        this.subcategory = subcategory;
        this.path = path;
    }

    public Cloths(int id, String category, String subcategory, String path) {
        this.id = id;
        this.category = category;
        this.subcategory = subcategory;
        this.path = path;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getSubcategory() {
        return subcategory;
    }

    public void setSubcategory(String subcategory) {
        this.subcategory = subcategory;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
