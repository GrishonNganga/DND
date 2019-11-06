package com.project.balmer.dnd.Model;

public class ShopInfo {
    private String id;
    private String name;
    private String location;
    private GoodInfo goodInfo;
    private String image;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public GoodInfo getGoodInfo() {
        return goodInfo;
    }

    public void setGoodInfo(GoodInfo goodInfo) {
        this.goodInfo = goodInfo;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public ShopInfo(String id, String name, String location, GoodInfo goodInfo, String image){
        this.id = id;
        this.name = name;
        this.location = location;
        this.goodInfo = goodInfo;
        this.image = image;

    }
}
