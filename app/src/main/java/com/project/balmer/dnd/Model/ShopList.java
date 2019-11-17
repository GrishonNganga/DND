package com.project.balmer.dnd.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;


public class ShopList {

    @SerializedName("shop")
    @Expose
    private List<ShopInfo> shops = new ArrayList<>();

    public List<ShopInfo> getShops() {
        return shops;
    }

    public void setShops(List<ShopInfo> shops) {
        this.shops = shops;
    }
}
