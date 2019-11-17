package com.project.balmer.dnd.Model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GoodInfo implements Parcelable {
    @SerializedName("id")
    @Expose
    private String id;

    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("description")
    @Expose
    private String description;

    @SerializedName("shop")
    @Expose
    private String shopInfo;

    @SerializedName("price")
    @Expose
    private String price;

    @SerializedName("image")
    @Expose
    private String image;

    protected GoodInfo(Parcel in) {
        id = in.readString();
        name = in.readString();
        description = in.readString();
        shopInfo = in.readString();
        price = in.readString();
        image = in.readString();
    }

    public static final Creator<GoodInfo> CREATOR = new Creator<GoodInfo>() {
        @Override
        public GoodInfo createFromParcel(Parcel in) {
            return new GoodInfo(in);
        }

        @Override
        public GoodInfo[] newArray(int size) {
            return new GoodInfo[size];
        }
    };

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

    public String getShopInfo() {
        return shopInfo;
    }

    public void setShopInfo(String shopInfo) {
        this.shopInfo = shopInfo;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public GoodInfo(String goodId, String name, String description, String shopInfo, String price, String image){
        this.id = goodId;
        this.name = name;
        this.description = description;
        this.shopInfo = shopInfo;
        this.price = price;
        this.image = image;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(id);
        parcel.writeString(name);
        parcel.writeString(description);
        parcel.writeString(shopInfo);
        parcel.writeString(price);
        parcel.writeString(image);
    }
}
