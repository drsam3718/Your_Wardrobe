package com.example.yourwardrobe.model;

public class Outfit {
    private int id, topId, bottomId, jacketId, shoesId, accessoriesId;
    private String outfitName, outfitImagePath;

    public Outfit() {}

    public Outfit(int id, int topId, int bottomId, int jacketId, int shoesId, int accessoriesId, String outfitName, String outfitImagePath) {
        this.id = id;
        this.topId = topId;
        this.bottomId = bottomId;
        this.jacketId = jacketId;
        this.shoesId = shoesId;
        this.accessoriesId = accessoriesId;
        this.outfitName = outfitName;
        this.outfitImagePath = outfitImagePath;
    }

    public Outfit(int topId, int bottomId, int jacketId, int shoesId, int accessoriesId, String outfitName, String outfitImagePath) {
        this.topId = topId;
        this.bottomId = bottomId;
        this.jacketId = jacketId;
        this.shoesId = shoesId;
        this.accessoriesId = accessoriesId;
        this.outfitName = outfitName;
        this.outfitImagePath = outfitImagePath;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTopId() {
        return topId;
    }

    public void setTopId(int topId) {
        this.topId = topId;
    }

    public int getBottomId() {
        return bottomId;
    }

    public void setBottomId(int bottomId) {
        this.bottomId = bottomId;
    }

    public int getJacketId() {
        return jacketId;
    }

    public void setJacketId(int jacketId) {
        this.jacketId = jacketId;
    }

    public int getShoesId() {
        return shoesId;
    }

    public void setShoesId(int shoesId) {
        this.shoesId = shoesId;
    }

    public int getAccessoriesId() {
        return accessoriesId;
    }

    public void setAccessoriesId(int accessoriesId) {
        this.accessoriesId = accessoriesId;
    }

    public String getOutfitName() {
        return outfitName;
    }

    public void setOutfitName(String outfitName) {
        this.outfitName = outfitName;
    }

    public String getOutfitImagePath() {
        return outfitImagePath;
    }

    public void setOutfitImagePath(String outfitImagePath) {
        this.outfitImagePath = outfitImagePath;
    }
}
