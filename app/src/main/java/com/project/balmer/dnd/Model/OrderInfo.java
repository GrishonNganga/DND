package com.project.balmer.dnd.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class OrderInfo {
    @SerializedName("id")
    @Expose
    private String orderNo;

    @SerializedName("customer")
    @Expose
    private String customerInfoUid;

    @SerializedName("good")
    @Expose
    private GoodInfo goodInfo;

    private String quantity;

    @SerializedName("price")
    @Expose
    private String price;

    private List<GoodInfo> goods;

    public List<GoodInfo> getGoods() {
        return goods;
    }

    public void setGoods(List<GoodInfo> goods) {
        this.goods = goods;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getCustomerInfoUid() {
        return customerInfoUid;
    }

    public void setCustomerInfoUid(String customerInfoUid) {
        this.customerInfoUid = customerInfoUid;
    }

    public GoodInfo getGoodInfo() {
        return goodInfo;
    }

    public void setGoodInfo(GoodInfo goodInfo) {
        this.goodInfo = goodInfo;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public OrderInfo(String orderNo, String customerInfoUid, GoodInfo goodInfo, String quantity ){
        this.orderNo = orderNo;
        this.customerInfoUid = customerInfoUid;
        this.goodInfo = goodInfo;
        this.quantity = quantity;
    }

    public OrderInfo(String customerInfoUid, List<GoodInfo> goods, String price){
        this.customerInfoUid = customerInfoUid;
        this.goods = goods;
        this.price = price;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
