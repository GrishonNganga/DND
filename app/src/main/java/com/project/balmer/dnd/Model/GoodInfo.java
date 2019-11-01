package com.project.balmer.dnd.Model;

public class GoodInfo {
    private String id;
    private String name;
    private String description;
    private ShopInfo shopInfo;
    private String price;

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ShopInfo getShopInfo() {
        return shopInfo;
    }

    public void setShopInfo(ShopInfo shopInfo) {
        this.shopInfo = shopInfo;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public GoodInfo(String goodId, String name, String description, ShopInfo shopInfo, String price){
        this.id = goodId;
        this.name = name;
        this.description = description;
        this.shopInfo = shopInfo;
        this.price = price;
    }
}
